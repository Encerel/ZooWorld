package by.mitso.zooworld.model.dao.impl;

import by.mitso.zooworld.entity.Cart;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.DaoException;
import by.mitso.zooworld.model.connection.HibernateSessionFactoryProvider;
import by.mitso.zooworld.model.dao.CartDao;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.mitso.zooworld.model.dao.ColumnName.*;

public class CartDaoImpl implements CartDao {

    @Override
    public List<Cart> findAll() {

        List<Cart> carts;

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            carts = session.createQuery("FROM Cart", Cart.class).list();

            session.getTransaction().commit();
        }

        return carts;
    }

    @Override
    public Optional<Cart> findById(long id) {

        Optional<Cart> cart = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            cart = session.createQuery("FROM Cart c WHERE c.id = :id", Cart.class)
                    .setParameter(CART_ID, id)
                    .uniqueResultOptional();

            session.getTransaction().commit();
        }

        return cart;
    }

    @Override
    public Optional<Cart> findByUser(User user) throws DaoException {

        Optional<Cart> cart = Optional.empty();
        Optional<User> userFromDB = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            userFromDB = session.createQuery("FROM User u WHERE u.id = :id", User.class)
                    .setParameter(USER_ID, user.getId())
                    .uniqueResultOptional();

            if (!userFromDB.isPresent()) {
                throw new DaoException("No user with id = " + user.getId());
            } else {
                cart = session.createQuery("FROM Cart c WHERE c.user = :id", Cart.class)
                        .setParameter(USER_ID, userFromDB.get())
                        .uniqueResultOptional();
            }


            session.getTransaction().commit();
        }
        return cart;
    }
}
