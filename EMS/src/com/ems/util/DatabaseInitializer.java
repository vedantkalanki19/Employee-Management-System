package com.ems.util;

import java.sql.Connection;
import java.sql.Statement;
import java.io.File;

public class DatabaseInitializer {

    public static void initialize() {
        // Ensure data directory exists
        new File("data").mkdirs();

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {

            // 1. Users Table
            String sqlUsers = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(50) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "role VARCHAR(20) NOT NULL)";
            stmt.execute(sqlUsers);

            // 2. Employees Table
            String sqlEmp = "CREATE TABLE IF NOT EXISTS employees (" +
                    "emp_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id INT, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100), " +
                    "phone VARCHAR(20), " +
                    "type VARCHAR(20) NOT NULL, " +
                    "basic_pay DECIMAL(10, 2) NOT NULL, " +
                    "allowances DECIMAL(10, 2) DEFAULT 0.00, " +
                    "join_date DATE, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE)";
            stmt.execute(sqlEmp);

            // 3. Default Admin (Check if exists first to avoid duplicates)
            try {
                String sqlAdmin = "INSERT INTO users (username, password, role) VALUES ('admin', 'admin123', 'ADMIN')";
                stmt.execute(sqlAdmin);
                System.out.println("Default Admin created (admin/admin123).");
            } catch (Exception e) {
                // Ignore unique constraint violation if admin already exists
            }

            System.out.println("Database Initialized Successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
