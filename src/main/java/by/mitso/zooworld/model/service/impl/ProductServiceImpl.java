package by.mitso.zooworld.model.service.impl;

import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.entity.Product.*;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.ProductDao;
import by.mitso.zooworld.model.service.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final int numberOfProductsInPage = 9;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Optional<Product> findById(long id) throws ServiceException {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findByName(String name) {
        Pattern pattern = Pattern.compile(".*" + name.toLowerCase() + ".*");
        List<Product> products = productDao.findAll();
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            Matcher matcher = pattern.matcher(product.getName().toLowerCase());
            if (matcher.matches()) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    @Override
    public boolean changeAvailability(long id, Availability availability) throws ServiceException {

        Optional<Product> product = productDao.findById(id);

        if (product.isEmpty()) {
            throw new ServiceException("No user with id = " + id);
        }

        return productDao.changeAvailability(id, availability);
    }

    @Override
    public List<Product> findByAvailability(Availability availability) {
        return productDao.findByAvailability(availability);
    }

    @Override
    public List<Product> findByType(Type type) {
        return productDao.findByType(type);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productDao.findByCategory(category);
    }

    @Override
    public List<Product> findSimilarProducts(Product product) {
        return productDao.findSimilarProducts(product);
    }

    @Override
    public List<Product> findBestSalesProducts() {
        return productDao.findBestSalesProducts();
    }

    @Override
    public List<Product> filterByAllParameters(
            double startPrice,
            double endPrice,
            List<String> categories,
            List<String> types
    ) {
        List<Product> products = productDao.findAll();
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {

            if (!(product.getPrice() >= startPrice && product.getPrice() <= endPrice)) {
                continue;
            }

            if (types.contains(product.getType().name()) && categories.contains(product.getCategory().name())) {
                filteredProducts.add(product);
            }

        }


        return filteredProducts;
    }

    @Override
    public List<Product> findProductsFromRow(int pageNumber) {
        List<Product> products = new ArrayList<>();
        int fromRow;
        if (pageNumber > 1) {
            fromRow = (pageNumber - 1) * numberOfProductsInPage;
        } else {
            fromRow = 0;
        }
        products = productDao.findUsersFromRow(fromRow, numberOfProductsInPage);

        return products;
    }

    @Override
    public long findNumberOfPages() {
        long numberOfPages;
        long numberOfProducts;
        numberOfProducts = productDao.findNumberOfRows();
        if (numberOfProducts > numberOfProductsInPage) {
            numberOfPages = (int) Math.ceil((double) numberOfProducts / numberOfProductsInPage);
        } else {
            numberOfPages = 1;
        }

        return numberOfPages;
    }
}
