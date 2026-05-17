package inventory;

import character.Monster;
import character.Player;
import combat.Combat;

public class Demo {
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		Item item1 = new Fruit("Obst", "Apple", 32);
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
		
		Player player1 = new Player("destroyer", "Elf", 75, 100, 10);
		player1.standardEquipment();
		
		Monster monster1 = new Monster("Goblin", 50, 60, 10);
		player1.equipWeapon(0);
		System.out.println(player1.calculateTotalDamage());
		System.out.println(monster1.toString());
		Combat battle1 = new Combat(player1, monster1);
		battle1.startBattle();
		
		
		
		
		
		
	}
}
