package org.example.internetshop.controller.order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.model.User;
import org.example.internetshop.service.OrderService;
import org.example.internetshop.service.ShoppingCartService;

public class CompleteOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("org.example.internetshop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("user_id");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        List<Product> products = shoppingCartService.getAllProducts(shoppingCart);
        User user = shoppingCart.getUser();
        orderService.completeOrder(products, user);
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
