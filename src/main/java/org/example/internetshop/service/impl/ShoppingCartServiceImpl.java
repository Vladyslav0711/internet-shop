package org.example.internetshop.service.impl;

import java.util.List;
import org.example.internetshop.dao.ShoppingCartDao;
import org.example.internetshop.lib.Inject;
import org.example.internetshop.lib.Service;
import org.example.internetshop.model.Product;
import org.example.internetshop.model.ShoppingCart;
import org.example.internetshop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Inject
    ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCartDao.getByUserId(shoppingCart.getUser().getId()).get()
                .getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        boolean result = shoppingCart.getProducts().remove(product);
        shoppingCartDao.update(shoppingCart);
        return result;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getByUserId(userId).get();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts();
    }
}
