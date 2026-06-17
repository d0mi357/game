package shop;

import java.util.ArrayList;
import java.util.Scanner;

import character.Player;
import items.Bandage;
import items.HealingPotion;
import items.Item;
import items.Weapon;
import util.AutoSave;
import util.InputHelper;

/**
 * Represents the in-game shop.
 * Players can buy and sell items and weapons using gold.
 */

public class Shop {
	
	private ArrayList<Item> shopItems = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);
	private InputHelper input = new InputHelper();
	
	/**
	 * Creates a new shop and initializes the available products.
	 */
	 
	public Shop() {
		shopItems.add(new HealingPotion("Small Healing Potion", 3, 10, 3, 10));
		shopItems.add(new Bandage("Small Bandage", 2, 15, 25));
		shopItems.add(new Bandage("Large Bandage", 2, 30, 30));
		shopItems.add(new Weapon("Long Sword", 1, 100, 200, 25, "Close Combat"));
		shopItems.add(new Weapon("Knight Sword", 1, 150, 300, 50, "Close combat" ));
		shopItems.add(new Weapon("Dragon Slayer", 1, 350, 400, 70, "Close combat" ));
	}
	
	/**
	 * Opens the shop menu and allows the player to buy or sell items.
	 *
	 * @param player the player interacting with the shop
	 */
	
	public void open(Player player) {
		
		boolean running = true;
		while(running) {
			System.out.println("\n===== SHOP =====");
            System.out.println("Gold: " + player.getGold());
            System.out.println("1. Buy Item");
            System.out.println("2. Sell Inventory Item");
            System.out.println("3. Sell Combat Inventory Item");
            System.out.println("4. Leave Shop");
            
            int choice = input.readInt(">");
            
            switch(choice) {
            case 1:
            	this.buyItem(player);
            	AutoSave.save(player);
            	break;
            case 2:
            	this.sellItem(player);
            	AutoSave.save(player);
            	break;
            case 3:
            	this.sellWeapon(player);
            	AutoSave.save(player);
            	break;
            case 4:
            	running = false;
            	break;
            default: 
            	System.out.println("Invalid choice");	
            }
		}
	}
	
	/**
	 * Allows the player to purchase an item from the shop.
	 * The item's cost is deducted from the player's gold.
	 *
	 * @param player the player buying the item
	 */
	
	private void buyItem(Player player) {
		
		System.out.println("\nItems for sale: ");
		
		for(int i = 0;i < this.shopItems.size();i++) {
			Item item = shopItems.get(i);
			
			System.out.printf("%d. %s x%d - %d Gold%n",
		            i + 1,
		            item.getName(),
		            item.getQuantity(),
		            item.getWorth());
			
		}
		
		
		
		int choice = input.readInt(">") - 1;
		
		if(choice >= 0 && choice < this.shopItems.size()) {
			Item item = this.shopItems.get(choice);
			int price = item.getWorth() * item.getQuantity();
			if(player.getGold() < price) {
				System.out.println("You cann not afford this item!");
				return;
			}
			
			if(item instanceof Weapon weapon) {
				player.equipment.addWeapon(new Weapon(
												weapon.getName(),
												weapon.getQuantity(),
												weapon.getWorth(),
												weapon.getDurability(),
												weapon.getAttack(),
												weapon.getType()
												));
												
			}
			else {
				player.normalInventory.addItem(item);					
			}	
			System.out.println("Bought: " + item.getName());
			player.setGold(player.getGold() - price);
		}
		
	}
	
	/**
	 * Allows the player to sell an item from their inventory.
	 * The player receives the item's gold value.
	 *
	 * @param player the player selling the item
	 */
	
	private void sellItem(Player player) {
		player.normalInventory.displayInventory();
		
		System.out.println("Which Item do you want to sell?");
		
		int itemidx = input.readInt(">");
		
		Item item = player.normalInventory.getItemByIndex(itemidx -1);
		
		if(item != null) {
			player.normalInventory.deleteItem(item);
			player.setGold(player.getGold() + (item.getWorth() * item.getQuantity()));
			
			System.out.println("Sold: " + item.getName());
		} else {
			System.out.println("Item not found");
		}
	}
	
	/**
	 * Allows the player to sell a weapon from the combat inventory.
	 * Equipped weapons cannot be sold.
	 *
	 * @param player the player selling the weapon
	 */
	
	private void sellWeapon(Player player) {
		player.equipment.displayCombatGear();
		
		System.out.println("Which weapon do you want to sell");
		int choice = input.readInt(">")-1;
		Weapon weapon = (Weapon) player.equipment.getCombatGear().get(choice);
		
		/*System.out.println("Selected: " + weapon.getName());
		System.out.println("Equipped: " + player.equipment.getEquippedWeapon().getName());

		System.out.println(
		    player.equipment.getEquippedWeapon() == weapon
		);*/
		
		
		if(this.compareWeaponNames(player, weapon)) {
			System.out.println("You cannot sell your equipped weapon!");
			return;
		}
		if(weapon != null) {
			player.equipment.deleteCombatGear(choice);
			player.setGold(player.getGold() + weapon.getWorth());
			System.out.println("Sold: " + weapon.getName());
		}
		
	}
		
	private boolean compareWeaponNames(Player player, Weapon weapon) {
		return player.equipment.geteEuippedWeapon().getName().equalsIgnoreCase(weapon.getName());
	}
	
}
