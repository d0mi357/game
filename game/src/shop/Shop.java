package shop;

import java.util.ArrayList;
import java.util.Scanner;

import character.Player;
import inventory.Bandage;
import inventory.HealingPotion;
import inventory.Item;
import util.InputHelper;

public class Shop {
	
	private ArrayList<Item> shopItems = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);
	private InputHelper input = new InputHelper();
	
	public Shop() {
		shopItems.add(new HealingPotion("Small Healing Potion", 3, 10, 3, 10));
		shopItems.add(new Bandage("Small Bandage", 2, 25, 25));
	}
	
	public void open(Player player) {
		
		boolean running = true;
		while(running) {
			System.out.println("\n===== SHOP =====");
            System.out.println("Gold: " + player.getGold());
            System.out.println("1. Buy Item");
            System.out.println("2. Sell Item");
            System.out.println("3. Leave Shop");
            
            int choice = input.readInt(">");
            
            switch(choice) {
            case 1:
            	this.buyItem(player);
            	break;
            case 2:
            	sellItem(player);
            	break;
            case 3:
            	running = false;
            	break;
            default: 
            	System.out.println("Invalid choice");	
            }
		}
	}
	
	private void buyItem(Player player) {
		
		System.out.println("\nItems for sale: ");
		
		for(int i = 0;i < this.shopItems.size();i++) {
			System.out.println((i + 1) +". " + this.shopItems.get(i));
		}
		
		
		int choice = input.readInt(">") - 1;
		
		if(choice >= 0 && choice < this.shopItems.size()) {
			Item item = this.shopItems.get(choice);
			
			player.normalInventory.addItem(item);
			System.out.println("Bought: " + item.getName());
			player.setGold(player.getGold() - (item.getWorth() * item.getQuantity()));
		}
		
	}
	
	
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
		
	
	
}
