package org.example.internetshop.service;

import java.util.List;
import org.example.internetshop.model.Order;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.User;

public interface OrderService {

    Order get(Long orderId);

    boolean delete(Long id);

    Order completeOrder(List<Product> products, User user);

    List<Order> getUserOrders(User user);

    List<Order> getAll();
}
