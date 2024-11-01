package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.Order;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.impl.OrderDaoImpl;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.OrderService;
import by.mitso.zooworld.model.service.UserService;
import by.mitso.zooworld.model.service.impl.OrderServiceImpl;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public class FindUserByIdCommand implements Command {

    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    private final OrderService orderService = new OrderServiceImpl(
            new OrderDaoImpl(),
            new UserServiceImpl(new UserDaoImpl()));

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        long id;
        try {
           id = Long.parseLong(request.getParameter(ParameterAndAttribute.USER_ID));
            Optional<User> foundUser = userService.findById(id);

            if (foundUser.isPresent()) {
                List<Order> ordersByUser = orderService.findOrdersByUser(foundUser.get());
                request.setAttribute(ParameterAndAttribute.ORDERS, ordersByUser);
                request.setAttribute(ParameterAndAttribute.USER, foundUser.get());
                router.setPagePath(PagePath.USER_FOR_ADMIN);
            }

        } catch (NumberFormatException e) {
            router.setPagePath(PagePath.ADMIN);
            request.setAttribute(ParameterAndAttribute.MESSAGE, Message.NO_USER_WITH_SUCH_ID);
        }


        return router;
    }
}
