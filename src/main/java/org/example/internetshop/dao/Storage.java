package org.example.internetshop.dao;

import java.util.ArrayList;
import java.util.List;
import org.example.internetshop.model.Order;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.model.User;

public class Storage {
    public static final List<Product> products = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    private static Long idProduct = 0L;
    private static Long idShoppingCart = 0L;
    private static Long idOrder = 0L;
    private static Long idUser = 0L;

    public static void addItemToList(Product product) {
        product.setId(++idProduct);
        products.add(product);
    }

    public static void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setId(++idShoppingCart);
        shoppingCarts.add(shoppingCart);
    }

    public static void addOrder(Order order) {
        order.setId(++idOrder);
        orders.add(order);
    }

    public static void addUser(User user) {
        user.setId(++idUser);
        users.add(user);
    }
}
