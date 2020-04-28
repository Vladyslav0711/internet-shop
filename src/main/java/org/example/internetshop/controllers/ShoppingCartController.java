package org.example.internetshop.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Product;
import org.example.internetshop.service.ShoppingCartService;

public class ShoppingCartController extends HttpServlet {
    public static final Long USER_ID = 1L;
    private static Injector injector = Injector.getInstance("org.example.internetshop");
    ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> products = shoppingCartService.getByUserId(USER_ID).getProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/cart/shoppingCart.jsp").forward(req, resp);
    }
}
