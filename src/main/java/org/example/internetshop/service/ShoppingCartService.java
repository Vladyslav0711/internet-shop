package org.example.internetshop.service;

import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteCart(Long id);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    Product getProductFromCart(ShoppingCart shoppingCart, Long productId);
}
