package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.Command;
import by.mitso.zooworld.command.PagePath;
import by.mitso.zooworld.command.ParameterAndAttribute;
import by.mitso.zooworld.command.Router;
import by.mitso.zooworld.entity.User;
import by.mitso.zooworld.model.dao.impl.UserDaoImpl;
import by.mitso.zooworld.model.service.UserService;
import by.mitso.zooworld.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class ToPersonalPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterAndAttribute.USER);
        if (user != null) {
            switch (user.getRole()) {
                case ADMIN:
                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_PERSONAL_PAGE);
                    router.setPagePath(PagePath.ADMIN);
                    break;
                case GUEST:
                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_SIGN_IN_PAGE);
                    router.setPagePath(PagePath.SIGN_IN);
                    break;
                case USER:
                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_PERSONAL_PAGE);
                    router.setPagePath(PagePath.USER);
                    break;
            }
        } else {
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_SIGN_IN_PAGE);
            router.setPagePath(PagePath.SIGN_IN);
        }
        return router;
    }

}
