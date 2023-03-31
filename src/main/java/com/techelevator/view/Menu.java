package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	// Displays a menu with a list of options and returns the user's choice
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	// Prompts the user to enter a whole number and returns it
	public int getWholeNumberFromUser(String message) {
		int userInput = 0;
		boolean valid = false;
		while (!valid) {
			out.print(message);
			out.flush();
			String input = in.nextLine();
			try {
				userInput = Integer.parseInt(input);
				valid = true;
			} catch (NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + input + " is not a valid whole number ***" + System.lineSeparator());
				out.flush();
			}
		}
		return userInput;
	}

	// Prompts the user to enter a money amount and returns it
	public double getMoneyAmountFromUser(String message) {
		double userInput = 0;
		boolean valid = false;
		while (!valid) {
			out.print(message);
			out.flush();
			String input = in.nextLine();
			try {
				userInput = Double.parseDouble(input);
				if (userInput >= 0) {
					valid = true;
				} else {
					out.println(System.lineSeparator() + "*** Amount must be greater than or equal to 0.00 ***" + System.lineSeparator());
					out.flush();
				}
			} catch (NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + input + " is not a valid amount ***" + System.lineSeparator());
				out.flush();
			}
		}
		return userInput;
	}

	// Gets the user's choice from the input and returns it
	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
			out.flush();
		}
		return choice;
	}

	// Displays the menu options to the user
	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	public double getDoubleInput(String message) {
		// Declare and initialize userInput and valid
		double userInput = 0;
		boolean valid = false;
		// Continue looping until userInput is valid
		while (!valid) {
			// Output message to console
			out.print(message);
			out.flush();
			// Get user input as string
			String input = in.nextLine();
			try {
				// Try to parse user input as double
				userInput = Double.parseDouble(input);
				// If userInput is greater than or equal to 0, set valid to true and exit loop
				if (userInput >= 0) {
					valid = true;
				} else {
					// If userInput is less than 0, output error message to console
					out.println(System.lineSeparator() + "*** Amount must be greater than or equal to 0.00 ***" + System.lineSeparator());
					out.flush();
				}
			} catch (NumberFormatException e) {
				// If user input is not a valid double, output error message to console
				out.println(System.lineSeparator() + "*** " + input + " is not a valid amount ***" + System.lineSeparator());
				out.flush();
			}
		}
		// Return valid userInput
		return userInput;
	}

	public String getStringInput(String message) {
		// Output message to console
		out.print(message);
		out.flush();
		// Get user input as string
		String input = in.nextLine();
		// Return user input
		return input;
	}

}
