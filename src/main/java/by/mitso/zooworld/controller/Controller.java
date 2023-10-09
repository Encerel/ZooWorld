package by.mitso.zooworld.controller;


import by.mitso.zooworld.command.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandFromPage = request.getParameter(ParametersAndAttribute.COMMAND);
        Command currentCommand = CommandProvider.defineCommand(commandFromPage);
        Router router = currentCommand.execute(request);

        switch (router.getType()) {
            case FORWARD: {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(router.getPagePath());
                requestDispatcher.forward(request, response);
                break;
            }
            case REDIRECT: {
                response.sendRedirect(router.getPagePath());
                break;
            }
            default: {
                response.sendRedirect(PagePath.SIGN_IN);
            }
        }
    }
}
