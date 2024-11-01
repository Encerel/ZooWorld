package by.mitso.zooworld.model.dao;

import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.entity.Product.Type;
import by.mitso.zooworld.entity.Product.Category;
import by.mitso.zooworld.entity.Product.Availability;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> findAll();

    Optional<Product> findById(long id);

    List<Product> findByName(String name);

    boolean changeAvailability(long id, Availability availability);

    List<Product> findByAvailability(Availability availability);
    List<Product> findByType(Type type);
    List<Product> findByCategory(Category category);

    abstract List<Product> findUsersFromRow(int fromRow, int numberOfProductsInPage);

    long findNumberOfRows();

    List<Product> findBestSalesProducts();

    List<Product> findSimilarProducts(Product product);
}
