package by.mitso.zooworld.model.service.impl;

import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.entity.Product.*;
import by.mitso.zooworld.exception.DaoException;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.ProductDao;
import by.mitso.zooworld.model.service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

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
    public Optional<Product> findByName(String name) {
        return productDao.findByName(name);
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
}
