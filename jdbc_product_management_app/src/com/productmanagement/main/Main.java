package com.productmanagement.main;

import com.productmanagement.admin.Admin;
import com.productmanagement.productowner.ProductOwner;
import com.productmanagement.customer.Customer;
import java.util.Scanner;

/*
 * @Piyush Kumar
 */

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Product Management App");
        System.out.println("1. Admin Login");
        System.out.println("2. Product Owner Register/Login");
        System.out.println("3. Customer Login/Register");
        System.out.println("4. Exit");
        while (true) {
            System.out.print("For Admin- Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    productOwnerAccess();
                    break;
                case 3:
                    customerAccess();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
//
    public static void adminLogin() {
        System.out.print("Enter Admin Email: ");
        String email = scanner.next();
        System.out.print("Enter Admin Password: ");
        String password = scanner.next();
        if (Admin.login(email, password)) {
            System.out.println("Login successful!");
            Admin.adminOperations();
        } else {
            System.out.println("Invalid email or password.");
        }
    }
//
    public static void productOwnerAccess() {
    	System.out.println("For Product Owner: ");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            ProductOwner.register();
        } else if (choice == 2) {
            System.out.print("Enter Product Owner Email: ");
            String email = scanner.next();
            System.out.print("Enter Product Owner Password: ");
            String password = scanner.next();
            if (ProductOwner.login(email, password)) {
                System.out.println("Login successful!");
                ProductOwner.productOwnerOperations();
            } else {
                System.out.println("Invalid email or password.");
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
//
    public static void customerAccess() {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("For Customer- Enter your choice: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            Customer.register();
        } else if (choice == 2) {
            System.out.print("Enter Customer Email: ");
            String email = scanner.next();
            System.out.print("Enter Customer Password: ");
            String password = scanner.next();
            if (Customer.login(email, password)) {
                System.out.println("Login successful!");
                Customer.customerOperations();
            } else {
                System.out.println("Invalid email or password.");
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}
