package org.example.internetshop.dao;

import org.example.internetshop.model.ShoppingCart;
import java.util.Optional;

public interface ShoppingCartDao extends GenericDao<ShoppingCart, Long> {

    Optional<ShoppingCart> getByUserId(Long userId);
}
