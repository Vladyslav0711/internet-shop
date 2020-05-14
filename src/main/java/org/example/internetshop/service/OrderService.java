package org.example.internetshop.service;

import java.util.List;
import org.example.internetshop.model.Order;
import org.example.internetshop.model.Product;

public interface OrderService {

    Order get(Long orderId);

    boolean delete(Long id);

    boolean deleteUserOrders(Long userId);

    Order completeOrder(List<Product> products, Long userId);

    List<Order> getUserOrders(Long userId);

    List<Order> getAll();
}
