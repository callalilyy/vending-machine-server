package com.techelevator.view;

public class Candy extends Product {

    public Candy(String slotNumber, String name, double price, int quantity) {
        super(slotNumber, name, price, "Candy", quantity);
    }

    @Override
    public String getPurchaseMessage() {
        return "Munch Munch, Yum!";
    }
}