package com.techelevator;

import com.techelevator.view.Inventory;
import com.techelevator.view.Menu;
import com.techelevator.view.Product;
import com.techelevator.view.Transactions;

import java.util.Map;

public class VendingMachineCLI {

	// Define main menu options
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private Menu menu; // Menu object to handle user input
	private Inventory inventory; // Inventory object to manage the vending machine products
	private Transactions transaction; // Transactions object to handle customer transactions

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.inventory = new Inventory();
		this.transaction = new Transactions();
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				inventory.displayProducts(); // Display all products in the vending machine
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				handlePurchase(); // Handle purchase menu options
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				break; // Exit the program
			}
		}
	}

	private void handlePurchase() {
		double currentBalance = transaction.getCurrentBalance();
		String[] purchaseMenuOptions = {"Feed Money", "Select Product", "Finish Transaction"};

		while (true) {
			System.out.printf("Current Money Provided: $%.2f\n\n", currentBalance);
			String choice = (String) menu.getChoiceFromOptions(purchaseMenuOptions);

			if (choice.equals("Feed Money")) {
				handleFeedMoney(); // Prompt the user to feed money into the machine
				currentBalance = transaction.getCurrentBalance(); // Update the current balance
			} else if (choice.equals("Select Product")) {
				handleSelectProduct(); // Prompt the user to select a product
				currentBalance = transaction.getCurrentBalance(); // Update the current balance
			} else if (choice.equals("Finish Transaction")) {
				// Return change to the customer and break out of the purchase loop
				Map<String, Integer> change = transaction.returnChange();
				System.out.println("Here's your change:");
				System.out.printf("%d quarters, %d dimes, %d nickels\n\n", change.get("quarters"), change.get("dimes"), change.get("nickels"));
				break;
			}
		}

		transaction.resetCurrentBalance(); // Reset the current balance to $0
	}

	private void handleFeedMoney() {
		double amount = menu.getDoubleInput("Enter dollar amount to feed: ");

		if (amount == 1 || amount == 5 || amount == 10 || amount == 20) {
			transaction.feedMoney(amount); // Add the specified amount to the current balance
		} else {
			System.out.println("Invalid amount. Please enter 1, 5, 10, or 20 dollars.");
		}
	}

	private void handleSelectProduct() {
		inventory.displayProducts(); // Display all products in the vending machine
		String slotNumber = menu.getStringInput("Enter product code: ");
		Product product = inventory.selectProduct(slotNumber); // Get the product at the specified slot number


		if (product == null) {
			System.out.println("Invalid product code."); // If the product code entered by the user is invalid, this message is printed
		} else if (product.getQuantity() == 0) {
			System.out.println("SOLD OUT."); // If the product is out of stock, this message is printed
		} else if (transaction.getCurrentBalance() < product.getPrice()) {
			System.out.println("Insufficient funds."); // If the user's current balance is less than the price of the product, this message is printed
		} else {
			transaction.purchaseProduct(product); // If the product code is valid, the product is in stock, and the user has enough funds, the product is purchased
			System.out.println(product.getPurchaseMessage()); // A message indicating that the product was purchased is printed
			product.setQuantity(product.getQuantity() - 1); // The quantity of the purchased product is updated
		}
	}




	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

