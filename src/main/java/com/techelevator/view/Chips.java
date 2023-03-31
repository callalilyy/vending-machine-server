package com.techelevator.view;

public class Chips extends Product {

    public Chips(String slotNumber, String name, double price, int quantity) {
        super(slotNumber, name, price, "Chips", quantity);
    }

    @Override
    public String getPurchaseMessage() {
        return "Crunch Crunch, Yum!";
    }
}
