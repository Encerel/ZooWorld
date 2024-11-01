package by.mitso.zooworld.model.dao.impl;

import by.mitso.zooworld.entity.Order;
import by.mitso.zooworld.entity.OrderItem;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.model.connection.HibernateSessionFactoryProvider;
import by.mitso.zooworld.model.dao.ColumnName;
import by.mitso.zooworld.model.dao.OrderDao;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.mitso.zooworld.model.dao.ColumnName.ORDER_ID;
import static by.mitso.zooworld.model.dao.ColumnName.ORDER_STATUS;

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
    public boolean save(Order order) {

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(order);
            for (OrderItem item : order.getItems()) {
                item.setOrder(order);
                session.save(item);
            }
            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public boolean changeOrderStatus(long id, Order.OrderStatus status) {

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();
            Order order = session.get(Order.class, id);
            order.setStatus(status);
            session.getTransaction().commit();
            return true;

        }
    }

    @Override
    public List<OrderItem> findOrderItemsByOrder(Order order) {

        List<OrderItem> items = new ArrayList<>();


        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();

            items = session.createQuery("FROM OrderItem WHERE order = :id", OrderItem.class)
                    .setParameter(ORDER_ID, order)
                    .list();

            session.getTransaction().commit();

        }
        return items;
    }

    @Override
    public List<Order> findByStatus(Order.OrderStatus status) {

        List<Order> items = new ArrayList<>();


        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();

            items = session.createQuery("FROM Order o WHERE o.status = :status", Order.class)
                    .setParameter(ORDER_STATUS, status)
                    .list();

            for (Order order : items) {
                Hibernate.initialize(order.getOwner());
            }
            session.getTransaction().commit();

        }
        return items;
    }
}
