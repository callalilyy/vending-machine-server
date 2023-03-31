package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
    private Map<String, Product> products;

    public Inventory() {
        // Initialize the products Map and load inventory from file
        products = new HashMap<>();
        loadInventoryFromFile("vendingmachine.csv");
    }

    public void restock() {
        // Restock the vending machine - method body not implemented yet
    }

    public Product selectProduct(String slotIdentifier) {
        // Convert user input to uppercase
        slotIdentifier = slotIdentifier.toUpperCase();

        // Check if the slot identifier is valid
        if (!products.containsKey(slotIdentifier)) {
            System.out.println("Invalid product code.");
            return null;
        }

        // Look up product by slot identifier
        Product product = products.get(slotIdentifier);

        if (product.getQuantity() == 0) {
            // Product is sold out, return null
            System.out.println("SOLD OUT.");
            return null;
        } else {
            // Product found and in stock, return product
            return product;
        }
    }

    public void displayProducts() {
        // Iterate through each product in the products Map
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            String slotNumber = entry.getKey();
            Product product = entry.getValue();
            String productName = product.getName();
            double productPrice = product.getPrice();
            int productQuantity = product.getQuantity();

            if (productQuantity == 0) {
                // Product is sold out, print message indicating that
                System.out.printf("%s| %s| $%.2f| SOLD OUT%n", slotNumber, productName, productPrice);
            } else {
                // Product is in stock, print message indicating that and the remaining quantity
                System.out.printf("%s| %s| $%.2f| %d remaining%n", slotNumber, productName, productPrice, productQuantity);
            }
        }
    }

    private void loadInventoryFromFile(String filename) {
        try {
            // Open file using filename passed in as parameter
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);

            // Iterate through each line in the file
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] productData = line.split("\\|");

                // Parse data from file line into variables
                String slotNumber = productData[0];
                String name = productData[1];
                double price = Double.parseDouble(productData[2]);
                String type = productData[3];
                int quantity = 5; // Assuming an initial quantity of 5 for all items

                // Create new Product object based on type and add it to products Map
                Product product;

                switch (type) {
                    case "Chips":
                        product = new Chips(slotNumber, name, price, quantity);
                        break;
                    case "Gum":
                        product = new Gum(slotNumber, name, price, quantity);
                        break;
                    case "Beverage":
                        product = new Beverages(slotNumber, name, price, quantity);
                        break;
                    case "Candy":
                        product = new Candy(slotNumber, name, price, quantity);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid product type: " + type);
                }

                products.put(slotNumber, product);
            }
        } catch (FileNotFoundException e) {
            // Catch block for FileNotFoundException
            System.out.println("Error: File not found - " + filename);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

