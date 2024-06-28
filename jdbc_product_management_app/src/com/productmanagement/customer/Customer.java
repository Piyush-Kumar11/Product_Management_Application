package com.productmanagement.customer;

import com.productmanagement.database.DatabaseConnection;
import java.sql.*;
import java.util.Scanner;

/*
 * @Piyush Kumar
 */

public class Customer {
    private static final Scanner scanner = new Scanner(System.in);

    public static void register() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            String query = "INSERT INTO customer (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Registration successful.");
            } else {
                System.out.println("Registration failed. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean login(String email, String password) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String query = "SELECT * FROM customer WHERE email = ? AND password = ?";
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

    public static void displayProducts() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            String query = "SELECT * FROM product";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Products:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                                   ", Name: " + resultSet.getString("name") +
                                   ", Price: " + resultSet.getDouble("price") +
                                   ", Color: " + resultSet.getString("color") +
                                   ", Mfg Date: " + resultSet.getDate("mfg_date") +
                                   ", Exp Date: " + resultSet.getDate("exp_date"));
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void customerOperations() {
        System.out.println("Customer Operations:");
        System.out.println("1. Display Products");
        System.out.println("2. Exit");
        while (true) {
            System.out.print("Customer- Enter your choice: \n1. Display Products\n2.Exit ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
