package com.productmanagement.productowner;

import com.productmanagement.database.DatabaseConnection;
import java.sql.*;
import java.util.Scanner;

/*
 * @Piyush Kumar
 */

public class ProductOwner {
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

            String query = "INSERT INTO product_owner (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Registration successful. Please wait for verification.");
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
            String query = "SELECT * FROM product_owner WHERE email = ? AND password = ? AND verify = 'YES'";
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

    public static void addProduct() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            System.out.print("Enter Product Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Product Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter Product Color: ");
            String color = scanner.nextLine();
            System.out.print("Enter Manufacturing Date (YYYY-MM-DD): ");
            String mfgDate = scanner.nextLine();
            System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
            String expDate = scanner.nextLine();

            String query = "INSERT INTO product (name, price, color, mfg_date, exp_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, color);
            statement.setDate(4, Date.valueOf(mfgDate));
            statement.setDate(5, Date.valueOf(expDate));
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Product added successfully.");
            } else {
                System.out.println("Failed to add product. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static void deleteProduct() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            System.out.print("Enter Product ID to Delete: ");
            int productId = scanner.nextInt();
            String query = "DELETE FROM product WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Product not found. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            System.out.print("Enter Product ID to Update: ");
            int productId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter New Product Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter New Product Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter New Product Color: ");
            String color = scanner.nextLine();
            System.out.print("Enter New Manufacturing Date (YYYY-MM-DD): ");
            String mfgDate = scanner.nextLine();
            System.out.print("Enter New Expiry Date (YYYY-MM-DD): ");
            String expDate = scanner.nextLine();

            String query = "UPDATE product SET name = ?, price = ?, color = ?, mfg_date = ?, exp_date = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, color);
            statement.setDate(4, Date.valueOf(mfgDate));
            statement.setDate(5, Date.valueOf(expDate));
            statement.setInt(6, productId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product not found. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void productOwnerOperations() {
        System.out.println("Product Owner Operations:");
        System.out.println("1. Add Product");
        System.out.println("2. Display Products");
        System.out.println("3. Delete Product");
        System.out.println("4. Update Product");
        System.out.println("5. Exit");
        while (true) {
            System.out.print("P.Owner- Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
