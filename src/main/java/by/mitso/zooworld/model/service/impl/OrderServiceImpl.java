package by.mitso.zooworld.model.service.impl;

import by.mitso.zooworld.entity.Order;
import by.mitso.zooworld.entity.Order.OrderStatus;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.OrderDao;
import by.mitso.zooworld.model.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Optional<Order> findById(long id) throws ServiceException {
        return orderDao.findById(id);
    }

    @Override
    public List<Order> findOrdersByUser(User user){

        return orderDao.findOrdersByUser(user);
    }

    @Override
    public boolean changeOrderStatus(long id, OrderStatus status) throws ServiceException {

        Optional<Order> order = orderDao.findById(id);

        if (order.isEmpty()) {
            throw new ServiceException("No order with id = " + id);
        }

        return orderDao.changeOrderStatus(id, status);
    }
}
