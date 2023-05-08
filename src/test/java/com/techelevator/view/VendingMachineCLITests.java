package com.techelevator.view;

import com.techelevator.VendingMachineCLI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

public class VendingMachineCLITests {
    private Menu menu;
    private VendingMachineCLI vendingMachineCLI;
    private Transactions transaction;
    private Inventory inventory;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setup() {
        menu = new Menu(System.in, new PrintStream(outputStream));
        transaction = new Transactions();
        inventory = new Inventory();
        vendingMachineCLI = new VendingMachineCLI(menu);
    }
    @Test
    public void feedMoneyTest() {
        transaction.feedMoney(10);
        double currentBalance = transaction.getCurrentBalance();
        Assert.assertEquals(10, currentBalance, 0.01);
    }

    @Test
    public void returnChangeTest() {
        transaction.feedMoney(10);
        Map<String, Integer> change = transaction.returnChange();
        Assert.assertEquals(40, (int) change.get("quarters"));
        Assert.assertEquals(0, (int) change.get("dimes"));
        Assert.assertEquals(0, (int) change.get("nickels"));
    }

    @Test
    public void resetCurrentBalanceTest() {
        transaction.feedMoney(5);
        transaction.resetCurrentBalance();
        double currentBalance = transaction.getCurrentBalance();
        Assert.assertEquals(0, currentBalance, 0.01);
    }
}
