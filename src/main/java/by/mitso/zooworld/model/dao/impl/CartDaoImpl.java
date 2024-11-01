package by.mitso.zooworld.model.dao.impl;

import by.mitso.zooworld.entity.Cart;
import by.mitso.zooworld.entity.CartItem;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.model.connection.HibernateSessionFactoryProvider;
import by.mitso.zooworld.model.dao.CartDao;
import by.mitso.zooworld.model.dao.ColumnName;
import org.hibernate.Session;

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
    public Optional<Cart> findByUser(User user) {

        Optional<Cart> cart = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            User userFromDB = session.get(User.class, user.getId());

            cart = session.createQuery("FROM Cart c WHERE c.user = :id", Cart.class)
                    .setParameter(USER_ID, userFromDB)
                    .uniqueResultOptional();


            session.getTransaction().commit();
        }
        return cart;
    }

    @Override
    public boolean addProductToCart(Cart cart, CartItem item) {

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            Cart cartFromDB = session.get(Cart.class, cart.getId());
            session.save(item);
            cartFromDB.getItems().add(item);
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public boolean deleteCartItem(Cart cart, CartItem item) {

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            Cart cartFromDB = session.get(Cart.class, cart.getId());
            cartFromDB.getItems().remove(item);
            session.getTransaction().commit();
            return true;
        }

    }

    @Override
    public boolean clear(Cart cart) {

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            for (CartItem cartItem : cart.getItems()) {
                session.delete(cartItem);
            }
            session.getTransaction().commit();
            return true;
        }

    }

    @Override
    public boolean clear(List<CartItem> items) {

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();

            for (CartItem cartItem : items) {
                session.delete(cartItem);
            }
            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public boolean updateExistedCartItem(CartItem item) {

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        }
        return true;
    }

}
