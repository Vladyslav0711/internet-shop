package org.example.internetshop.dao;

import java.util.List;
import org.example.internetshop.model.Order;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getUserOrders(Long userId);
}
