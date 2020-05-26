package org.example.internetshop.controller.product;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.service.ProductService;

public class DeleteProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("org.example.internetshop");
    private ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String productId = req.getParameter("product_id");
        productService.delete(Long.valueOf(productId));
        resp.sendRedirect(req.getContextPath() + "/products/edit");
    }
}
