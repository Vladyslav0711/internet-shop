package org.example.internetshop.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Product;
import org.example.internetshop.service.ProductService;

public class ProductController extends HttpServlet {
    private static Injector injector = Injector.getInstance("org.example.internetshop");
    ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> products = productService.getAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/products/allProducts.jsp").forward(req, resp);
    }
}
