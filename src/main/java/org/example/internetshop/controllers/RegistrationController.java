package org.example.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.model.User;
import org.example.internetshop.service.ShoppingCartService;
import org.example.internetshop.service.UserService;

public class RegistrationController extends HttpServlet {
    private static Injector injector = Injector.getInstance("org.example.internetshop");
    UserService userService =
            (UserService) injector.getInstance(UserService.class);
    ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/users/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");
        if (password.equals(repeatPassword)) {
            User user = userService.create(new User(name, surname, login, password));
            ShoppingCart shoppingCart = new ShoppingCart(user);
            shoppingCartService.create(shoppingCart);
            resp.sendRedirect(req.getContextPath() + "/users/all");
        } else {
            String issue = "Passwords is not equals";
            req.setAttribute("issue", issue);
            req.setAttribute("savedLogin", login);
            req.setAttribute("savedName", name);
            req.setAttribute("savedSurname", surname);
            this.doGet(req, resp);
        }
    }
}
