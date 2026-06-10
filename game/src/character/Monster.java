package character;

import java.util.ArrayList;

import inventory.Item;

public abstract class Monster extends Character{
	
	private int droppedXP;
	private ArrayList<Item> droppedItems;
	
	public Monster(String name, int health, int maxHealth, int strength, int agility,  int droppedXP) {
		super(name, health, maxHealth, strength, agility);
		this.setDroppedXP(droppedXP);
		this.droppedItems = new ArrayList<>();
	}
	



	@Override
	public String toString() {
		return "Name: " + getName() + "\n" +
				"Health: " + getHealth() + "/" + getMaxHealth() + "\n" +
				"Strength: " + getStrength() + "\n";
				
				
	}


	@Override
	public int calculateTotalDamage() {
				return this.getStrength();
	}
	public abstract void lastStance();
	

	public int getDroppedXP() {
		return droppedXP;
	}
	public void addDrop(Item item) {
		this.droppedItems.add(item);
	}


	public void setDroppedXP(int droppedXP) {
		this.droppedXP = droppedXP;
	}
	
	public ArrayList<Item> getDroppedItems(){
		return droppedItems;
	}
}


