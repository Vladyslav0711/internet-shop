package org.example.internetshop.controllers.order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Order;
import org.example.internetshop.model.Product;
import org.example.internetshop.service.OrderService;

public class GetOrderInfoController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("org.example.internetshop");
    private OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("order_id");
        Order order = orderService.get(Long.valueOf(orderId));
        List<Product> products = orderService.get(Long.valueOf(orderId)).getProducts();
        req.setAttribute("order", order);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/orders/info.jsp").forward(req, resp);
    }
}
