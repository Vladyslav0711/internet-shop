package org.example.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.service.ProductService;
import org.example.internetshop.service.ShoppingCartService;
import org.example.internetshop.service.UserService;

public class AddProductToCartController extends HttpServlet {
    public static final Long USER_ID = 1L;
    private static Injector injector = Injector.getInstance("org.example.internetshop");
    UserService userService =
            (UserService) injector.getInstance(UserService.class);
    ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID);
        String productId = req.getParameter("product_id");
        shoppingCartService.addProduct(shoppingCart, productService.get(Long.valueOf(productId)));
        resp.sendRedirect(req.getContextPath() + "/products/all");
    }
}
