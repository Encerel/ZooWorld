package by.mitso.zooworld.model.dao.impl;

import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.entity.User.Role;
import by.mitso.zooworld.model.connection.HibernateSessionFactoryProvider;
import by.mitso.zooworld.model.dao.UserDao;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.mitso.zooworld.model.ColumnName.*;

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
    public List<User> findUsersByFirstName(String firstName) {

        List<User> users = new ArrayList<>();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            users = session.createQuery("FROM User u WHERE u.firstName = :first_name", User.class)
                    .setParameter(FIRST_NAME, firstName)
                    .list();

            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public List<User> findUsersLastName(String lastName) {

        List<User> users = new ArrayList<>();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            users = session.createQuery("FROM User u WHERE u.lastName = :last_name", User.class)
                    .setParameter(LAST_NAME, lastName)
                    .list();

            session.getTransaction().commit();
        }
        return users;
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

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        }

    }

    @Override
    public boolean changeUserRole(long id, Role role) {

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.createQuery("UPDATE User u SET u.role = :role WHERE u.id = :id")
                    .setParameter(USER_ID, id)
                    .setParameter(USER_ROLE, role);
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
}
