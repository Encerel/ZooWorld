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

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class ToFilteredProductsCommand implements Command {

    private final ProductService productService = new ProductServiceImpl(new ProductDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        List<Product> filteredProducts;
        String[] categories;
        String[] types;
        double startPrice;
        double endPrice;

        try {
            startPrice = Double.parseDouble(request.getParameter(ParameterAndAttribute.START_PRICE));
        } catch (NumberFormatException e) {
            startPrice = (double) session.getAttribute(ParameterAndAttribute.PRODUCT_MIN_PRICE);
        }


        try {
            endPrice = Double.parseDouble(request.getParameter(ParameterAndAttribute.END_PRICE));
        } catch (NumberFormatException e) {
            endPrice = (double) session.getAttribute(ParameterAndAttribute.PRODUCT_MAX_PRICE);
        }


        try {
            categories = request.getParameterValues(ParameterAndAttribute.CATEGORY);
            String category = categories[0];
        } catch (NullPointerException e) {
            categories = new String[]{"CATS", "DOGS", "BIRDS", "TURTLES"};
        }

        try {
            types = request.getParameterValues(ParameterAndAttribute.TYPE);
            String type = types[0];
        } catch (NullPointerException e) {
            types = new String[]{"ACCESSORIES", "FOOD", "TOY"};
        }

        filteredProducts = productService.filterByAllParameters(startPrice, endPrice, List.of(categories), List.of(types));
        request.setAttribute(ParameterAndAttribute.PRODUCTS, filteredProducts);
        router.setPagePath(PagePath.FILTERED_PRODUCTS);
        return router;
    }
}
