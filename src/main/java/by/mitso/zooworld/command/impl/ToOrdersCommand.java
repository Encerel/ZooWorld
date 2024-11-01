package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.Order;
import by.mitso.zooworld.model.dao.impl.OrderDaoImpl;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.OrderService;
import by.mitso.zooworld.model.service.impl.OrderServiceImpl;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class ToOrdersCommand implements Command {

    private final OrderService orderService = new OrderServiceImpl(
            new OrderDaoImpl(),
            new UserServiceImpl(new UserDaoImpl()));

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        List<Order> orders = orderService.findByStatus(Order.OrderStatus.PENDING);
        if (orders.size() == 0) {
            request.setAttribute(ParameterAndAttribute.MESSAGE, Message.NOTHING_HERE);
        }
        request.setAttribute(ParameterAndAttribute.ORDERS, orders);
        router.setPagePath(PagePath.ORDERS);
        return router;
    }
}
