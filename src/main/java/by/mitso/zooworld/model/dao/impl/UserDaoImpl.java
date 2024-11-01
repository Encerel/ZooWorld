package by.mitso.zooworld.model.dao.impl;

import by.mitso.zooworld.entity.Cart;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.entity.User.Role;
import by.mitso.zooworld.model.connection.HibernateSessionFactoryProvider;
import by.mitso.zooworld.model.dao.ColumnName;
import by.mitso.zooworld.model.dao.UserDao;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.mitso.zooworld.model.dao.ColumnName.*;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            users = session.createQuery("FROM User ", User.class).list();

            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public Optional<User> findById(long id) {

        Optional<User> user = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();

            user = session.createQuery("FROM User u WHERE u.id = :id", User.class)
                    .setParameter(USER_ID, id)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        }

        return user;
    }

    @Override
    public List<User> findUsersByFirstName(String firstName) {

        List<User> users = new ArrayList<>();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            users = session.createQuery("FROM User u WHERE u.firstName like :first_name%", User.class)
                    .setParameter(FIRST_NAME, "%" + firstName + "%")
                    .list();

            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public List<User> findUsersByLastName(String lastName) {

        List<User> users = new ArrayList<>();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            users = session.createQuery("FROM User u WHERE u.lastName like :last_name", User.class)
                    .setParameter(LAST_NAME, "%" + lastName + "%")
                    .list();

            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public Optional<User> findUserByPhoneNumber(String phoneNumber) {

        Optional<User> user = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();

            user = session.createQuery("FROM User u WHERE u.phoneNumber = :phone_number", User.class)
                            .setParameter(ColumnName.PHONE_NUMBER, phoneNumber)
                                    .uniqueResultOptional();

            session.getTransaction().commit();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findUsersByRole(Role role) {
        List<User> users = new ArrayList<>();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            users = session.createQuery("FROM User u WHERE u.role = :role", User.class)
                    .setParameter(USER_ROLE, role)
                    .list();

            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        Optional<User> user = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            user = session.createQuery("FROM User u WHERE u.phoneNumber = :phone_number", User.class)
                    .setParameter(PHONE_NUMBER, phoneNumber)
                    .uniqueResultOptional();

            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public Optional<String> findPasswordByEmail(String email) {

        Optional<String> password = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            password = session.createQuery("SELECT u.password FROM User u WHERE u.email = :email", String.class)
                    .setParameter(EMAIL, email)
                    .uniqueResultOptional();

            session.getTransaction().commit();
        }

        return password;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {

        Optional<User> user = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            user = session.createQuery("FROM User u WHERE u.email = :email", User.class)
                    .setParameter(EMAIL, email)
                    .uniqueResultOptional();

            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public boolean save(User user) {

        Cart cart = Cart.builder()
                .user(user)
                .build();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.save(user);
            session.save(cart);
            session.getTransaction().commit();
            return true;
        }

    }

    @Override
    public boolean changeUserRole(long id, Role role) {

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            user.setRole(role);
            session.getTransaction().commit();
            return true;
        }

    }

    @Override
    public boolean changePersonalInfo(User user) {

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public long findNumberOfRows() {
        Long numberOfRows = 0l;
        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();
            numberOfRows = (Long) session.createQuery("SELECT count (u.id) FROM User u").uniqueResult();
            session.getTransaction().commit();

        }
        return numberOfRows;
    }

    @Override
    public List<User> findUsersFromRow(int fromRow, int numberOfUsersInPage) {
        List<User> users = new ArrayList<User>();
        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            users = session.createQuery("FROM User", User.class)
                    .setFirstResult(fromRow)
                    .setMaxResults(numberOfUsersInPage)
                    .list();
            session.getTransaction().commit();

        }
        return users;
    }

    @Override
    public Optional<User> findByEmailAndLastName(String email, String lastName) {
        Optional<User> user = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            user = session.createQuery("FROM User u WHERE u.email = :email AND u.lastName = :last_name", User.class)
                    .setParameter(EMAIL, email)
                    .setParameter(LAST_NAME, lastName)
                    .uniqueResultOptional();

            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public Optional<User> findByPhoneNumberAndLastName(String phoneNumber, String lastName) {
        Optional<User> user = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            user = session.createQuery("FROM User u WHERE u.phoneNumber = :phone_number AND u.lastName = :last_name", User.class)
                    .setParameter(PHONE_NUMBER, phoneNumber)
                    .setParameter(LAST_NAME, lastName)
                    .uniqueResultOptional();

            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public Optional<User> findByPhoneNumberAndEmail(String phoneNumber, String email) {
        Optional<User> user = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            user = session.createQuery("FROM User u WHERE u.phoneNumber = :phone_number AND u.email = :email", User.class)
                    .setParameter(PHONE_NUMBER, phoneNumber)
                    .setParameter(EMAIL, email)
                    .uniqueResultOptional();

            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public Optional<User> findByAllParameters(String email, String phoneNumber, String lastName) {
        Optional<User> user = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            user = session.createQuery("FROM User u WHERE u.phoneNumber = :phone_number AND u.email = :email AND u.lastName = :last_name", User.class)
                    .setParameter(PHONE_NUMBER, phoneNumber)
                    .setParameter(EMAIL, email)
                    .setParameter(LAST_NAME, lastName)
                    .uniqueResultOptional();

            session.getTransaction().commit();
        }
        return user;
    }
}
