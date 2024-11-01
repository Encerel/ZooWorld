package by.mitso.zooworld.command.impl;

import by.mitso.zooworld.command.Command;
import by.mitso.zooworld.command.PagePath;
import by.mitso.zooworld.command.ParameterAndAttribute;
import by.mitso.zooworld.command.Router;
import by.mitso.zooworld.entity.Product;
import by.mitso.zooworld.exception.ServiceException;
import by.mitso.zooworld.model.dao.impl.ProductDaoImpl;
import by.mitso.zooworld.model.service.ProductService;
import by.mitso.zooworld.model.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

public class ToProductPageCommand implements Command {

    private final ProductService productService = new ProductServiceImpl(new ProductDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        List<Product> similarProducts;

        long productId = Long.parseLong(request.getParameter(ParameterAndAttribute.PRODUCT_ID));

        try {
            Optional<Product> product = productService.findById(productId);

            if (product.isPresent()) {
                similarProducts = productService.findSimilarProducts(product.get());
                request.setAttribute(ParameterAndAttribute.PRODUCT, product.get());
                request.setAttribute(ParameterAndAttribute.PRODUCTS, similarProducts);
                session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_PRODUCT_PAGE);
                router.setPagePath(PagePath.PRODUCT);
            }
        } catch (ServiceException e) {
            router.setPagePath(PagePath.ERROR);
            router.setType(Router.Type.REDIRECT);
        }

        return router;
    }
}
