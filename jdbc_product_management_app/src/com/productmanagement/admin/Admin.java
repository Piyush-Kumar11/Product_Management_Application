package com.productmanagement.admin;

import com.productmanagement.database.DatabaseConnection;
import java.sql.*;
import java.util.Scanner;

/*
 * @Piyush Kumar
 */

public class Admin {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static boolean login(String email, String password) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String query = "SELECT * FROM admin WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void verifyProductOwner() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            System.out.print("Enter Product Owner Email to Verify: ");
            String email = scanner.next();
            String query = "UPDATE product_owner SET verify = 'YES' WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product Owner verified successfully.");
            } else {
                System.out.println("Product Owner not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayAllProducts() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String query = "SELECT * FROM product";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Products:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                                   ", Name: " + resultSet.getString("name") +
                                   ", Price: " + resultSet.getBigDecimal("price") +
                                   ", Color: " + resultSet.getString("color") +
                                   ", Mfg Date: " + resultSet.getDate("mfg_date") +
                                   ", Exp Date: " + resultSet.getDate("exp_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayAllProductOwners() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String query = "SELECT * FROM product_owner";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Product Owners:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                                   ", Name: " + resultSet.getString("name") +
                                   ", Email: " + resultSet.getString("email") +
                                   ", Verify: " + resultSet.getString("verify"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayAllCustomers() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String query = "SELECT * FROM customer";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Customers:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                                   ", Name: " + resultSet.getString("name") +
                                   ", Email: " + resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void adminOperations() {
        System.out.println("Admin Operations:");
        System.out.println("1. Verify Product Owner");
        System.out.println("2. Display All Products");
        System.out.println("3. Display All Product Owners");
        System.out.println("4. Display All Customers");
        System.out.println("5. Exit");
        while (true) {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    verifyProductOwner();
                    break;
                case 2:
                    displayAllProducts();
                    break;
                case 3:
                    displayAllProductOwners();
                    break;
                case 4:
                    displayAllCustomers();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
