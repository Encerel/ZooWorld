package by.mitso.zooworld.model.service.impl;

import by.mitso.zooworld.entity.Cart;
import by.mitso.zooworld.entity.CartItem;
import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.CartDao;
import by.mitso.zooworld.model.service.CartService;
import by.mitso.zooworld.model.service.ProductService;
import by.mitso.zooworld.model.service.UserService;

import java.util.List;
import java.util.Optional;

public class CartServiceImpl implements CartService {

    private final CartDao cartDao;
    private final UserService userService;

    private final ProductService productService;

    public CartServiceImpl(CartDao cartDao, UserService userService, ProductService productService) {
        this.cartDao = cartDao;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public List<Cart> findAll() {
        return cartDao.findAll();
    }

    @Override
    public Optional<Cart> findById(long id) {
        return cartDao.findById(id);
    }

    @Override
    public Optional<Cart> findByUser(User user) throws ServiceException {

        Optional<User> userFromDB = userService.findById(user.getId());

        return cartDao.findByUser(userFromDB.get());
    }

    @Override
    public List<CartItem> findAllCartItems(Cart cart) {
        return cartDao.findAllCartItems(cart);
    }

    @Override
    public boolean addProductToCart(Cart cart, CartItem item) {
        return cartDao.addProductToCart(cart, item);
    }

    @Override
    public boolean deleteCartItem(Cart cart, CartItem item) throws ServiceException {

        Optional<Cart> cartFromDB = cartDao.findById(cart.getId());
        Optional<Product> productFromDB = productService.findById(item.getProduct().getId());

        if (cartFromDB.isEmpty()) {
            throw new ServiceException("No cart with id = " + cart.getId());
        }

        if (productFromDB.isEmpty()) {
            throw new ServiceException("No product with id = " + item.getProduct().getId());
        }

        return cartDao.deleteCartItem(cart, item);
    }
}
