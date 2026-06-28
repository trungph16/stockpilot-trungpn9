package com.stockpilot.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.stockpilot.exception.DataAccessException;
import com.stockpilot.exception.NotFoundException;
import com.stockpilot.model.Customer;
import com.stockpilot.model.Product;
import com.stockpilot.util.DBConnection;

public class CustomerRepositoryJdbc implements CustomerRepository {
    
    @Override
    public Customer save(Customer customer){

        String sql =
                """
                INSERT INTO customers
                (
                    name,
                    email,
                    phone
                )
                    VALUES
                    (
                        ?, ?, ?
                    )
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getEmail());

            statement.executeUpdate();

            return customer;
        } catch (SQLException e) {
            throw new DataAccessException("Save customer failed", e);
        }

    };

    @Override
    public Optional<Customer> findById(Long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, id);

            try (ResultSet rs = statement.executeQuery())
            {
                if (rs.next()) {
                    Customer customer = new Customer(
                            rs.getLong("id"), 
                            rs.getString("name"), 
                            rs.getString("email"), 
                            rs.getString("phone")
                    );

                    return Optional.of(customer);
                }
            } 
        } catch (SQLException e) {
            throw new DataAccessException(
                "Find Customer by ID failed for id: " + id,
                e
            );
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> findAll() {

        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM customers";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            while (rs.next()) {

                Customer customer =
                        new Customer(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("phone")
                        );

                customers.add(customer);
            }

        } catch (SQLException e) {

            throw new DataAccessException(
                    "Find all customer failed",
                    e
            );
        }

        return customers;
    }

    @Override
    public Customer update (Customer customer) {

        String sql = 
                """
                    UPDATE customers
                    SET
                        name = ?,
                        email = ?,
                        phone = ?
                    WHERE id = ?
                """;
        
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPhone());

            statement.setLong(4, customer.getId());

            int rowAffected = statement.executeUpdate();

            if (rowAffected == 0) {
                throw new NotFoundException(
                    "Customer not found with id: " + customer.getId()
                );
            }

            return customer;
        } catch (SQLException e) {
            throw new DataAccessException(
                    "Update customer failed for id: " + customer.getId(),
                    e
            );
        }
    }

    @Override
    public void deleteById(Long id){

        String sql = "DELETE FROM customers WHERE id = ?";
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);

            int rowAffected = statement.executeUpdate();

            if (rowAffected == 0) {
                throw new NotFoundException(
                    "Customer not found with id: " + id
                );
            }

        } catch (SQLException e) {
            throw new DataAccessException(
                    "Delete customer failed for id: " + id,
                    e
            );
        }
    }
}
