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

import java.util.List;


public class FindProductByNameCommand implements Command {

    private final ProductService productService = new ProductServiceImpl(new ProductDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String partOfProductName;
        List<Product> filteredProducts;
        try {
            partOfProductName =  request.getParameter(ParameterAndAttribute.PRODUCT_NAME);
            filteredProducts = productService.findByName(partOfProductName);
            request.setAttribute(ParameterAndAttribute.PRODUCTS, filteredProducts);
            router.setPagePath(PagePath.FILTERED_PRODUCTS);
        } catch (NullPointerException e) {
            router.setPagePath(PagePath.TO_PRODUCTS_PAGE);
        }

        return router;
    }
}
