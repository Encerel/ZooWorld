package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.UserService;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class FindUsersPaginationCommand implements Command {

    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        List<User> users;
        Router router = new Router();
        int pageNumber = Integer.parseInt(request.getParameter(ParameterAndAttribute.USERS_START_FROM));
        HttpSession session = request.getSession();
//        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        users = userService.findUsersFromRow(pageNumber);
        router.setPagePath(PagePath.ADMIN);
        request.setAttribute(ParameterAndAttribute.USERS, users);
        session.setAttribute(ParameterAndAttribute.MESSAGE, Message.SUCCESSFUL);
        return router;
    }
}
