package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.Cart;
import by.mitso.zooworld.entity.CartItem;
import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.impl.CartDaoImpl;
import by.mitso.zooworld.model.dao.impl.ProductDaoImpl;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.CartService;
import by.mitso.zooworld.model.service.ProductService;
import by.mitso.zooworld.model.service.impl.CartServiceImpl;
import by.mitso.zooworld.model.service.impl.ProductServiceImpl;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;

public class AddProductToCartCommand implements Command {

    private final ProductService productService = new ProductServiceImpl(new ProductDaoImpl());
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

        if (user == null) {
            router.setPagePath(PagePath.TO_SIGN_IN_PAGE);
            router.setType(Router.Type.REDIRECT);
            return router;
        }

        long productId = Long.parseLong(request.getParameter(ParameterAndAttribute.PRODUCT_ID));
        int quantity = Integer.parseInt(request.getParameter(ParameterAndAttribute.PRODUCT_QUANTITY));
        int cartItemsCount = (int) session.getAttribute(ParameterAndAttribute.CART_ITEMS_COUNT);
        double cartTotalPrice = (double) session.getAttribute(ParameterAndAttribute.CART_TOTAL_PRICE);
        String currentPage = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);


        try {

            Optional<Product> product = productService.findById(productId);
            Optional<Cart> cart = cartService.findByUser(user);

            if (product.isPresent() && cart.isPresent()) {
                List<CartItem> itemsInCart = cart.get().getItems();
                CartItem cartItem = CartItem.builder()
                        .cart(cart.get())
                        .product(product.get())
                        .totalPrice(product.get().getPrice() * quantity)
                        .quantity(quantity)
                        .build();

                if (isSuchItemInCart(itemsInCart, cartItem)) {
                    if (cartService.updateExistedCartItem(findExistedItemInCart(itemsInCart,cartItem), cartItem)) {
                        session.setAttribute(
                                ParameterAndAttribute.CART_TOTAL_PRICE,
                                roundPrice(cartTotalPrice + cartItem.getTotalPrice())
                        );
                    }
                } else {
                    if (cartService.addProductToCart(cart.get(), cartItem)) {
                        session.setAttribute(
                                ParameterAndAttribute.CART_TOTAL_PRICE,
                                roundPrice(cartTotalPrice + cartItem.getTotalPrice())
                        );
                        session.setAttribute(ParameterAndAttribute.CART_ITEMS_COUNT, (cartItemsCount + 1));
                    }
                }
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.MESSAGE, e.getMessage());
            router.setPagePath(PagePath.PRODUCTS);
        }


        return router;
    }

    private boolean isSuchItemInCart(List<CartItem> itemsInCart, CartItem cartItem) {
        return itemsInCart.contains(cartItem);
    }


    private CartItem findExistedItemInCart(List<CartItem> itemsInCart, CartItem cartItem) throws ServiceException {
        for (CartItem item : itemsInCart) {

            if (item.getCart().equals(cartItem.getCart()) &&
                    item.getProduct().equals(cartItem.getProduct())) {
                return item;
            }
        }
        throw new ServiceException(
                Message.NO_CART_WITH_ID + cartItem.getCart().getId() +
                " or " +
                Message.NO_PRODUCT_WITH_ID + cartItem.getProduct().getId()
        );
    }

    private double roundPrice(double price) {
        return (double) Math.round(price * 100) / 100;
    }
}
