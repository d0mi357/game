package inventory;

import java.util.ArrayList;

import items.Item;
import items.UseableItem;


/**
 * Represents the player's inventory.
 * Stores items and provides methods for adding, removing,
 * searching and displaying inventory contents.
 */

public class Inventory {
	public String showInventory = null;
	private ArrayList<Item> items;
	private final int MAX_SPACE = 30;
	
	/**
	 * Creates an empty inventory.
	 */
	
	
	public Inventory() {
		items = new ArrayList<>();
	}
	
	/**
	 * Checks whether the inventory has free space available.
	 *
	 * @return true if the inventory is not full, otherwise false
	 */
	
	public boolean hasFreeSpace() {
		if(this.items.size()<= MAX_SPACE) return true;
		else return false;
	}
	
	/**
	 * Adds an item to the inventory if free space is available.
	 *
	 * @param item the item to add
	 */
	
	public void addItem(Item item) {
		if(this.hasFreeSpace() == true) {
			items.add(item);
			//System.out.println("Item erfolgreich aufgenommen!");
		}
	}	
	
	/**
	 * Uses an item by reducing its quantity by one.
	 *
	 * @param name name of the item to use
	 */
	
	public void useItem(String name) {	
			for(Item item : items) {
				if(item.getName().equals(name)) {
					item.decreaseQuantity();	
			}
		}
	}
	
	/**
	 * Searches for an item by name.
	 *
	 * @param name name of the item
	 * @return the matching item or null if not found
	 */
	
	public Item findItem(String name) {
		Item m = null;
		for(Item item : items) {
			if(item.getName().equalsIgnoreCase(name)) {
				//index = items.indexOf(item);
				m = item;
			}
		}
		//return index;
		return m;
	}
	
	/**
	 * Returns an item at the specified inventory position.
	 *
	 * @param idx inventory index
	 * @return the item at the index or null if the index is invalid
	 */
	
	public Item getItemByIndex(int idx) {

	    if(idx >= 0 && idx < items.size()) {
	        return items.get(idx);
	    }

	    return null;
	}
	
	/**
	 * Reduces the durability of a usable item.
	 *
	 * @param name name of the item
	 * @param amount durability damage
	 */
	
	public void damageItem(String name, int amount) {
		 Item item = findItem(name);
		 if(item instanceof UseableItem) {
			 UseableItem useableitem = (UseableItem) item;
			 useableitem.damageDurability(amount);
		 }
		 
	}
	
	/**
	 * Removes an item from the inventory.
	 *
	 * @param item item to remove
	 */
	
	public void deleteItem(Item item) {
		items.remove(item);
	}
	
	/**
	 * Removes all items with a quantity of zero or less.
	 */
		
	public void removeEmptyItem() {
		items.removeIf(item -> item.getQuantity() <= 0);
	}
	
	/**
	 * Displays all items currently stored in the inventory.
	 */
	
	public void displayInventory() {

	    System.out.println("\n========= INVENTORY =========");

	    for(int i = 0; i < items.size(); i++) {
	        Item item = items.get(i);

	        System.out.printf("[%d] %-20s x%d%n",
	                i + 1,
	                item.getName(),
	                item.getQuantity());
	    }

	    System.out.println("=============================");
	}
	
	/**
	 * Sorts all items in the inventory alphabetically by name.
	 * Uppercase and lowercase letters are ignored.
	 */
	
	public void sortInventoryAlphabetically() {
	    items.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
		}
		
		
	}

