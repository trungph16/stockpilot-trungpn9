package com.stockpilot.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private Long id;
    private String sku;
    private String name;
    private String category;
    private BigDecimal price;
    private int stockQuantity;

    public Product(Long id,String sku, String name, String category, BigDecimal price, int stockQuantity) 
    {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Long getId() 
    {
        return id;
    }

    public String getSku() 
    {
        return sku;
    }

    public String getName() 
    {
        return name;
    }

    public String getCategory() 
    {
        return category;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    public int getStockQuantity() 
    {
        return stockQuantity;
    }

    @Override
    public String toString() {
        return sku + " - " + name;
    }

    @Override
    public boolean equals(Object o) 
    {
        if(this == o) return true;
        if(!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(id);
    }
}