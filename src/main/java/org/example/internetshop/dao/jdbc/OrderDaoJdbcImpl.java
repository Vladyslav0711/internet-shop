package org.example.internetshop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.internetshop.dao.OrderDao;
import org.example.internetshop.dao.ShoppingCartDao;
import org.example.internetshop.exception.DataProcessingException;
import org.example.internetshop.lib.Dao;
import org.example.internetshop.lib.Inject;
import org.example.internetshop.model.Order;
import org.example.internetshop.model.Product;
import org.example.internetshop.utill.ConnectionUtil;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public List<Order> getUserOrders(Long userId) {
        String query = "SELECT * FROM orders WHERE user_id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("Getting user orders failed", e);
        }
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id)"
                + "VALUES (?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            setProductsToOrder(order);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Order creation failed", e);
        }
    }

    @Override
    public Optional<Order> get(Long orderId) {
        String query = "SELECT * FROM orders WHERE order_id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getOrderFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Getting order failed", e);
        }
    }

    @Override
    public Order update(Order order) {
        String query = "DELETE FROM orders_products"
                + " WHERE order_id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            clearOrderInfo(query, connection, order.getId());
            setProductsToOrder(order);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Update cart with id = "
                    + order.getId() + "failed", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM orders_products"
                    + " WHERE order_id=?;";
            clearOrderInfo(query, connection, id);
            query = " DELETE FROM orders"
                    + " WHERE order_id=?;";
            return clearOrderInfo(query, connection, id);
        } catch (SQLException e) {
            throw new DataProcessingException("Deleting cart with id = "
                    + id + " failed", e);
        }
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders";
        try (Connection connection = ConnectionUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("Getting all orders failed", e);
        }
    }

    private boolean clearOrderInfo(String query, Connection connection, Long orderId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, orderId);
        return statement.executeUpdate() != 0;
    }

    private void setProductsToOrder(Order order) {
        String query = "INSERT INTO orders_products (order_id, product_id)"
                + " VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            for (Product product : order.getProducts()) {
                statement.setLong(1, order.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Setting products to cart failed", e);
        }
    }

    public List<Product> getProductByOrder(Long orderId) {
        String query = "SELECT id, product_name, price FROM orders_products op"
                + " JOIN products p ON op.product_id = p.id"
                + " WHERE op.order_id = ?;";
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet));
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Getting products from order failed", e);
        }
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("product_name");
        BigDecimal price = resultSet.getBigDecimal("price");
        return new Product(id, name, price);
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong("order_id");
        Long userId = resultSet.getLong("user_id");
        return new Order(orderId, userId, getProductByOrder(orderId));
    }
}
