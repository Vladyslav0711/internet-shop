package org.example.internetshop.security;

import org.example.internetshop.exception.AuthenticationException;
import org.example.internetshop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}
