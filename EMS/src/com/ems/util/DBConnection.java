package com.ems.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // H2 Embedded Database (Stored in 'data' folder)
    private static final String URL = "jdbc:h2:./data/ems_db;DB_CLOSE_DELAY=-1";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("H2 Driver not found! Make sure h2.jar is in the lib folder.");
        } catch (SQLException e) {
            System.err.println("DB Connection failed! " + e.getMessage());
        }
        return conn;
    }
}
