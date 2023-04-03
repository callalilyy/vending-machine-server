package com.techelevator.view;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;

public class TransactionsTest {

    private Transactions transactions;

    @Before
    public void setup() {
        transactions = new Transactions();
    }

    @Test
    public void feedMoney_shouldIncreaseCurrentBalance() {
        transactions.feedMoney(5.0);
        Assert.assertEquals(5.0, transactions.getCurrentBalance(), 0.001);
    }

    @Test
    public void purchaseProduct_shouldDecreaseCurrentBalance() {
        Product product = new Chips("A1", "Doritos", 1.50, 5);
        transactions.feedMoney(2.0);
        transactions.purchaseProduct(product);
        Assert.assertEquals(0.50, transactions.getCurrentBalance(), 0.001);
    }

    @Test
    public void returnChange_shouldReturnCorrectChange() {
        transactions.feedMoney(5.0);
        Map<String, Integer> change = transactions.returnChange();
        Assert.assertEquals(20, (int)change.get("quarters"));
        Assert.assertEquals(0, (int)change.get("dimes"));
        Assert.assertEquals(0, (int)change.get("nickels"));
    }

    @Test
    public void resetCurrentBalance_shouldSetCurrentBalanceToZero() {
        transactions.feedMoney(5.0);
        transactions.resetCurrentBalance();
        Assert.assertEquals(0, transactions.getCurrentBalance(), 0.001);
    }
}
