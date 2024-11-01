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
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToCartPageCommand implements Command {

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
        List<CartItem> cartItems = new ArrayList<>();

        if (user == null) {
            router.setPagePath(PagePath.TO_SIGN_IN_PAGE);
            return router;
        }

        try {
            Optional<Cart> optionalCart = cartService.findByUser(user);

            if (optionalCart.isPresent()) {
                Cart cart = optionalCart.get();

                cartItems = cart.getItems();
                request.setAttribute(ParameterAndAttribute.CART_ITEMS, cartItems);
                router.setPagePath(PagePath.CART);
                session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.CART);
                if (cartItems.isEmpty()) {
                    request.setAttribute(ParameterAndAttribute.MESSAGE, Message.NOTHING_HERE);
                }
            }

        } catch (ServiceException e) {
            router.setPagePath(PagePath.TO_MAIN_PAGE);
            request.setAttribute(ParameterAndAttribute.MESSAGE, e.getMessage());
        }

        return router;
    }


}
