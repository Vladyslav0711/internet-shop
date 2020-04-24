package org.example.internetshop.service.impl;

import org.example.internetshop.dao.OrderDao;
import org.example.internetshop.dao.ShoppingCartDao;
import org.example.internetshop.lib.Inject;
import org.example.internetshop.lib.Service;
import org.example.internetshop.model.Order;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.model.User;
import org.example.internetshop.service.OrderService;
import org.example.internetshop.service.ShoppingCartService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService  {

    @Inject
    OrderDao orderDao;

    @Override
    public Order get(Long orderId) {
        return orderDao.get(orderId).get();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }

    @Override
    public Order completeOrder(List<Product> products, User user) {
        Order order = new Order(user, products);
        return orderDao.create(order);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getUserOrders(user);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

}
