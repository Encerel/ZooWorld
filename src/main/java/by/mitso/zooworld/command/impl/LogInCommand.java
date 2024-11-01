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
import by.mitso.zooworld.model.service.UserService;
import by.mitso.zooworld.model.service.impl.CartServiceImpl;
import by.mitso.zooworld.model.service.impl.ProductServiceImpl;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LogInCommand implements Command {

    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    private final CartService cartService = new CartServiceImpl(
            new CartDaoImpl(),
            new UserServiceImpl(new UserDaoImpl()),
            new ProductServiceImpl(new ProductDaoImpl())
    );

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user;
        String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
        String password = request.getParameter(ParameterAndAttribute.USER_PASSWORD);
        Optional<User> optionalUser;
        try {
            optionalUser = userService.findUserByEmailPassword(email, password);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                Cart cart = cartService.findByUser(user).get();
                router.setPagePath(PagePath.TO_MAIN_PAGE);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(ParameterAndAttribute.USER, user);
                session.setAttribute(ParameterAndAttribute.CART, user.getCart());
                session.setAttribute(ParameterAndAttribute.CART_ITEMS_COUNT, cart.getItems().size());
                session.setAttribute(ParameterAndAttribute.CART_TOTAL_PRICE, calculateCartTotalPrice(cart));
            }
        } catch (ServiceException e) {
            router.setPagePath(PagePath.SIGN_IN);
            request.setAttribute(ParameterAndAttribute.MESSAGE, Message.INCORRECT_EMAIL_OR_LOGIN);
        }
        return router;
    }


    private double calculateCartTotalPrice(Cart cart) {
        double totalPrice = 0;

        for (CartItem cartItem : cart.getItems()) {
            totalPrice += cartItem.getTotalPrice();
        }

        return totalPrice;
    }
}
