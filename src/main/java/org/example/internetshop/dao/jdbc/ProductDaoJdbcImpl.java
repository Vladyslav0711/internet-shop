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
import org.example.internetshop.dao.ProductDao;
import org.example.internetshop.exception.DataProcessingException;
import org.example.internetshop.lib.Dao;
import org.example.internetshop.model.Product;
import org.example.internetshop.utill.ConnectionUtil;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {

    @Override
    public Product create(Product product) {
        String query = "INSERT INTO products (product_name, price)" +
                "VALUES (?, ?)";
        try(Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement  statement = connection.
                    prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next())
            {
                product.setId(resultSet.getLong(1));
            }
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Product creation failed", e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String query = "SELECT * FROM products WHERE id=?";
        try(Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return Optional.of(getProductFromResultSet(resultSet));
        } catch (SQLException e) {
            throw new DataProcessingException("Getting product with id = " + id + "failed", e);
        }
    }

    @Override
    public Product update(Product product) {
        String query = "UPDATE products " +
                "SET produtc_name = ?" +
                "price = ?" +
                "WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getResultSet();
            return getProductFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DataProcessingException("Update product with id = " + product.getId() + "failed", e);
        }
    }

    @Override
    public boolean delete(Long id) {

        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM products WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Deleting product with id = " + id + "failed", e);
        }
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet));
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Getting all products failed", e);
        }
    }
    public Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("product_name");
        BigDecimal price = resultSet.getBigDecimal("price");
        return new Product(id, name, price);
    }
}
