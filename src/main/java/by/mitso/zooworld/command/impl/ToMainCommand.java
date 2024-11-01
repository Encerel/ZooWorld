package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.Command;
import by.mitso.zooworld.command.PagePath;
import by.mitso.zooworld.command.ParameterAndAttribute;
import by.mitso.zooworld.command.Router;
import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.model.dao.impl.ProductDaoImpl;
import by.mitso.zooworld.model.service.ProductService;
import by.mitso.zooworld.model.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class ToMainCommand implements Command {

    private final ProductService productService = new ProductServiceImpl(new ProductDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        List<Product> bestSalesProducts = productService.findBestSalesProducts();
        request.setAttribute(ParameterAndAttribute.PRODUCTS, bestSalesProducts);
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_MAIN_PAGE);
        router.setPagePath(PagePath.MAIN);
        return router;
    }
}
