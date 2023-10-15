package by.mitso.zooworld.model.connection;

import by.mitso.zooworld.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryProvider {
    private static final SessionFactory sessionFactory = buildSessionFactory();


    private static SessionFactory buildSessionFactory() {

        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Cart.class);
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(CartItem.class);
            configuration.addAnnotatedClass(OrderItem.class);
            return configuration.buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
