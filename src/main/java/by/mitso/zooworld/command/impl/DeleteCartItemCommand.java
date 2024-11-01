package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.Cart;
import by.mitso.zooworld.entity.CartItem;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.impl.CartDaoImpl;
import by.mitso.zooworld.model.dao.impl.ProductDaoImpl;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.CartService;
import by.mitso.zooworld.model.service.impl.CartServiceImpl;
import by.mitso.zooworld.model.service.impl.ProductServiceImpl;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeleteCartItemCommand implements Command {

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
        long productId = Long.parseLong(request.getParameter(ParameterAndAttribute.PRODUCT_ID));
        int cartItemsCount = (int) session.getAttribute(ParameterAndAttribute.CART_ITEMS_COUNT);
        double cartTotalPrice = (double) session.getAttribute(ParameterAndAttribute.CART_TOTAL_PRICE);

        try {
            Optional<Cart> cart = cartService.findByUser(user);

            if (cart.isPresent()) {
                List<CartItem> cartItems = cart.get().getItems();
                List<CartItem> itemsToDelete = findCartItemsToDelete(cartItems, productId);
                session.setAttribute(ParameterAndAttribute.CART_ITEMS_COUNT, cartItemsCount - itemsToDelete.size());
                session.setAttribute(ParameterAndAttribute.CART_TOTAL_PRICE, calculateCartTotalPrice(cartTotalPrice, itemsToDelete));
                cartService.clear(itemsToDelete);
                router.setPagePath(PagePath.TO_CART_PAGE);
                router.setType(Router.Type.REDIRECT);
            }

        } catch (ServiceException e) {
            router.setPagePath(PagePath.TO_CART_PAGE);
            request.setAttribute(ParameterAndAttribute.MESSAGE, e.getMessage());
        }


        return router;
    }

    private List<CartItem> findCartItemsToDelete(List<CartItem> cartItems, long productId) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.getProduct().getId() == productId)
                .collect(Collectors.toList());
    }

    private double calculateCartTotalPrice(double currentTotalPrice, List<CartItem> itemsToDelete) {
        double totalCartPriceAfterItemsDelete = currentTotalPrice;

        for (CartItem cartItem : itemsToDelete) {
            totalCartPriceAfterItemsDelete -= cartItem.getTotalPrice();
        }
        return (double) Math.round(totalCartPriceAfterItemsDelete * 100) / 100;
    }

}
