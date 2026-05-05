package inventory;

import character.Player;

public class Demo {
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		Item item1 = new Item("Apple", 32);
		Item item2 = new Weapon("Sword", 1, 100, 10, "Melee");
		Fruit fruit1 = new Fruit("Obst", "Banana", 32);
		inventory.addItem(item1);
		inventory.addItem(item2);
		inventory.addItem(fruit1);
		//inventory.displayInventory();
		//System.out.println(inventory.maxInventorySpace());
		inventory.useItem("Apple");
		inventory.damageItem("Sword", 50);
		//inventory.displayInventory();
		
		Player player1 = new Player("destroyer", "Elf", 75, 100);
		player1.standardEquipment();
		System.out.println(player1);
		player1.levelUp(3399);
		System.out.println(player1);
		
	}
}
