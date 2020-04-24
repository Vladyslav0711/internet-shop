package org.example.internetshop;

import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Order;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.model.User;
import org.example.internetshop.service.OrderService;
import org.example.internetshop.service.ProductService;
import org.example.internetshop.service.ShoppingCartService;
import org.example.internetshop.service.UserService;

import java.util.ArrayList;

public class Application {
    private static Injector injector = Injector.getInstance("org.example.internetshop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product nokia = new Product("Nokia", 2400.0);
        Product simens = new Product("Simens", 1100.0);
        Product sumsung = new Product("Sumsung", 3100.0);
        productService.create(nokia);
        productService.create(simens);
        productService.create(sumsung);
        UserService userService = (UserService) injector.getInstance(UserService.class);

        User dima = new User("Dima", "Dimich228");
        User vlad = new User("Vlad", "vladislav0711");
        User olya = new User("Olya", "Olka123");

        userService.create(dima);
        userService.create(vlad);
        userService.create(olya);
        System.out.println("All users:");
        userService.getAll().forEach(System.out::println);

        System.out.println("get 2:");
        System.out.println(userService.get(2L));

        System.out.println("Delete 1:");
        userService.delete(1L);
        userService.getAll().forEach(System.out::println);

        System.out.println("UpdateUser:");
        vlad.setLogin("vladvlad");
        System.out.println(userService.update(vlad));

        ShoppingCartService shoppingCartService = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

        ShoppingCart vladShoppingCart = new ShoppingCart(vlad);

        shoppingCartService.addProduct(vladShoppingCart, nokia);
        shoppingCartService.addProduct(vladShoppingCart, simens);
        shoppingCartService.addProduct(vladShoppingCart, sumsung);

        System.out.println("products from cart:");
        shoppingCartService.getAllProducts(vladShoppingCart).forEach(System.out::println);



        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

        orderService.completeOrder(shoppingCartService.getAllProducts(vladShoppingCart), vlad);
        orderService.getUserOrders(vlad).forEach(System.out::println);

        System.out.println(shoppingCartService.getByUserId(vlad.getId()));
    }
}
