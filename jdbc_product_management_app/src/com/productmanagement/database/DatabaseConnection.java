package com.productmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * @Piyush Kumar
 */

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc-a4";
    private static final String USER = "root";
    private static final String PASSWORD = "tiger";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
//            throw new RuntimeException("Unable to connect to the database.");
            return null;
        }
    }
}
