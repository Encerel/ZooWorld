package by.mitso.zooworld.validator;

import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;

import static by.mitso.zooworld.command.Message.*;

public class UserValidator {

    private final static String EMAIL_REGEX = "\\w+@\\p{Alpha}+\\.\\p{Alpha}{2,}";
    private final static String NAME_REGEX = "[\\p{Alpha}А-Яа-яA-Za-z\\s-]{1,15}";
    private final static String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-zA-Zа-яА-Я]).{8,}$";

    private final static String PHONE_NUMBER_REGEX = "^\\+375\\d{9}$";

    public static boolean isValidName(String name) throws ServiceException {
        if (name.matches(NAME_REGEX)) {
            return true;
        }
        throw new ServiceException(WRONG_FIRST_OR_LAST_NAME_FORMAT);
    }


    public static boolean isValidEmail(String email) throws ServiceException {
        if (email.matches(EMAIL_REGEX)) {
            return true;
        }
        throw new ServiceException(WRONG_EMAIL_FORMAT);
    }

    public static boolean isValidPassword(String password) throws ServiceException {
        if (password.matches(PASSWORD_REGEX)) {
            return true;
        }
        throw new ServiceException(WRONG_PASSWORD_FORMAT);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) throws ServiceException {
        if (phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            return true;
        }
        throw new ServiceException(WRONG_PHONE_NUMBER_FORMAT);
    }

    public static boolean isValidUser(User user) throws ServiceException {

        return isValidName(user.getFirstName()) &&
                isValidName(user.getLastName()) &&
                isValidPassword(user.getPassword()) &&
                isValidPhoneNumber(user.getPhoneNumber()) &&
                isValidEmail(user.getEmail());
    }

}
