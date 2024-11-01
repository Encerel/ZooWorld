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
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandFromPage = request.getParameter(ParameterAndAttribute.COMMAND);
        Command currentCommand = CommandProvider.defineCommand(commandFromPage);
        Router router = currentCommand.execute(request);

        switch (router.getType()) {
            case FORWARD -> {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(router.getPagePath());
                requestDispatcher.forward(request, response);
            }
            case REDIRECT -> {
                response.sendRedirect(router.getPagePath());
            }
            default -> {
                response.sendRedirect(PagePath.SIGN_IN);
            }
        }
    }
}
