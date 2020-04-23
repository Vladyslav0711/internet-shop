package org.example.internetshop.dao;

import org.example.internetshop.model.Order;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.model.User;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order create(ShoppingCart shoppingCart);

    Optional<Order> get(Long orderId);

    Order update(Order order);

    boolean delete(Long id);

    List<Order> getUserOrders(User user);

    List<Order> getAll();
}
