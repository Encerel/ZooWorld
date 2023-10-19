package by.mitso.zooworld.model.service.impl;

import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.entity.User.Role;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.UserDao;
import by.mitso.zooworld.model.service.UserService;
import by.mitso.zooworld.util.Encoder;
import by.mitso.zooworld.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.mitso.zooworld.command.Message.*;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findById(long id) throws ServiceException {

        Optional<User> user = userDao.findById(id);

        if (user.isEmpty()) {
            throw new ServiceException(NO_USER_WITH_ID + id);
        }
        return user;
    }

    @Override
    public List<User> findUsersByFirstName(String firstName) throws ServiceException {

        List<User> users = new ArrayList<>();

        if (UserValidator.isValidName(firstName)) {
            users = userDao.findUsersByFirstName(firstName);
            return users;
        }

        throw new ServiceException(NO_USERS_WITH_FIRST_NAME + firstName);
    }

    @Override
    public List<User> findUsersByLastName(String lastName) throws ServiceException {
        List<User> users = new ArrayList<>();

        if (UserValidator.isValidName(lastName)) {
            users = userDao.findUsersByLastName(lastName);
            return users;
        }

        throw new ServiceException(NO_USERS_WITH_LAST_NAME + lastName);
    }

    @Override
    public List<User> findUsersByRole(Role role) throws ServiceException {

        List<User> users = userDao.findUsersByRole(role);
        if (users.isEmpty()) {
            throw new ServiceException("No users found with role = " + role.name());
        }

        return users;

    }

    @Override
    public Optional<User> findUserByEmailPassword(String email, String password) throws ServiceException {


        Optional<User> user = Optional.empty();

        if (UserValidator.isValidEmail(email)) {
            String encodedPassword = Encoder.encodePassword(password);

            Optional<String> passwordFromDB = userDao.findPasswordByEmail(email);

            if (passwordFromDB.isEmpty()) {
                throw new ServiceException(NO_REGISTERED_USER_WITH_EMAIL + email);
            }

            if (!encodedPassword.equals(passwordFromDB.get())) {
                throw new ServiceException(WRONG_PASSWORD);
            }

            user = userDao.findUserByEmail(email);
        }

        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {

        return userDao.findUserByEmail(email);
    }

    @Override
    public boolean save(User user) throws ServiceException {

        if (UserValidator.isValidUser(user)) {

            String encodedPassword = Encoder.encodePassword(user.getPassword());
            user.setPassword(encodedPassword);
            return userDao.save(user);
        }

        throw new ServiceException(WRONG_USER_DATA);
    }

    @Override
    public boolean changeUserRole(long id, Role role) throws ServiceException {

        Optional<User> user = userDao.findById(id);

        if (user.isEmpty()) {
            throw new ServiceException(NO_USER_WITH_ID + id);
        }

        return userDao.changeUserRole(id, role);
    }

    @Override
    public boolean changePersonalInfo(User user) throws ServiceException {

        if (UserValidator.isValidUser(user)) {

            String encodedPassword = Encoder.encodePassword(user.getPassword());
            user.setPassword(encodedPassword);
            return userDao.changePersonalInfo(user);
        }

        throw new ServiceException(WRONG_USER_DATA);
    }
}
