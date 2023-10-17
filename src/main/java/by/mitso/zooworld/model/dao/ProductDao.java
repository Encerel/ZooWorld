package by.mitso.zooworld.model.dao;

import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.entity.Product.Availability;
import by.mitso.zooworld.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> findAll();

    Optional<Product> findById(long id);

    Optional<Product> findByName(String name);

    boolean changeAvailability(long id, Availability availability) throws DaoException;

    List<Product> findByAvailability(Availability availability);
}
