package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TransactionsTest {

    Transactions transactions;

    @Before
    public void setup() {
        transactions = new Transactions();
    }

    @Test
    public void enter_10_dollars_to_update_current_balance_to_10(){
        //Arrange
        transactions.feedMoney(10);
        Assertions.assertEquals(10, transactions.getCurrentBalance());

    }

 public void test(){

 }

}
