package org.example.internetshop.service;

import org.example.internetshop.model.Order;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.model.User;
import java.util.List;

public interface OrderService {

    Order get(Long orderId);

    boolean delete(Long id);

    Order completeOrder(List<Product> products, User user);

    List<Order> getUserOrders(User user);

    List<Order> getAll();
}
