package com.stockpilot;

import com.stockpilot.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldCreateProductSuccessfully() {

        Product product = new Product(
                1L,
                "ABC-1001",
                "Laptop",
                "Electronics",
                new BigDecimal("1500"),
                10
        );

        assertEquals("Laptop",
                product.getName());
    }
}