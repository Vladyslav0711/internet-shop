package org.example.internetshop.web.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Role;
import org.example.internetshop.model.User;
import org.example.internetshop.service.UserService;

public class AuthorizationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("org.example.internetshop");
    private UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        protectedUrls.put("/users/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/users/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/edit", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/complete", Set.of(Role.RoleName.USER));
        protectedUrls.put("/orders", Set.of(Role.RoleName.USER));
        protectedUrls.put("/orders/info", Set.of(Role.RoleName.USER));
        protectedUrls.put("/cart", Set.of(Role.RoleName.USER));
        protectedUrls.put("/cart/add", Set.of(Role.RoleName.USER));
        protectedUrls.put("/cart/delete", Set.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getServletPath();

        if (protectedUrls.get(url) == null) {
            filterChain.doFilter(req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null || userService.get(userId) == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        User user = userService.get(userId);

        if (isAuthorized(user, protectedUrls.get(url))) {
            filterChain.doFilter(req, resp);
        } else {
            LOGGER.warn("User " + user.getLogin() + " forbidden access to page " + url);
            req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isAuthorized(User user, Set<Role.RoleName> authorizedRoles) {
        for (Role.RoleName authorizedRole : authorizedRoles) {
            for (Role userRole : user.getRoles()) {
                if (authorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
