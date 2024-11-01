package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.Command;
import by.mitso.zooworld.command.PagePath;
import by.mitso.zooworld.command.ParameterAndAttribute;
import by.mitso.zooworld.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;

public class ToSignInCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request)  {
        Router router = new Router();
        HttpSession session = request.getSession();
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_SIGN_IN_PAGE);
        router.setPagePath(PagePath.SIGN_IN);
        return router;
    }
}
