package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.UserService;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import by.mitso.zooworld.util.Encoder;
import by.mitso.zooworld.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class SignUpCommand implements Command {

    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        String firstName = request.getParameter(ParameterAndAttribute.USER_FIRST_NAME);
        String lastName = request.getParameter(ParameterAndAttribute.USER_LAST_NAME);
        String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
        String phone = request.getParameter(ParameterAndAttribute.USER_PHONE_NUMBER);
        String password = request.getParameter(ParameterAndAttribute.USER_PASSWORD);
        String confirmedPassword = request.getParameter(ParameterAndAttribute.USER_CONFIRMED_PASSWORD);


        if (password.equals(confirmedPassword)) {

            Optional<User> userFromDB = userService.findUserByEmail(email);

            if (userFromDB.isEmpty()) {
                User user = User.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .role(User.Role.USER)
                        .createdAt(new Date())
                        .phoneNumber(phone)
                        .password(password)
                        .build();
                try {
                    userService.save(user);
                    router.setPagePath(PagePath.TO_MAIN_PAGE);
                    router.setType(Router.Type.REDIRECT);
                } catch (ServiceException e) {
                    session.setAttribute(ParameterAndAttribute.MESSAGE, e.getMessage());
                    router.setPagePath(PagePath.TO_SIGN_UP_PAGE);
                    return router;
                }
            }  else {
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.USER_ALREADY_EXISTS);
                router.setPagePath(PagePath.TO_SIGN_UP_PAGE);
                return router;
            }

        } else {
            session.setAttribute(ParameterAndAttribute.MESSAGE, Message.PASSWORDS_MISMATCH);
            router.setPagePath(PagePath.TO_SIGN_UP_PAGE);
            return router;
        }


        return router;
    }


}


