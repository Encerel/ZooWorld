package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.Command;
import by.mitso.zooworld.command.PagePath;
import by.mitso.zooworld.command.ParameterAndAttribute;
import by.mitso.zooworld.command.Router;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.UserService;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class FindFilteredUsersCommand implements Command {

    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String lastName = request.getParameter(ParameterAndAttribute.USER_LAST_NAME);
        String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
        String phoneNumber = request.getParameter(ParameterAndAttribute.USER_PHONE_NUMBER);

        try {
            List<User> usersByAllParameters = userService.findByAllParameters(email, phoneNumber, lastName);
            request.setAttribute(ParameterAndAttribute.USERS, usersByAllParameters);
            router.setPagePath(PagePath.ADMIN);
        } catch (ServiceException e) {
            router.setPagePath(PagePath.ADMIN);
            request.setAttribute(ParameterAndAttribute.MESSAGE, e.getMessage());
        }

        session.removeAttribute(ParameterAndAttribute.NUMBER_OF_PAGES);
        return router;
    }
}
