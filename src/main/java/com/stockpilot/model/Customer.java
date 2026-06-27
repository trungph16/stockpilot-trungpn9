package com.stockpilot.model;

import java.util.Objects;

public class Customer {

    private Long id;
    private String name;
    private String email;
    private String phone;

    public Customer(
            Long id,
            String name,
            String email,
            String phone) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }
}