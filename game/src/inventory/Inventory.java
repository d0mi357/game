package inventory;

import java.util.ArrayList;

public class Inventory {
	public String showInventory = null;
	private ArrayList<Item> items;
	private final int MAX_SPACE = 30;
	
	public Inventory() {
		items = new ArrayList<>();
	}
	
		public boolean maxInventorySpace() {
		if(this.items.size()<= MAX_SPACE) return true;
		else return false;
	}
	
	public void addItem(Item item) {
		if(this.maxInventorySpace() == true) {
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
			if(item.getName().equals(name)) {
				//index = items.indexOf(item);
				m = item;
			}
		}
		//return index;
		return m;
	}
	
	public void damageItem(String name, int amaount) {
		 Item item = findItem(name);
		 if(item instanceof UseableItem) {
			 UseableItem useableitem = (UseableItem) item;
			 useableitem.damageDurability(amaount);
		 }
		 
	}
	
	public void deleteItem(String name) {
		items.remove(findItem(name));
	}
	
	public void displayInventory() {
		for(Item item : items) {
			System.out.println(item.toString()); 
		}
	}
}
