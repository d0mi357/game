package shop;

import java.util.ArrayList;
import java.util.Scanner;

import inventory.HealingPotion;
import inventory.Item;

public class Shop {
	
	private ArrayList<Item> shopItems = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);
	
	public Shop() {
		shopItems.add(new HealingPotion("Small Healing Potion", 3, 3, 10));
	}
	
	
}
