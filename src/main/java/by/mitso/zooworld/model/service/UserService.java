package by.mitso.zooworld.model.service;

import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(long id) throws ServiceException;

    List<User> findUsersByFirstName(String firstName) throws ServiceException;

    List<User> findUsersByLastName(String lastName) throws ServiceException;

    List<User> findUsersByRole(User.Role role) throws ServiceException;

    Optional<User> findUserByEmailPassword(String email, String password) throws ServiceException;

    Optional<User> findUserByEmail(String email) throws ServiceException;

    boolean save(User user) throws ServiceException;

    boolean changeUserRole(long id, User.Role role) throws ServiceException;

    boolean changePersonalInfo(User user) throws ServiceException;

}
