package org.example.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Product;
import org.example.internetshop.service.ProductService;

public class AddProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("org.example.internetshop");
    ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        Product product = productService.create(new Product(name, Double.valueOf(price)));
        if (product != null) {
            req.setAttribute("message", "Successfully added");
        } else {
            req.setAttribute("message", "something was wrong");
        }
        this.doGet(req, resp);
    }
}
