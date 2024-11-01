package by.mitso.zooworld.model.dao;

import by.mitso.zooworld.entity.Order;
import by.mitso.zooworld.entity.Order.OrderStatus;
import by.mitso.zooworld.entity.OrderItem;
import by.mitso.zooworld.entity.User;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    List<Order> findAll();

    Optional<Order> findById(long id);

    List<Order> findOrdersByUser(User user);

    boolean save(Order order);

    boolean changeOrderStatus(long id, OrderStatus status);

    List<OrderItem> findOrderItemsByOrder(Order order);

    List<Order> findByStatus(OrderStatus status);
}
