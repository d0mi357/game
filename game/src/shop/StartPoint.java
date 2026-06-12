package shop;

import java.util.Scanner;

import character.Player;

public class StartPoint {
	
	private Scanner scanner = new Scanner(System.in);
	private Shop shop = new Shop();
	
	public void enter(Player player) {
		
		boolean running = true;
		
		while(running) {
			System.out.println("\n===== START POINT =====");
            System.out.println("1. Open Shop");
            System.out.println("2. Enter Dungeon");
            System.out.print("> ");
			
			int choice = Integer.parseInt(scanner.nextLine());
			
			switch(choice) {
			case 1:
				shop.open(player);
				break;
			case 2:
				running = false;
				break;
			default:
				System.out.println("Invalid choice");
				
			
			
			
			}
			
		}
	}
	

}
