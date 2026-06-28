package com.stockpilot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.stockpilot.model.Product;
import com.stockpilot.model.Customer;
import com.stockpilot.repository.ProductRepository;
import com.stockpilot.repository.ProductRepositoryJdbc;
import com.stockpilot.repository.CustomerRepository;
import com.stockpilot.repository.CustomerRepositoryJdbc;


public class Main {

    public static void main(String[] args) {
        
        CustomerRepository repository = new CustomerRepositoryJdbc();

        Customer customer =
                new Customer(
                        null,
                        "A",
                        "a@gmail.com",
                        "0123456789"
                );

        repository.save(customer);
        repository.save(customer);
        repository.save(customer);

        List<Customer> customers = new ArrayList<>();
        
        // repository.deleteById(1l);
        customers = repository.findAll();
        
        for (Customer c : customers) {
            System.out.println(c.getEmail());
        }
    }
}