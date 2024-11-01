package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.Command;
import by.mitso.zooworld.command.PagePath;
import by.mitso.zooworld.command.ParameterAndAttribute;
import by.mitso.zooworld.command.Router;
import by.mitso.zooworld.entity.Order;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.impl.OrderDaoImpl;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.OrderService;
import by.mitso.zooworld.model.service.impl.OrderServiceImpl;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class CancelOrderAdminCommand implements Command {

    private final OrderService orderService = new OrderServiceImpl(
            new OrderDaoImpl(),
            new UserServiceImpl(new UserDaoImpl()));

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        long orderId = Long.parseLong(request.getParameter(ParameterAndAttribute.ORDER_ID));

        try {
            orderService.changeOrderStatus(orderId, Order.OrderStatus.CANCELED);
            router.setPagePath(PagePath.TO_ORDERS_PAGE);
        } catch (ServiceException e) {
            router.setPagePath(PagePath.TO_ORDERS_PAGE);
            request.setAttribute(ParameterAndAttribute.MESSAGE, e.getMessage());
        }


        return router;
    }
}
