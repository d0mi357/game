package character;

import java.util.ArrayList;

import inventory.Inventory;
import inventory.Item;
import inventory.Weapon;

public class Player extends Character{
	
	public Inventory normalInventory;
	//public Inventory protectiveGear;
	public ArrayList<Weapon> combatGear;
	
	private String race;
	private int level;
	private int xp;
	private int max_xp = 1000;
	private int gold;
	private int equippedIndex = -1;
	private final int DAMAGEUSEITEM = 1;
	
	public Player(String name, String race, int health, int maxHealth, int strength) {
		super(name, health, maxHealth, strength);
		this.normalInventory = new Inventory();
		this.combatGear = new ArrayList<>();
		this.race = race;
	}
	
	public void addWeapon(Weapon weapon) {
		if(combatGear.size()<3) {
			combatGear.add(weapon);
		}
	}
	
	public void displayCombatGear() {
		for(Weapon w : combatGear) {
			System.out.println(w.toString()); 
		}
	}
	public void damageCombatItem() {
		this.getEquippedWeapon().damageDurability(DAMAGEUSEITEM);
	}

	
	public void standardEquipment() {
		this.level = 1;
		this.xp = 0;	
		this.gold = 100;
		Weapon weapon = new Weapon("Sword", 1, 100, 5, "Melee");
		this.combatGear.add(weapon);
		this.normalInventory.displayInventory();
		
	}
	public void equipWeapon(int index) {
		if(index >= 0 && index < combatGear.size())
			equippedIndex = index;
	}
	
	public Weapon getEquippedWeapon() {
		return (equippedIndex >= 0) ? combatGear.get(equippedIndex) : null;
	}
	public String getEquppedWeaponName() {
		return combatGear.get(equippedIndex).getName();	
		}
	
	public void showEquippedWeapon() {
		System.out.println(this.getEquippedWeapon().getName());
	}
	
	public int getIndexCombatGear(String name) {
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("Weapon name can not be empty!");
		}
		for (int i = 0; i < combatGear.size(); i++) {
	        Weapon w = combatGear.get(i);
	        if (w.getName().equalsIgnoreCase(name)) {
	            return i;               // Treffer → Index zurückgeben
	        }
	    }
	    return -1;                      // kein Treffer
	}
	
	public int calculateTotalDamage() {
		int total = this.getStrength();
		Weapon w = getEquippedWeapon();
		if(w != null) {
			total += w.getAttack();
		}
		return total;
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
