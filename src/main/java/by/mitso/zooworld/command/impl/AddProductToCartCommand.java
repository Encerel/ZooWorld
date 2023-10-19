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

        long productId = Long.parseLong(request.getParameter(ParameterAndAttribute.PRODUCT_ID));
        int quantity = Integer.parseInt(request.getParameter(ParameterAndAttribute.PRODUCT_QUANTITY));

        try {
            Optional<Product> product = productService.findById(productId);
            Optional<Cart> cart = cartService.findByUser(user);
            if (product.isPresent() && cart.isPresent()) {
                CartItem cartItem = CartItem.builder()
                        .cart(cart.get())
                        .product(product.get())
                        .totalPrice(product.get().getPrice() * quantity)
                        .quantity(quantity)
                        .build();
                cartService.addProductToCart(cart.get(), cartItem);
                router.setPagePath(PagePath.TO_PRODUCTS_PAGE);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.MESSAGE, e.getMessage());
            router.setPagePath(PagePath.PRODUCTS);
        }


        return router;
    }
}
