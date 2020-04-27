package org.example.internetshop.service.impl;

import java.util.List;
import org.example.internetshop.dao.OrderDao;
import org.example.internetshop.lib.Inject;
import org.example.internetshop.lib.Service;
import org.example.internetshop.model.Order;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.model.User;
import org.example.internetshop.service.OrderService;
import org.example.internetshop.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    OrderDao orderDao;

    @Inject
    ShoppingCartService shoppingCartService;

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
        List<Product> copyProducts = List.copyOf(products);
        Order order = new Order(user, copyProducts);
        order = orderDao.create(order);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(user.getId());
        shoppingCartService.clear(shoppingCart);
        return order;
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
