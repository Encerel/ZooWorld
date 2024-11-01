package by.mitso.zooworld.model.service;

import by.mitso.zooworld.entity.Cart;
import by.mitso.zooworld.entity.CartItem;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CartService {

    List<Cart> findAll();

    Optional<Cart> findById(long id);

    Optional<Cart> findByUser(User user) throws ServiceException;

    boolean addProductToCart(Cart cart, CartItem item);

    boolean updateExistedCartItem(CartItem oldItem, CartItem newItem);

    boolean deleteCartItem(Cart cart, CartItem item) throws ServiceException;

    boolean clear(Cart cart);

    boolean clear(List<CartItem> items);
}
