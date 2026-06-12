package inventory;

import java.util.ArrayList;

public class Inventory {
	public String showInventory = null;
	private ArrayList<Item> items;
	private final int MAX_SPACE = 30;
	
	public Inventory() {
		items = new ArrayList<>();
	}
	
	public boolean hasFreeSpace() {
		if(this.items.size()<= MAX_SPACE) return true;
		else return false;
	}
	
	public void addItem(Item item) {
		if(this.hasFreeSpace() == true) {
			items.add(item);
			//System.out.println("Item erfolgreich aufgenommen!");
		}
	}	
	public void useItem(String name) {	
			for(Item item : items) {
				if(item.getName().equals(name)) {
					item.decreaseQuantity();	
			}
		}
	}
	
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
	
	public Item getItemByIndex(int idx) {

	    if(idx >= 0 && idx < items.size()) {
	        return items.get(idx);
	    }

	    return null;
	}
	
	public void damageItem(String name, int amount) {
		 Item item = findItem(name);
		 if(item instanceof UseableItem) {
			 UseableItem useableitem = (UseableItem) item;
			 useableitem.damageDurability(amount);
		 }
		 
	}
	
	public void deleteItem(Item item) {
		items.remove(item);
	}
	
	public void removeEmptyItem() {
		items.removeIf(item -> item.getQuantity() <= 0);
	}
	
	
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
		
		
	}

