package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.Command;
import by.mitso.zooworld.command.Message;
import by.mitso.zooworld.command.ParameterAndAttribute;
import by.mitso.zooworld.command.Router;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.UserService;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class FindAllUsersCommand implements Command {

    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {

        int numberOfPages;
        List<User> users;
        Router router = new Router();
        HttpSession session = request.getSession();

        int startRow;
        if (session.getAttribute(ParameterAndAttribute.USERS_START_FROM) != null) {
            startRow = (int) session.getAttribute(ParameterAndAttribute.USERS_START_FROM);
        } else {
            startRow = 0;
        }
        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        users = userService.findUsersFromRow(startRow);
        numberOfPages = userService.findNumberOfPages();
        router.setPagePath(page);
        request.setAttribute(ParameterAndAttribute.USERS, users);
        session.setAttribute(ParameterAndAttribute.NUMBER_OF_PAGES, numberOfPages);
        session.setAttribute(ParameterAndAttribute.MESSAGE, Message.SUCCESSFUL);
        return router;
    }
}
