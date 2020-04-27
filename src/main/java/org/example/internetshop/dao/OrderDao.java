package org.example.internetshop.dao;

import java.util.List;
import org.example.internetshop.model.Order;
import org.example.internetshop.model.User;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getUserOrders(User user);
}
