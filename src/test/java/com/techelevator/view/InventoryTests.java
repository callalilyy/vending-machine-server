package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryTests {

    private Inventory inventory;

    @Before
    public void setUp() {
        inventory = new Inventory();
    }

    @Test
    public void selectProduct_validSlotIdentifier_returnsProduct() {
        // Arrange
        String slotIdentifier = "A1";

        // Act
        Product actualProduct = inventory.selectProduct(slotIdentifier);

        // Assert
        Assert.assertEquals("Doritos", actualProduct.getName());
        Assert.assertEquals("Chips", actualProduct.getType());
        Assert.assertEquals(1.35, actualProduct.getPrice(), 0.001);
        Assert.assertEquals(5, actualProduct.getQuantity());
    }

    @Test
    public void selectProduct_invalidSlotIdentifier_returnsNull() {
        // Arrange
        String slotIdentifier = "Z9";

        // Act
        Product actualProduct = inventory.selectProduct(slotIdentifier);

        // Assert
        Assert.assertNull(actualProduct);
    }

    @Test
    public void selectProduct_soldOutProduct_returnsNull() {
        // Arrange
        String slotIdentifier = "A4";
        Product product = inventory.selectProduct(slotIdentifier);

        // Purchase all remaining inventory of the product
        for (int i = 0; i < 5; i++) {
            inventory.selectProduct(slotIdentifier);
        }

        // Act
        Product actualProduct = inventory.selectProduct(slotIdentifier);

        // Assert
        Assert.assertNull(actualProduct);
    }

}