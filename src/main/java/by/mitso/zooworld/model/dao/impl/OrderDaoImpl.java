package by.mitso.zooworld.model.dao.impl;

import by.mitso.zooworld.entity.Order;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.DaoException;
import by.mitso.zooworld.model.connection.HibernateSessionFactoryProvider;
import by.mitso.zooworld.model.dao.OrderDao;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.mitso.zooworld.model.dao.ColumnName.ORDER_ID;

public class OrderDaoImpl implements OrderDao {

    @Override
    public List<Order> findAll() {

        List<Order> orders = new ArrayList<>();
        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();
            orders = session.createQuery("FROM Order", Order.class).list();
            session.getTransaction().commit();
        }
        return orders;
    }

    @Override
    public Optional<Order> findById(long id) {

        Optional<Order> order = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();
            order = session.createQuery("FROM Order o WHERE o.id = :id", Order.class)
                    .setParameter(ORDER_ID, id)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        }
        return order;
    }

    @Override
    public List<Order> findOrdersByUser(User user) {

        List<Order> orders = new ArrayList<>();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();
            orders = session.createQuery("FROM Order o WHERE o.owner.id = :id", Order.class)
                    .setParameter(ORDER_ID, user.getId())
                    .list();
            session.getTransaction().commit();
            return orders;
        }


    }

    @Override
    public boolean changeOrderStatus(long id, Order.OrderStatus status) throws DaoException {

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();
            Order order = session.get(Order.class, id);

            if (order != null) {
                order.setStatus(status);
                session.update(order);
                session.getTransaction().commit();
                return true;
            }

            throw new DaoException("No order with id = " + id);
        }

    }
}
