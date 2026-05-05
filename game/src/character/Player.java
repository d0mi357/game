package character;

import inventory.Inventory;
import inventory.Weapon;

public class Player extends Character{
	
	public Inventory inv;
	private int level;
	private int xp;
	private int max_xp = 1000;
	private int gold;
	
	public Player(String name, String race, int health, int maxHealth) {
		super(name, race, health, maxHealth);
		this.inv = new Inventory();
	}
	
	public void standardEquipment() {
		this.level = 1;
		this.xp = 0;	
		this.gold = 100;
		Weapon weapon = new Weapon("Sword", 1, 100, 5, "Melee");
		this.inv.addItem(weapon);
		this.inv.displayInventory();
		
	}
	
	public String toString() {
		return "Name: " + getName() + "\n" +
				"Race: " + getRace() + "\n" +
				"Health: " + getHealth() + "/" + getMaxHealth() + "\n" +
				"XP: " + this.xp + "/" + this.max_xp + "\n" +
				"Level: " + this.level + "\n" +
				"Gold: " + this.gold;
	}
	public void levelUp(int amount) {
		this.xp += amount;
		while(this.xp>this.max_xp) {
		if(xp>=max_xp) {
			this.xp = this.xp - this.max_xp;
			this.max_xp = this.max_xp * 2;
			this.level ++;
			}
		}
	}
	
	
	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}



	public int getXp() {
		return xp;
	}



	public void setXp(int xp) {
		this.xp = xp;
	}



	public int getGold() {
		return gold;
	}



	public void setGold(int gold) {
		this.gold = gold;
	}

}
