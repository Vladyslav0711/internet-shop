package org.example.internetshop.controller.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.service.ProductService;
import org.example.internetshop.service.ShoppingCartService;

public class DeleteProductFromCartController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("org.example.internetshop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String productId = req.getParameter("product_id");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID);
        Product product = productService.get(Long.valueOf(productId));
        shoppingCartService.deleteProduct(shoppingCart, product);
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
