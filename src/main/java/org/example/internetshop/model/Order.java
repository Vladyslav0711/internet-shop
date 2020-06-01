package org.example.internetshop.model;

import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private List<Product> products;

    public Order(Long userId, List<Product> products) {
        this.userId = userId;
        this.products = products;
    }

    public Order(Long id, Long userId, List<Product> products) {
        this(userId, products);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", user=" + userId
                + ", products=" + products
                + '}';
    }
}
