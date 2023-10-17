package by.mitso.zooworld.model.dao.impl;

import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.entity.Product.Availability;
import by.mitso.zooworld.entity.Product.Type;
import by.mitso.zooworld.entity.Product.Category;
import by.mitso.zooworld.exception.DaoException;
import by.mitso.zooworld.model.connection.HibernateSessionFactoryProvider;
import by.mitso.zooworld.model.dao.ProductDao;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.mitso.zooworld.model.dao.ColumnName.*;

public class ProductDaoImpl implements ProductDao {

    @Override
    public List<Product> findAll() {

        List<Product> products = new ArrayList<>();

        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            products = session.createQuery("FROM Product", Product.class).list();

            session.getTransaction().commit();
        }
        return products;
    }

    @Override
    public Optional<Product> findById(long id) {

        Optional<Product> product = Optional.empty();

        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            product = session.createQuery("FROM Product p WHERE p.id = :id" , Product.class)
                    .setParameter(PRODUCT_ID, id)
                    .uniqueResultOptional();

            session.getTransaction().commit();
        }

        return product;
    }

    @Override
    public Optional<Product> findByName(String name) {
        Optional<Product> product = Optional.empty();

        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            product = session.createQuery("FROM Product p WHERE p.name = :name" , Product.class)
                    .setParameter(PRODUCT_NAME, name)
                    .uniqueResultOptional();

            session.getTransaction().commit();
        }

        return product;
    }

    @Override
    public boolean changeAvailability(long id, Availability availability) throws DaoException {

        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            Product product = session.get(Product.class, id);

            if (product != null) {
                product.setAvailability(availability);
                session.getTransaction().commit();
                return true;
            }
        }
        throw new DaoException("No products with id = " + id);
    }

    @Override
    public List<Product> findByAvailability(Availability availability) {

        List<Product> products = new ArrayList<>();

        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            products = session.createQuery("FROM Product p WHERE p.availability = :availability" , Product.class)
                    .setParameter(PRODUCT_AVAILABILITY, availability)
                    .list();

            session.getTransaction().commit();
        }

        return products;
    }

    @Override
    public List<Product> findByType(Type type) {

        List<Product> products = new ArrayList<>();

        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            products = session.createQuery("FROM Product p WHERE p.type = :type", Product.class)
                    .setParameter(PRODUCT_TYPE, type)
                    .list();
        }
        return products;
    }

    @Override
    public List<Product> findByCategory(Category category) {

        List<Product> products = new ArrayList<>();

        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            products = session.createQuery("FROM Product p WHERE p.category = :category", Product.class)
                    .setParameter(PRODUCT_CATEGORY, category)
                    .list();
        }
        return products;
    }
}
