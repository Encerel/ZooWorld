package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.Cart;
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

import java.util.Optional;

public class ClearCartCommand implements Command {

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
        try {
            Optional<Cart> cart = cartService.findByUser(user);

            if (cart.isEmpty()) {
                throw new ServiceException(Message.ORDER_SHOULD_CONTAINS_AT_LEAST_ONE_ITEM);
            }

            cartService.clear(cart.get());
        } catch (ServiceException e) {
            router.setPagePath(PagePath.TO_CART_PAGE);
            request.setAttribute(ParameterAndAttribute.MESSAGE, e.getMessage());
            return router;
        }
        cartService.clear(user.getCart());

        router.setPagePath(PagePath.TO_CART_PAGE);
        router.setType(Router.Type.REDIRECT);
        return router;
    }
}
