package org.example.internetshop.controllers.order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Order;
import org.example.internetshop.model.User;
import org.example.internetshop.service.OrderService;
import org.example.internetshop.service.UserService;

public class GetUserOrdersController extends HttpServlet {
    public static final Long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("org.example.internetshop");
    private OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = userService.get(USER_ID);
        List<Order> orders = orderService.getUserOrders(user);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/views/orders/all.jsp").forward(req, resp);
    }
}
