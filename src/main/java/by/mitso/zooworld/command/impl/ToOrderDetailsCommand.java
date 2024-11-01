package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.Order;
import by.mitso.zooworld.entity.OrderItem;
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
import java.util.Optional;

public class ToOrderDetailsCommand implements Command {

    private final OrderService orderService = new OrderServiceImpl(
            new OrderDaoImpl(),
            new UserServiceImpl(new UserDaoImpl())
    );

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ParameterAndAttribute.USER);


        long orderId = Long.parseLong(request.getParameter(ParameterAndAttribute.ORDER_ID));

        try {
            Optional<Order> order = orderService.findById(orderId);

            if (order.isPresent()) {
                List<OrderItem> items = orderService.findOrderItemsByOrderAndUser(order.get(), user);

                if (items.isEmpty()) {
                    request.setAttribute(ParameterAndAttribute.MESSAGE, Message.NOTHING_HERE);
                }
                request.setAttribute(ParameterAndAttribute.ORDER_ITEMS, items);
                router.setPagePath(PagePath.ORDER_DETAILS);

            } else {
                router.setPagePath(PagePath.TO_ORDER_PAGE);
                request.setAttribute(ParameterAndAttribute.MESSAGE, Message.NO_ORDER_WITH_ID + orderId);
            }
        } catch (ServiceException e) {
            router.setPagePath(PagePath.ERROR);
            router.setType(Router.Type.REDIRECT);
        }

        return router;
    }
}
