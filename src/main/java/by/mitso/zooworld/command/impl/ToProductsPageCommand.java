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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ToProductsPageCommand implements Command {

    private final ProductService productService = new ProductServiceImpl(new ProductDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        long numberOfPages;
        Router router = new Router();
        HttpSession session = request.getSession();

        int startRow;
        if (session.getAttribute(ParameterAndAttribute.PRODUCTS_START_FROM) != null) {
            startRow = (int) session.getAttribute(ParameterAndAttribute.PRODUCTS_START_FROM);
        } else {
            startRow = 0;
        }
        List<Product> products = productService.findProductsFromRow(startRow);
        numberOfPages = productService.findNumberOfPages();
        request.setAttribute(ParameterAndAttribute.PRODUCTS, products);
        if (session.getAttribute(ParameterAndAttribute.PRODUCT_MIN_PRICE  ) == null ||
                session.getAttribute(ParameterAndAttribute.PRODUCT_MAX_PRICE) == null) {
            session.setAttribute(ParameterAndAttribute.PRODUCT_MIN_PRICE, findMinPriceOfProducts(products));
            session.setAttribute(ParameterAndAttribute.PRODUCT_MAX_PRICE, findMaxPriceOfProducts(products));
        }
        session.setAttribute(ParameterAndAttribute.NUMBER_OF_PAGES, numberOfPages);
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_PRODUCTS_PAGE);
        router.setPagePath(PagePath.PRODUCTS);
        return router;
    }

    private double findMinPriceOfProducts(List<Product> products) {
        Product minPriceProduct = Collections.min(products, Comparator.comparingDouble(Product::getPrice));
        return minPriceProduct.getPrice();
    }

    private double findMaxPriceOfProducts(List<Product> products) {
        Product minPriceProduct = Collections.max(products, Comparator.comparingDouble(Product::getPrice));
        return minPriceProduct.getPrice();
    }
}
