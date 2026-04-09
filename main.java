/*
Name: Rasul.A
Date: April 4th, 2026
Description: Main file of assignment 4
 */

// imports
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;


public class main {
    // List to store products
    private static ArrayList<Product> inventory = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        System.out.print("Enter your name: ");
        String userName = input.nextLine();
        int score = 0;
        System.out.println("Hello" + userName + "Score: " + score);

        int choice = 0;
        // Loop
        while (choice != 7) {
            System.out.println("--- Inventory Menu ---");
            System.out.println("1) Create Product");
            System.out.println("2) Create Perishable Product");
            System.out.println("3) Edit Product by SKU");
            System.out.println("4) Delete Product by SKU");
            System.out.println("5) Display Product by SKU");
            System.out.println("6) Display all Products");
            System.out.println("7) Exit");
            System.out.print("Select an option: ");

            try {
                choice = Integer.parseInt(input.nextLine());

                if (choice == 1) {
                    createProduct(false);
                } else if (choice == 2) {
                    createProduct(true);
                } else if (choice == 3) {
                    editProduct();
                } else if (choice == 4) {
                    deleteProduct();
                } else if (choice == 5) {
                    displayBySku();
                } else if (choice == 6) {
                    displayAll();
                } else if (choice == 7) {
                    System.out.println("Exiting, goodbye " + userName + "!");
                } else {
                    System.out.println("Invalid choice. enter 1-7.");
                }
            } catch (Exception e) {
                System.out.println("Error: enter a numeric value.");
            }
        }
    }

    // Adding product
    private static void createProduct(boolean isPerishable) {
        System.out.print("SKU (8+ digits): ");
        String sku = input.nextLine();
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Cost: ");
        double cost = Double.parseDouble(input.nextLine());
        System.out.print("Price: ");
        double price = Double.parseDouble(input.nextLine());
        System.out.print("Quantity on Hand: ");
        int hand = Integer.parseInt(input.nextLine());
        System.out.print("Quantity Needed: ");
        int need = Integer.parseInt(input.nextLine());
        System.out.print("Special Instructions: ");
        String notes = input.nextLine();

        if (isPerishable) {
            System.out.print("Expiry Date (dd/mm/yyyy): ");
            try {
                Date expiry = new SimpleDateFormat("dd/MM/yyyy").parse(input.nextLine());
                inventory.add(new PerishableProduct(sku, name, cost, price, hand, need, notes, expiry));
            } catch (Exception e) {
                System.out.println("Invalid date. Defaulting to today.");
                inventory.add(new PerishableProduct(sku, name, cost, price, hand, need, notes, new Date()));
            }
        } else {
            inventory.add(new Product(sku, name, cost, price, hand, need, notes));
        }
        System.out.println("Product added successfully!");
    }

    // Finds the product by sku and allows for the user to update it
    private static void editProduct() {
        System.out.print("Enter SKU to edit: ");
        String sku = input.nextLine();
        for (Product p : inventory) {
            if (p.getSku().equals(sku)) {
                System.out.println("Product found. Enter new name: ");
                p.setProductName(input.nextLine());
                System.out.print("New Price: ");
                p.setSalePrice(Double.parseDouble(input.nextLine()));
                System.out.println("Product updated.");
                return;
            }
        }
        System.out.println("SKU not found.");
    }

    // Removes a product if sku matches it
    private static void deleteProduct() {
        System.out.print("Enter SKU to delete: ");
        String sku = input.nextLine();
        if (inventory.removeIf(p -> p.getSku().equals(sku))) {
            System.out.println("Product deleted.");
        } else {
            System.out.println("SKU not found.");
        }
    }

    // Show specific product
    private static void displayBySku() {
        System.out.print("Enter SKU to view: ");
        String sku = input.nextLine();
        for (Product p : inventory) {
            if (p.getSku().equals(sku)) {
                System.out.println(p);
                return;
            }
        }
        System.out.println("SKU not found.");
    }

    // Prints all products currently in the list
    private static void displayAll() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product p : inventory) {
                System.out.println(p);
            }
        }
    }
}
