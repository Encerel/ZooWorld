package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.Order;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.impl.OrderDaoImpl;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.OrderService;
import by.mitso.zooworld.model.service.impl.OrderServiceImpl;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class ToOrderPageCommand implements Command {

    private final OrderService orderService = new OrderServiceImpl(
            new OrderDaoImpl(),
            new UserServiceImpl(new UserDaoImpl())
    );

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ParameterAndAttribute.USER);

        if (user == null) {
            router.setType(Router.Type.REDIRECT);
            router.setPagePath(PagePath.TO_SIGN_IN_PAGE);
            return router;
        }

        List<Order> ordersByUser = orderService.findOrdersByUser(user);

        if (ordersByUser.isEmpty()) {
            request.setAttribute(ParameterAndAttribute.MESSAGE, Message.NOTHING_HERE);
        }
        request.setAttribute(ParameterAndAttribute.ORDERS, ordersByUser);
        router.setPagePath(PagePath.ORDER);
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ORDER);

        return router;
    }
}
