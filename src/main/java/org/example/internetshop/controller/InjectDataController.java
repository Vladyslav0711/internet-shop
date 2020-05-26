package org.example.internetshop.controller;

import java.io.IOException;
import java.util.Set;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Role;
import org.example.internetshop.model.User;
import org.example.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("org.example.internetshop");
    private UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        User admin = new User("admin", "admin",
                "admin", "admin", Set.of(Role.of("ADMIN")));
        userService.create(admin);
        resp.sendRedirect(req.getContextPath() + "/products/all");
    }
}
