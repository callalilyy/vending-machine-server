package com.techelevator.view;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


public class InventoryTest {
    private Inventory inventory;

    @Before
    public void setup(){
        inventory = new Inventory();
    }


    @Test
    public void enter_wrong_product_code_returns_invalid_error(){



        //Arrange
        Product expected = null;
        //Act
        Product product = inventory.selectProduct("B12");
        //Assert
        Assert.assertEquals(expected, inventory.selectProduct("B12"));

    }

    @Test
    public void enter_correct_product_code_returns_corresponding_product(){
      Product product = inventory.selectProduct("a3");
      Product expected = inventory.selectProduct("A3");
        Assert.assertEquals(expected, product);
    }





}
