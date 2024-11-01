package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.UserService;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import by.mitso.zooworld.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;

public class ChangePersonalInfoCommand implements Command {

    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ParameterAndAttribute.USER);

        String firstName = request.getParameter(ParameterAndAttribute.USER_FIRST_NAME);
        String lastName = request.getParameter(ParameterAndAttribute.USER_LAST_NAME);
        String password = request.getParameter(ParameterAndAttribute.USER_PASSWORD);

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        try {
            if (UserValidator.isValidUser(user)) {
                userService.changePersonalInfo(user);
                router.setPagePath(PagePath.TO_MAIN_PAGE);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            router.setPagePath(PagePath.TO_SIGN_UP_PAGE);
            request.setAttribute(ParameterAndAttribute.MESSAGE, e.getMessage());
        }


        return router;
    }
}
