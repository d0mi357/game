package shop;

import java.util.Scanner;

import character.Player;
import persistence.SaveGameManager;
import util.AutoSave;
import util.InputHelper;

/**
 * Represents the main hub of the game.
 * Players can access the shop, manage their character,
 * view inventory information and enter the dungeon.
 */

public class StartPoint {
	
	private Scanner scanner = new Scanner(System.in);
	private Shop shop = new Shop();
	
	private InputHelper input = new InputHelper();
	
	/**
	 * Creates a new start point and initializes the shop.
	 */
	
	public StartPoint() {
		
	}
	
	
	/**
	 * Displays the main menu and handles player actions.
	 *
	 * @param player the current player
	 * @return true if the player chooses to enter the dungeon,
	 *         false if the player exits the game
	 */
	
	public boolean enter(Player player) {
		
		boolean running = true;
		
		while(running) {
			System.out.println("\n===== START POINT =====");
            System.out.println("1. Open Shop");
            System.out.println("2. Enter Dungeon");
            System.out.println("3. Inventory");
            System.out.println("4. Sort Inventory");
            System.out.println("5. Character");
            System.out.println("6. Upgrade Stats");
            System.out.println("7. Save");
            System.out.println("8. Exit");
			
			int choice = input.readInt(">");
			
			switch(choice) {
			case 1:
				shop.open(player);
				break;
			case 2:
				return true;
			case 3:
				player.getInventory().displayInventory();
				player.equipment.displayCombatGear();
				break;
			case 4:
				player.normalInventory.sortInventoryAlphabetically();
				player.normalInventory.displayInventory();
				break;
			case 5:
				player.displayStatus();
				break;
			case 6:
				this.upgradeStats(player);
				AutoSave.save(player);
				break;
			case 7:
				AutoSave.save(player);
				break;
			case 8:
				AutoSave.save(player);
				return false;	
			default:
				System.out.println("Invalid choice");
				
			}
			
		}
		return false;
	}
	
	/**
	 * Allows the player to spend available stat points
	 * to improve character attributes.
	 *
	 * @param player the player upgrading their stats
	 */
	
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
