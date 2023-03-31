package com.techelevator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class Transactions {
    private double currentBalance;

    public void feedMoney(double amount) {
        // Add the amount fed to the current balance
        currentBalance += amount;
        logTransaction(String.format("Feed Money: $%.2f $%.2f", currentBalance, amount));

    }

    public void purchaseProduct(Product product) {
        // Subtract the price of the product from the current balance
        currentBalance -= product.getPrice();

        // Log the transaction
        logTransaction(String.format("%s %s $%.2f $%.2f", product.getName(), product.getSlotNumber(), product.getPrice(), currentBalance));
    }


    public Map<String, Integer> returnChange(){
        // Calculate the number of quarters, dimes, and nickels required for the current balance
        int balanceInCents = (int) (currentBalance * 100);
        int quarters = balanceInCents / 25;
        balanceInCents %= 25;
        int dimes = balanceInCents / 10;
        balanceInCents %= 10;
        int nickels = balanceInCents / 5;

        // Set current balance to zero and return a Map containing the number of each coin returned
        logTransaction(String.format("GIVE CHANGE: $%.2f $%.2f", currentBalance, resetCurrentBalance()));

        currentBalance = 0;

        return new HashMap<String, Integer>() {{
            put("quarters", quarters);
            put("dimes", dimes);
            put("nickels", nickels);
        }};

    }

    private void logTransaction(String logMessage) {
        try {
            // Create a FileWriter object to append to the Log.txt file
            FileWriter writer = new FileWriter("Log.txt", true);

            // Write the log message to the file
            writer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")) + " " + logMessage + "\n");

            // Close the FileWriter
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }


    public double getCurrentBalance() {
        // Getter method for currentBalance
        return currentBalance;
    }

    public double resetCurrentBalance() {
       return currentBalance = 0;
    }
}
