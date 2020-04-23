package org.example.internetshop.dao.impl;

import org.example.internetshop.dao.OrderDao;
import org.example.internetshop.dao.Storage;
import org.example.internetshop.lib.Dao;
import org.example.internetshop.model.Order;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.model.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(ShoppingCart shoppingCart) {
        Order order = new Order(shoppingCart.getUser(), shoppingCart.getProducts());
        Storage.addOrder(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long orderId) {
        return Storage.orders
                .stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst();
    }

    @Override
    public Order update(Order order) {
        IntStream.range(0, Storage.orders.size())
                .filter(x -> order.getId().equals(Storage.orders.get(x).getId()))
                .forEach(i -> Storage.orders.set(i, order));
        return order;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.orders.removeIf(o -> o.getId().equals(id));
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return Storage.orders
                .stream()
                .filter(o -> o.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }
}
