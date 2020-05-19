package org.example.internetshop.security;

import org.example.internetshop.exception.AuthenticationException;
import org.example.internetshop.lib.Inject;
import org.example.internetshop.lib.Service;
import org.example.internetshop.model.User;
import org.example.internetshop.service.UserService;
import org.example.internetshop.utill.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User userFromDB = userService.findByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect username or password"));
        if (userFromDB.getPassword().equals(HashUtil.hashPassword(password, userFromDB.getSalt()))) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect username or password");
    }
}
