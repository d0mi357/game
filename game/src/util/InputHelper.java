package util;

import java.util.Scanner;

/**
 * Utility class for reading and validating user input.
 * Provides methods for safely reading integers and strings
 * from the console.
 */

public class InputHelper {
	
	private Scanner scanner;
	
	/**
	 * Creates a new input scanner.
	 */
	
	public InputHelper() {
		this.scanner = new Scanner(System.in);
	}
	
	
	/**
	 * Reads an integer value from the console.
	 * Repeats the input request until a valid number is entered.
	 *
	 * @param prompt message displayed to the user
	 * @return the entered integer
	 */
	
	public int readInt(String message) {
		while(true) {
			System.out.println(message);
			
			try {
				return Integer.parseInt(scanner.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Please enter a valid number!");
			}
		}
	}

}
