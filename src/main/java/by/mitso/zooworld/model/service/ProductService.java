package by.mitso.zooworld.model.service;

import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.entity.Product.*;
import by.mitso.zooworld.exception.DaoException;
import by.mitso.zooworld.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(long id) throws ServiceException;
    List<Product> findByName(String name);
    boolean changeAvailability(long id, Product.Availability availability) throws DaoException, ServiceException;
    List<Product> findByAvailability(Product.Availability availability);
    List<Product> findByType(Type type);
    List<Product> findByCategory(Category category);

    List<Product> findSimilarProducts(Product product);
    List<Product> findBestSalesProducts();

    List<Product> filterByAllParameters(
            double startPrice,
            double endPrice,
            List<String> categories,
            List<String> types
    );

    List<Product> findProductsFromRow(int startRow);
    long findNumberOfPages();
}
