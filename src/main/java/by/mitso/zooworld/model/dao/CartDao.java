package by.mitso.zooworld.model.dao;

import by.mitso.zooworld.entity.Cart;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface CartDao {

    List<Cart> findAll();

    Optional<Cart> findById(long id);

    Optional<Cart> findByUser(User user) throws DaoException;
}
