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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals (Object o) {
        if(this == o) return true;
        if(!(o instanceof Customer customer)) return false;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}