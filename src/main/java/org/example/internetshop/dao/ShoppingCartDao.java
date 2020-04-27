package org.example.internetshop.dao;

import java.util.Optional;
import org.example.internetshop.model.ShoppingCart;

public interface ShoppingCartDao extends GenericDao<ShoppingCart, Long> {

    Optional<ShoppingCart> getByUserId(Long userId);
}
