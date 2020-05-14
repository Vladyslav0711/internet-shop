package org.example.internetshop.dao;

import java.util.List;
import org.example.internetshop.model.Product;

public interface ProductDao extends GenericDao<Product, Long> {
    List<Product> getProductsByCart(Long cartId);

    List<Product> getProductByOrder(Long orderID);
}
