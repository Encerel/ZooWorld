package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.*;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.impl.CartDaoImpl;
import by.mitso.zooworld.model.dao.impl.OrderDaoImpl;
import by.mitso.zooworld.model.dao.impl.ProductDaoImpl;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.CartService;
import by.mitso.zooworld.model.service.OrderService;
import by.mitso.zooworld.model.service.impl.CartServiceImpl;
import by.mitso.zooworld.model.service.impl.OrderServiceImpl;
import by.mitso.zooworld.model.service.impl.ProductServiceImpl;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.*;

public class MakeOrderCommand implements Command {

    private final OrderService orderService = new OrderServiceImpl(
            new OrderDaoImpl(),
            new UserServiceImpl(new UserDaoImpl()));

    private final CartService cartService = new CartServiceImpl(
            new CartDaoImpl(),
            new UserServiceImpl(new UserDaoImpl()),
            new ProductServiceImpl(new ProductDaoImpl())
    );

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ParameterAndAttribute.USER);
        Optional<Cart> cart = null;
        List<CartItem> cartItems = new ArrayList<>();
        try {
            cart = cartService.findByUser(user);
            cartItems = cart.get().getItems();
        } catch (ServiceException e) {
            router.setPagePath(PagePath.TO_CART_PAGE);
            request.setAttribute(ParameterAndAttribute.MESSAGE, e.getMessage());
            return router;
        }

        List<OrderItem> orderItems = convertCartItemsToOrderItems(cartItems);
        Order order = Order.builder()
                .owner(user)
                .orderAt(new Date())
                .items(orderItems)
                .totalPrice(calculateOrderTotalPrice(orderItems))
                .status(Order.OrderStatus.PENDING)
                .completedAt(null)
                .build();

        orderService.save(order);
        cartService.clear(cartItems);

        session.setAttribute(ParameterAndAttribute.CART_TOTAL_PRICE, 0.0d);
        session.setAttribute(ParameterAndAttribute.CART_ITEMS_COUNT, 0);
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ORDER);
        router.setType(Router.Type.REDIRECT);
        router.setPagePath(PagePath.TO_ORDER_PAGE);

        return router;
    }

    private List<OrderItem> convertCartItemsToOrderItems(List<CartItem> cartItems) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {

            OrderItem orderItem = OrderItem.builder()
                    .product(cartItem.getProduct())
                    .quantity(cartItem.getQuantity())
                    .totalPrice(cartItem.getTotalPrice())
                    .build();
            orderItems.add(orderItem);

        }
        return orderItems;
    }

    private double calculateOrderTotalPrice(List<OrderItem> items) {
        double totalPrice = 0;

        for(OrderItem orderItem : items) {
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }

    private List<CartItem> defineSelectedItems(List<CartItem> cartItems, String[] productIds) throws NullPointerException {

        List<CartItem> selectedItems = new ArrayList<>();
        List<Long> integerIds = Arrays.stream(productIds)
                .map(Long::parseLong)
                .toList();

        for (CartItem cartItem : cartItems ) {

            for (Long id : integerIds) {
                if (cartItem.getProduct().getId() == id) {
                    selectedItems.add(cartItem);
                }
            }

        }
        return selectedItems;
    }

}

