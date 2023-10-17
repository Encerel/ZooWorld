package by.mitso.zooworld.validator;

import by.mitso.zooworld.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private final static String EMAIL_REGEX = "\\w+@\\p{Alpha}+\\.\\p{Alpha}{2,}";
    private final static String NAME_REGEX = "[\\p{Alpha}А-Яа-я\\s-]{1,15}";
    private final static String PASSWORD_REGEX = "[a-zA-Z\\d]{1,15}";


    public static boolean isValidName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        return name.matches(NAME_REGEX);
    }


    public static boolean isValidEmail(String email) {
        boolean isValid = true;
        if (!email.isBlank()) {
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = pattern.matcher(email);
            isValid = matcher.matches();
        } else {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }
        return password.matches(PASSWORD_REGEX);
    }

}
