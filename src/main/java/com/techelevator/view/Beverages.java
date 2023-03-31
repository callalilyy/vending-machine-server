package com.techelevator.view;

public class Beverages extends Product {

    public Beverages(String slotNumber, String name, double price, int quantity) {
        super(slotNumber, name, price, "Beverages", quantity);
    }

    @Override
    public String getPurchaseMessage() {
        return "Glug Glug, Yum!";
    }
}
