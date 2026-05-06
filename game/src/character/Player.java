package character;

import inventory.Inventory;
import inventory.Weapon;

public class Player extends Character{
	
	public Inventory normalInventory;
	public Inventory protectiveGear;
	public Inventory combatGear;
	
	private String race;
	private int level;
	private int xp;
	private int max_xp = 1000;
	private int gold;
	
	public Player(String name, String race, int health, int maxHealth, int strength) {
		super(name, health, maxHealth, strength);
		this.normalInventory = new Inventory();
		this.protectiveGear = new Inventory();
		this.combatGear = new Inventory();
		this.race = race;
	}
	
	public void standardEquipment() {
		this.level = 1;
		this.xp = 0;	
		this.gold = 100;
		Weapon weapon = new Weapon("Sword", 1, 100, 5, "Melee");
		this.combatGear.addItem(weapon);
		this.normalInventory.displayInventory();
		
	}
	
	public void calculateTotalDamage() {
		
	}
	
	@Override
	public String toString() {
		return "Name: " + getName() + "\n" +
				"Race: " + getRace() + "\n" +
				"Health: " + getHealth() + "/" + getMaxHealth() + "\n" +
				"XP: " + this.xp + "/" + this.max_xp + "\n" +
				"Strength: " + getStrength() + "\n" + 
				"Level: " + this.level + "\n" +
				"Gold: " + this.gold;
	}
	
	//Funktion zum berechnen vom level
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

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

}
