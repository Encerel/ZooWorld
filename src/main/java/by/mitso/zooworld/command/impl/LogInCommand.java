package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.UserService;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LogInCommand implements Command {

    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user;
        String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
        String password = request.getParameter(ParameterAndAttribute.USER_PASSWORD);
        Optional<User> optionalUser;
        try {
            optionalUser = userService.findUserByEmailPassword(email, password);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                router.setPagePath(PagePath.TO_MAIN_PAGE);
                session.setAttribute(ParameterAndAttribute.USER, user);
            }
        } catch (ServiceException e) {
            router.setPagePath(PagePath.SIGN_IN);
            request.setAttribute(ParameterAndAttribute.MESSAGE, Message.INCORRECT_EMAIL_OR_LOGIN);
        }
        return router;
    }
}
