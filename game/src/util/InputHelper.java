package util;

import java.util.Scanner;

public class InputHelper {
	
	private Scanner scanner;
	
	public InputHelper() {
		this.scanner = new Scanner(System.in);
	}
	
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
