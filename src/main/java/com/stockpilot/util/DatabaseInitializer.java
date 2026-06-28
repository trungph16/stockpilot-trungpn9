package com.stockpilot.util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {

        try (
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement()
        ) {

            InputStream inputStream = DatabaseInitializer.class.getClassLoader().getResourceAsStream("schema.sql");

            String sql = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            statement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}