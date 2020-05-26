package org.example.internetshop.controller.cart;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.service.ProductService;
import org.example.internetshop.service.ShoppingCartService;

public class AddProductToCartController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("org.example.internetshop");
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("user_id");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        String productId = req.getParameter("product_id");
        shoppingCartService.addProduct(shoppingCart, productService.get(Long.valueOf(productId)));
        resp.sendRedirect(req.getContextPath() + "/products/all");
    }
}
