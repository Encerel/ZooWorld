package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.*;
import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.model.dao.impl.ProductDaoImpl;
import by.mitso.zooworld.model.service.ProductService;
import by.mitso.zooworld.model.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class FindProductsPaginationCommand implements Command {

    private final ProductService productService = new ProductServiceImpl(new ProductDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        List<Product> products;
        Router router = new Router();
        int pageNumber = Integer.parseInt(request.getParameter(ParameterAndAttribute.PRODUCTS_START_FROM));
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        products = productService.findProductsFromRow(pageNumber);
        router.setPagePath(PagePath.TO_PRODUCTS_PAGE);
        session.setAttribute(ParameterAndAttribute.PRODUCTS_START_FROM, pageNumber);
        request.setAttribute(ParameterAndAttribute.PRODUCTS, products);
        request.setAttribute(ParameterAndAttribute.MESSAGE, Message.SUCCESSFUL);
        return router;
    }
}
