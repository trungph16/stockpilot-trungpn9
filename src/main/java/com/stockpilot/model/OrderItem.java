package com.stockpilot.model;

import java.math.BigDecimal;

public class OrderItem {

    private Product product;
    private int quantity;
    private BigDecimal unitPrice;

    public OrderItem(
            Product product,
            int quantity,
            BigDecimal unitPrice) {

        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}