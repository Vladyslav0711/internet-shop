package org.example.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.example.internetshop.dao.UserDao;
import org.example.internetshop.exception.DataProcessingException;
import org.example.internetshop.model.Role;
import org.example.internetshop.model.User;
import org.example.internetshop.utill.ConnectionUtil;

public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE login=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.executeUpdate();
            ResultSet resultSet = statement.getResultSet();
            return Optional.of(getUserFromResultSet(resultSet));
        } catch (SQLException e) {
            throw new DataProcessingException("Product creation failed", e);
        }
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (name, surname, login, password)"
                + "VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Product creation failed", e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users WHERE id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            ResultSet resultSet = statement.getResultSet();
            return Optional.of(getUserFromResultSet(resultSet));
        } catch (SQLException e) {
            throw new DataProcessingException("Product creation failed", e);
        }
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users u"
                + "JOIN users_roles ur ON u.id=ur.user_id;";
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new DataProcessingException("Getting all products failed", e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");

        return new User(id, name, surname, login, password, getUserRoles(id));
    }

    private Set<Role> getUserRoles(Long userId) {
        String query = "SELECT role_name FROM users_roles ur"
                + "JOIN roles r ON ur.role_id=r.role_id"
                + "WHERE ur.user_id=?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            Set<Role> roles = new HashSet<>();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                roles.add(Role.of(resultSet.getString("role_name")));
            }
            return roles;
        } catch (SQLException e) {
            throw new DataProcessingException("Getting all products failed", e);
        }
    }
}
