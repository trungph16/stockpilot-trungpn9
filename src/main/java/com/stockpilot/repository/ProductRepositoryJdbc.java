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
import com.stockpilot.model.Product;
import com.stockpilot.util.DBConnection;

public class ProductRepositoryJdbc implements ProductRepository {

    @Override
    public Product save(Product product) {

        String sql =
                """
                INSERT INTO products
                (
                    sku,
                    name,
                    category,
                    price,
                    stock_quantity
                )
                VALUES
                (
                    ?, ?, ?, ?, ?
                )
                """;

        try (
                Connection connection = DBConnection.getConnection();

                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, product.getSku());

            statement.setString(2, product.getName());

            statement.setString(3, product.getCategory());

            statement.setBigDecimal(4, product.getPrice());

            statement.setInt(5, product.getStockQuantity());

            statement.executeUpdate();

            return product;

        } catch (SQLException e) {
            throw new DataAccessException("Save product failed", e);
        }
    };

    @Override
    public List<Product> findAll() {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            while (rs.next()) {

                Product product =
                        new Product(
                                rs.getLong("id"),
                                rs.getString("sku"),
                                rs.getString("name"),
                                rs.getString("category"),
                                rs.getBigDecimal("price"),
                                rs.getInt("stock_quantity")
                        );

                products.add(product);
            }

        } catch (SQLException e) {

            throw new DataAccessException(
                    "Find all products failed",
                    e
            );
        }

        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {

        String sql = "SELECT * FROM products WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, id);

            try (ResultSet rs = statement.executeQuery())
            {
                if(rs.next()) {
                    Product product = new Product(
                        rs.getLong("id"),
                        rs.getString("sku"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock_quantity")
                    );

                    return Optional.of(product);
                }
            }

        } catch (SQLException e) {

            throw new DataAccessException(
                    "Find product by ID failed for id: " + id,
                    e
            );
        }

        return Optional.empty();
    }

    @Override
    public Product update(Product product) {

        String sql = 
                """
                    UPDATE products
                    SET
                        sku = ?,
                        name = ?,
                        category = ?,
                        price = ?,
                        stock_quantity = ?
                    WHERE id = ?
                """;
        
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, product.getSku());
            statement.setString(2, product.getName());
            statement.setString(3, product.getCategory());
            statement.setBigDecimal(4, product.getPrice());
            statement.setInt(5, product.getStockQuantity());
            statement.setLong(6, product.getId());

            int rowAffected = statement.executeUpdate();

            if (rowAffected == 0) {
                throw new NotFoundException(
                    "Product not found with id: " + product.getId()
                );
            }

            return product;
        } catch (SQLException e) {
            throw new DataAccessException(
                    "Update product failed for id: " + product.getId(),
                    e
            );
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
    
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);

            int rowAffected = statement.executeUpdate();

            if (rowAffected == 0) {
                throw new NotFoundException(
                    "Product not found with id: " + id
                );
            }

        } catch (SQLException e) {
            throw new DataAccessException(
                    "Delete product failed for id: " + id,
                    e
            );
        }
    }
}