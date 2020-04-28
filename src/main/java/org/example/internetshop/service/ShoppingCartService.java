package org.example.internetshop.service;

import java.util.List;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);
}
