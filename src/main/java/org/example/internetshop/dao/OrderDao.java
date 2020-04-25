package org.example.internetshop.dao;

import org.example.internetshop.model.Order;
import org.example.internetshop.model.User;
import java.util.List;

public interface OrderDao extends GenericDao<Order, Long>{
    List<Order> getUserOrders(User user);
}
