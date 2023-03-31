//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.techelevator;

import com.techelevator.view.Inventory;
import com.techelevator.view.Menu;
import com.techelevator.view.Product;
import com.techelevator.view.Transactions;
import java.util.Map;

public class VendingMachineCLI {
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[]{"Display Vending Machine Items", "Purchase", "Exit"};
	private Menu menu;
	private Inventory inventory;
	private Transactions transaction;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.inventory = new Inventory();
		this.transaction = new Transactions();
	}

	public void run() {
		while(true) {
			String choice = (String)this.menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals("Display Vending Machine Items")) {
				this.inventory.displayProducts();
			} else if (choice.equals("Purchase")) {
				this.handlePurchase();
			} else if (choice.equals("Exit")) {
				return;
			}
		}
	}

	private void handlePurchase() {
		double currentBalance = this.transaction.getCurrentBalance();
		String[] purchaseMenuOptions = new String[]{"Feed Money", "Select Product", "Finish Transaction"};

		while(true) {
			while(true) {
				System.out.printf("Current Money Provided: $%.2f\n\n", currentBalance);
				String choice = (String)this.menu.getChoiceFromOptions(purchaseMenuOptions);
				if (choice.equals("Feed Money")) {
					this.handleFeedMoney();
					currentBalance = this.transaction.getCurrentBalance();
				} else if (choice.equals("Select Product")) {
					this.handleSelectProduct();
					currentBalance = this.transaction.getCurrentBalance();
				} else if (choice.equals("Finish Transaction")) {
					Map<String, Integer> change = this.transaction.returnChange();
					System.out.println("Here's your change:");
					System.out.printf("%d quarters, %d dimes, %d nickels\n\n", change.get("quarters"), change.get("dimes"), change.get("nickels"));
					this.transaction.resetCurrentBalance();
					return;
				}
			}
		}
	}

	private void handleFeedMoney() {
		double amount = this.menu.getDoubleInput("Enter dollar amount to feed: ");
		if (amount != 1.0D && amount != 5.0D && amount != 10.0D && amount != 20.0D) {
			System.out.println("Invalid amount. Please enter 1, 5, 10, or 20 dollars.");
		} else {
			this.transaction.feedMoney(amount);
		}

	}

	private void handleSelectProduct() {
		this.inventory.displayProducts();
		String slotNumber = this.menu.getStringInput("Enter product code: ");
		Product product = this.inventory.selectProduct(slotNumber);
		if (product == null) {
			System.out.println("Invalid product code.");
		} else if (product.getQuantity() == 0) {
			System.out.println("SOLD OUT.");
		} else if (this.transaction.getCurrentBalance() < product.getPrice()) {
			System.out.println("Insufficient funds.");
		} else {
			this.transaction.purchaseProduct(product);
			System.out.println(product.getPurchaseMessage());
			product.setQuantity(product.getQuantity() - 1);
		}

	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
