package by.mitso.zooworld.model.dao;

import by.mitso.zooworld.entity.Order;
import by.mitso.zooworld.entity.Order.OrderStatus;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    List<Order> findAll();

    Optional<Order> findById(long id);

    List<Order> findOrdersByUser(User user);

    boolean changeOrderStatus(long id, OrderStatus status) throws DaoException;

}
