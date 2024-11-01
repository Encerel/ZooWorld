package by.mitso.zooworld.model.service.impl;

import by.mitso.zooworld.command.Message;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.entity.User.Role;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.ColumnName;
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

    private final int numberOfUsersInPage = 9;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findById(long id)  {
        return userDao.findById(id);
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
    public Optional<User> findUserByPhoneNumber(String phoneNumber) {
        return userDao.findUserByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<User> findUserByEmailAndFirstName(String email, String firstName) {
        return userDao.findByEmailAndLastName(email, firstName);
    }

    @Override
    public Optional<User> findUserByPhoneNumberAndLastName(String phoneNumber, String lastName) {
        return userDao.findByPhoneNumberAndLastName(phoneNumber, lastName);
    }

    @Override
    public List<User> findByAllParameters(String email, String phoneNumber, String lastName) throws ServiceException {

        if ((email == null || email.isBlank())
                && (phoneNumber == null || phoneNumber.isBlank())
                && !(lastName == null || lastName.isBlank())) {
           return userDao.findUsersByLastName(lastName);
        }

        if (!(email == null || email.isBlank())
                && (phoneNumber == null || phoneNumber.isBlank())
                && (lastName == null || lastName.isBlank())) {
            Optional<User> userByEmail = userDao.findUserByEmail(email);
            if (userByEmail.isPresent()) {
                return List.of(userByEmail.get());
            } else {
                throw new ServiceException(NO_REGISTERED_USER_WITH_EMAIL + email);
            }
        }

        if ((email == null || email.isBlank())
                && !(phoneNumber == null || phoneNumber.isBlank())
                && (lastName == null || lastName.isBlank())) {
            Optional<User> userByPhoneNumber = userDao.findByPhoneNumber(phoneNumber);
            if (userByPhoneNumber.isPresent()) {
                return List.of(userByPhoneNumber.get());
            } else {
                throw new ServiceException(NO_USERS_WITH_PHONE_NUMBER + phoneNumber);
            }
        }

        if ((email == null || email.isBlank()) && !(phoneNumber == null || phoneNumber.isBlank())) {
            Optional<User> userByPhoneNumberAndLastName = userDao.findByPhoneNumberAndLastName(phoneNumber, lastName);
            if (userByPhoneNumberAndLastName.isPresent()) {
                return List.of(userByPhoneNumberAndLastName.get());
            } else {
                throw new ServiceException(NO_USERS_WITH_PHONE_NUMBER + phoneNumber + OR + ColumnName.EMAIL + email);
            }
        }

        if (!(email == null || email.isBlank()) && (phoneNumber == null || phoneNumber.isBlank())) {
            Optional<User> userByEmailAndLastName = userDao.findByEmailAndLastName(email, lastName);
            if (userByEmailAndLastName.isPresent()) {
                return List.of(userByEmailAndLastName.get());
            } else {
                throw new ServiceException(NO_USERS_WITH_EMAIL + email + OR + NO_USER_WITH_LAST_NAME + lastName);
            }
        }

        Optional<User> userByAllParameters = userDao.findByAllParameters(email, phoneNumber, lastName);


        return List.of(userByAllParameters.orElseThrow(
                () -> new ServiceException(
                        NO_USERS_WITH_EMAIL + email + OR + NO_USERS_WITH_PHONE_NUMBER + phoneNumber + OR + NO_USERS_WITH_LAST_NAME + lastName
                )
        ));

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
            String passwordInDB = userDao.findPasswordByEmail(user.getEmail()).get();
            if (!passwordInDB.equals(user.getPassword())) {
                String encodedPassword = Encoder.encodePassword(user.getPassword());
                user.setPassword(encodedPassword);
            }
            return userDao.changePersonalInfo(user);
        }

        throw new ServiceException(WRONG_USER_DATA);
    }

    @Override
    public int findNumberOfPages() {
        int numberOfPages;
        long numberOfUser;
            numberOfUser = userDao.findNumberOfRows();
            if (numberOfUser > numberOfUsersInPage) {
                numberOfPages = (int) Math.ceil((double) numberOfUser / numberOfUsersInPage);
            } else {
                numberOfPages = 1;
            }

        return numberOfPages;
    }

    @Override
    public List<User> findUsersFromRow(int pageNumber) {
        List<User> users = new ArrayList<>();
        int fromRow;
        if (pageNumber > 1) {
            fromRow = (pageNumber - 1) * numberOfUsersInPage;
        } else {
            fromRow = 0;
        }
            users = userDao.findUsersFromRow(fromRow, numberOfUsersInPage);

        return users;
    }
}
