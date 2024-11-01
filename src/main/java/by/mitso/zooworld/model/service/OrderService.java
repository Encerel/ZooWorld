package by.mitso.zooworld.model.service;

import by.mitso.zooworld.entity.Order;
import by.mitso.zooworld.entity.OrderItem;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();

    Optional<Order> findById(long id) throws ServiceException;

    List<Order> findOrdersByUser(User user);

    List<OrderItem> findOrderItemsByOrderAndUser(Order order, User user) throws ServiceException;

    boolean save(Order order);
    boolean changeOrderStatus(long id, Order.OrderStatus status) throws ServiceException;

    List<Order> findByStatus(Order.OrderStatus pending);
}
