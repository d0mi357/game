package shop;

import java.util.Scanner;

import character.Player;
import persistence.SaveGameManager;
import util.InputHelper;

public class StartPoint {
	
	private Scanner scanner = new Scanner(System.in);
	private Shop shop = new Shop();
	
	private SaveGameManager saveManager;
	private InputHelper input = new InputHelper();
	
	public StartPoint(SaveGameManager saveManager) {
		this.saveManager = saveManager;
	}
	
	public boolean enter(Player player) {
		
		boolean running = true;
		
		while(running) {
			System.out.println("\n===== START POINT =====");
            System.out.println("1. Open Shop");
            System.out.println("2. Enter Dungeon");
            System.out.println("3. Inventory");
            System.out.println("4. Character");
            System.out.println("5. Upgrade Stats");
            System.out.println("6. Save");
            System.out.println("7. Exit");
			
			int choice = input.readInt(">");
			
			switch(choice) {
			case 1:
				shop.open(player);
				break;
			case 2:
				return true;
			case 3:
				System.out.println("DEBUG CASE 3");
				player.getInventory().displayInventory();
				player.equipment.displayCombatGear();
				break;
			case 4:
				player.displayStatus();
				break;
			case 5:
				this.upgradeStats(player);
				break;
			case 6:
				this.saveManager.savePlayer(player);
				break;
			case 7:
				return false;		
			default:
				System.out.println("Invalid choice");
				
			}
			
		}
		return false;
	}
	
	private void upgradeStats(Player player) {
		
		System.out.println("\n===== UPGRADE =====");

	    System.out.println("Available Points: " + player.getStatPoints());
	    
	    System.out.println("1. Strength");
	    System.out.println("2. Agility");
	    System.out.println("3. Max Health");
	    
	    int choice = input.readInt(">");
	    
	    player.spendStatPoint(choice);
	    
	}
	

}
