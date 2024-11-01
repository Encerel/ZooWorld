package by.mitso.zooworld.model.dao.impl;

import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.entity.Product.Availability;
import by.mitso.zooworld.entity.Product.Type;
import by.mitso.zooworld.entity.Product.Category;
import by.mitso.zooworld.model.connection.HibernateSessionFactoryProvider;
import by.mitso.zooworld.model.dao.ColumnName;
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

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            products = session.createQuery("FROM Product", Product.class).list();

            session.getTransaction().commit();
        }
        return products;
    }

    @Override
    public Optional<Product> findById(long id) {

        Optional<Product> product = Optional.empty();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            product = session.createQuery("FROM Product p WHERE p.id = :id", Product.class)
                    .setParameter(PRODUCT_ID, id)
                    .uniqueResultOptional();

            session.getTransaction().commit();
        }

        return product;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products;

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            products = session.createQuery("FROM Product p WHERE p.name = :name", Product.class)
                    .setParameter(PRODUCT_NAME, name)
                    .list();

            session.getTransaction().commit();
        }

        return products;
    }

    @Override
    public boolean changeAvailability(long id, Availability availability) {

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            Product product = session.get(Product.class, id);
            product.setAvailability(availability);
            session.getTransaction().commit();
            return true;
        }
    }


    @Override
    public List<Product> findByAvailability(Availability availability) {

        List<Product> products = new ArrayList<>();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            products = session.createQuery("FROM Product p WHERE p.availability = :availability", Product.class)
                    .setParameter(PRODUCT_AVAILABILITY, availability)
                    .list();

            session.getTransaction().commit();
        }

        return products;
    }

    @Override
    public List<Product> findByType(Type type) {

        List<Product> products = new ArrayList<>();

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
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

        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            products = session.createQuery("FROM Product p WHERE p.category = :category", Product.class)
                    .setParameter(PRODUCT_CATEGORY, category)
                    .list();
        }
        return products;
    }

    @Override
    public List<Product> findUsersFromRow(int fromRow, int numberOfProductsInPage) {
        List<Product> products = new ArrayList<>();
        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();

            products = session.createQuery("FROM Product", Product.class)
                    .setFirstResult(fromRow)
                    .setMaxResults(numberOfProductsInPage)
                    .list();
            session.getTransaction().commit();

        }
        return products;
    }

    @Override
    public long findNumberOfRows() {
        Long numberOfRows;
        try (Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();
            numberOfRows = (Long) session.createQuery("SELECT count (p.id) FROM Product p").uniqueResult();
            session.getTransaction().commit();

        }
        return numberOfRows;
    }

    @Override
    public List<Product> findBestSalesProducts() {

        List<Product> bestSalesProducts;

        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {

            session.beginTransaction();

            bestSalesProducts = session.createQuery("select p " +
                    "from OrderItem oi join oi.product p on oi.product.id = p.id " +
                    "group by p " +
                    "order by count(oi.product.id) desc", Product.class)
                    .setMaxResults(6)
                    .list();
            session.getTransaction();

        }
        return bestSalesProducts;
    }

    @Override
    public List<Product> findSimilarProducts(Product product) {

        List<Product> similarProducts;

        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            session.beginTransaction();
            similarProducts = session.createQuery("FROM Product p WHERE p.category = :category AND p.type = :type AND p.id != :id")
                    .setParameter(PRODUCT_CATEGORY, product.getCategory())
                    .setParameter(PRODUCT_TYPE, product.getType())
                    .setParameter(PRODUCT_ID, product.getId())
                    .setMaxResults(5)
                    .list();
            session.getTransaction().commit();
        }
        return similarProducts;
    }
}

