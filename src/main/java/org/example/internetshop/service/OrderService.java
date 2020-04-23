package org.example.internetshop.service;

import org.example.internetshop.model.Order;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.model.User;
import java.util.List;

public interface OrderService {

    Order get(Long orderId);

    boolean delete(Long id);

    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getUserOrders(User user);

    List<Order> getAll();
}
