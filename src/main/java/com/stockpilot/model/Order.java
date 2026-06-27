package com.stockpilot.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private Long id;
    private Customer customer;
    private List<OrderItem> items;
    private LocalDateTime createdAt;

    public Order(
            Long id,
            Customer customer,
            List<OrderItem> items,
            LocalDateTime createdAt) {

        this.id = id;
        this.customer = customer;
        this.items = items;
        this.createdAt = createdAt;
    }
}