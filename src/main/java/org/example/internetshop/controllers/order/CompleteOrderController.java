package org.example.internetshop.controllers.order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.model.User;
import org.example.internetshop.service.OrderService;
import org.example.internetshop.service.ShoppingCartService;
import org.example.internetshop.service.UserService;

public class CompleteOrderController extends HttpServlet {
    public static final Long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("org.example.internetshop");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID);
        List<Product> products = shoppingCartService.getAllProducts(shoppingCart);
        User user = userService.get(USER_ID);
        orderService.completeOrder(products, user);
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
