package com.techelevator.view;

public abstract class Product {

    // Declare instance variables for Product class
    private String slotNumber;
    private String name;
    private double price;
    private String type;
    private int quantity;

    // Constructor for Product class
    public Product(String slotNumber, String name, double price, String type, int quantity) {
        // Assign values to instance variables
        this.slotNumber = slotNumber;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = 5;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Abstract method to get purchase message for Product subclass
    public abstract String getPurchaseMessage();

}
