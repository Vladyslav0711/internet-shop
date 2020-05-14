package org.example.internetshop.service.impl;

import java.util.List;
import org.example.internetshop.dao.OrderDao;
import org.example.internetshop.lib.Inject;
import org.example.internetshop.lib.Service;
import org.example.internetshop.model.Order;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;
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
    public boolean deleteUserOrders(Long userId) {
        for (Order order : orderDao.getUserOrders(userId)) {
            orderDao.delete(order.getId());
        }
        return true;
    }

    @Override
    public Order completeOrder(List<Product> products, Long userId) {
        List<Product> copyProducts = List.copyOf(products);
        Order order = new Order(userId, copyProducts);
        order = orderDao.create(order);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        order = orderDao.update(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.getUserOrders(userId);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

}
