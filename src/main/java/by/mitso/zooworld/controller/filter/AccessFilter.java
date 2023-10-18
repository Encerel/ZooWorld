package by.mitso.zooworld.controller.filter;

import by.mitso.zooworld.command.PagePath;
import by.mitso.zooworld.command.ParameterAndAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "AccessFilter", dispatcherTypes = { DispatcherType.REQUEST,
        DispatcherType.FORWARD }, urlPatterns = "*.jsp")
public class AccessFilter implements Filter {
    private static final Set<String> ALLOWED_PATH_GUEST = new HashSet<>(
            Arrays.asList("/index.jsp", "/jsp/main.jsp",
                    "/jsp/signin.jsp", "/jsp/signup.jsp", "/jsp/error.jsp"));

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String pagePath = req.getServletPath();
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute(ParameterAndAttribute.USER) != null);
        boolean allowedPath = ALLOWED_PATH_GUEST.contains(pagePath);
        if (loggedIn || allowedPath) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + PagePath.TO_MAIN_PAGE);
        }
    }
}