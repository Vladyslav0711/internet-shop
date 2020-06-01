package org.example.internetshop.controller.cart;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Product;
import org.example.internetshop.service.ShoppingCartService;

public class GetShoppingCartController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("org.example.internetshop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("user_id");
        List<Product> products = shoppingCartService.getByUserId(userId).getProducts();
        req.setAttribute("products", products);
        if (products.size() == 0) {
            req.setAttribute("message", "Your cart is empty.");
        }
        req.getRequestDispatcher("/WEB-INF/views/cart/shoppingCart.jsp").forward(req, resp);
    }
}
