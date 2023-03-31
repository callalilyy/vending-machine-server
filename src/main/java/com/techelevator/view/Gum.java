package com.techelevator.view;

public class Gum extends Product {

    public Gum(String slotNumber, String name, double price, int quantity) {
        super(slotNumber, name, price, "Gum", quantity);
    }

    @Override
    public String getPurchaseMessage() {
        return "Chew Chew, Yum!";
    }
}