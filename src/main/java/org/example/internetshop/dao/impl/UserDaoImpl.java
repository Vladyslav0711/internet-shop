package org.example.internetshop.dao.impl;

import org.example.internetshop.dao.Storage;
import org.example.internetshop.dao.UserDao;
import org.example.internetshop.lib.Dao;
import org.example.internetshop.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.addUser(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        IntStream
                .range(0, Storage.users.size())
                .filter(x -> user.getId().equals(Storage.users.get(x).getId()))
                .forEach(i -> Storage.users.set(i, user));
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.users.removeIf(u -> u.getId().equals(id));
    }
}
