package org.example.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.internetshop.dao.ProductDao;
import org.example.internetshop.dao.ShoppingCartDao;
import org.example.internetshop.exception.DataProcessingException;
import org.example.internetshop.lib.Dao;
import org.example.internetshop.lib.Inject;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.utill.ConnectionUtil;

@Dao
public class ShoppingCartJdbcImpl implements ShoppingCartDao {

    @Inject
    ProductDao productDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        String query = "INSERT INTO shopping_carts (user_id)"
                + "VALUES (?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, shoppingCart.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                shoppingCart.setId(resultSet.getLong(1));
            }
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataProcessingException("Shopping cart creation failed", e);
        }
    }

    @Override
    public Optional<ShoppingCart> get(Long userId) {
        String query = "SELECT * FROM shopping_carts WHERE user_id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getShoppingCartFromResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Getting cart failed", e);
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        String query = "DELETE FROM shopping_carts_products"
                + " WHERE cart_id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            clearCartInfo(query, connection, shoppingCart.getId());
            setProductsToCart(shoppingCart);
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataProcessingException("Update cart with id = "
                    + shoppingCart.getId() + "failed", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM shopping_carts_products"
                    + " WHERE cart_id=?;";
            clearCartInfo(query, connection, id);
            query = " DELETE FROM shopping_carts"
                    + " WHERE cart_id=?;";
            return clearCartInfo(query, connection, id);
        } catch (SQLException e) {
            throw new DataProcessingException("Deleting cart with id = "
                    + id + " failed", e);
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        String query = "SELECT * FROM shopping_carts";
        try (Connection connection = ConnectionUtil.getConnection()) {
            List<ShoppingCart> shoppingCarts = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                shoppingCarts.add(getShoppingCartFromResultSet(resultSet));
            }
            return shoppingCarts;
        } catch (SQLException e) {
            throw new DataProcessingException("Getting all carts failed", e);
        }
    }

    private void setProductsToCart(ShoppingCart shoppingCart) {
        String query = "INSERT INTO shopping_carts_products (cart_id, product_id)"
                + " VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            for (Product product : shoppingCart.getProducts()) {
                statement.setLong(1, shoppingCart.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Setting products to cart failed", e);
        }
    }

    private boolean clearCartInfo(String query, Connection connection, Long id)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        return statement.executeUpdate() != 0;
    }

    private ShoppingCart getShoppingCartFromResultSet(ResultSet resultSet)
            throws SQLException {
        Long cartId = resultSet.getLong("cart_id");
        Long userId = resultSet.getLong("user_id");
        return new ShoppingCart(cartId, userId, productDao.getProductsByCart(cartId));
    }
}
