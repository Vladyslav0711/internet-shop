package org.example.internetshop.dao;

import java.util.Optional;
import org.example.internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByLogin(String login);
}
