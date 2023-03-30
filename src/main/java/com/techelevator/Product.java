package com.techelevator;

import java.util.Scanner;

public class Product {
    private String name;
    private double price;
    private int inventory;
    private String code;


    public Product(String name, double price, int inventory, String code){
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.code = code;
    }


    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getInventory(){
        return inventory;
    }
    public String getCode(){
        return code;
    }


    public void setName(){
        this.name = name;
    }
    public void setPrice(){
        this.price = price;
    }
    public void setInventory(){
        this.inventory = inventory;
    }
    public void setCode(){
        this.code = code;
    }


    //methods
    //If the product code doesn't exist, the vending machine informs the customer and returns them to the Purchase menu.
    //If a product is currently sold out, the vending machine informs the customer and returns them to the Purchase menu.
    //If a customer selects a valid product, it's dispensed to the customer.

    Scanner userInput = new Scanner(System.in);
    String selection = userInput.nextLine();


    if(selection = product code ){
        do dispense method
    } else if (selection = sold out product){
        System.out.println("Product sold out");
    }else if (selection != a valid product) {
        System.out.println("Product doesn't exist");
    }

    return user to purchase menu



}


