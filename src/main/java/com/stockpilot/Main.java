package com.stockpilot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.stockpilot.exception.ProductNotFoundException;
import com.stockpilot.model.Product;
import com.stockpilot.repository.ProductRepository;
import com.stockpilot.repository.ProductRepositoryJdbc;
import com.stockpilot.repository.Repository;
import com.stockpilot.util.DatabaseInitializer;
import com.stockpilot.util.ValidationUtils;

public class Main {

    public static void main(String[] args) {
        
        ProductRepository repository = new ProductRepositoryJdbc();

        Product product =
                new Product(
                        null,
                        "ABC-1001",
                        "Laptop",
                        "Electronics",
                        new BigDecimal("1500"),
                        10
                );

        List<Product> products = new ArrayList<>();
        
        repository.deleteById(1l);
        products = repository.findAll();
        
        for (Product p : products) {
            System.out.println(p.getId());
        }

        System.out.println();
    }
}