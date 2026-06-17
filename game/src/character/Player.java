package character;

import java.util.ArrayList;


import inventory.Equipment;
import inventory.Inventory;
import items.Weapon;


/**
 * Represents the player character.
 * Stores inventory, equipment, stats, experience and active effects.
 */

public class Player extends Character{
	
	public Inventory normalInventory;
	//public Inventory protectiveGear;	
	public Equipment equipment;
	private transient ArrayList<ActiveEffect> activeEffects = new ArrayList<>();
	
	private String race;
	private int level;
	private int xp;
	private int max_xp = 200;
	private int gold;
	private int statPoints = 0;
	
	public Player(){
		super("Unbekannter Held", 100, 100, 10, 10);
		this.normalInventory = new Inventory();
		this.equipment = new Equipment();
	}
	
	public Player(String name, String race, int health, int maxHealth, int strength, int agility) {
		super(name, health, maxHealth, strength, agility);
		this.normalInventory = new Inventory();
		this.race = race;
		this.equipment = new Equipment();
	}
	
	/**
     * Sets the starting values for a new player.
     */
	
	public void startStatus() {
		this.level = 1;
		this.xp = 0;	
		this.gold = 100;
		this.normalInventory.displayInventory();
		
	}
	
	/**
     * Calculates the player's total damage including equipped weapon damage.
     *
     * @return total damage dealt by the player
     */

	
	public int calculateTotalDamage() {
		int total = this.getStrength();
		Weapon w = equipment.getEquippedWeapon();
		if(w != null) {
			total += w.getAttack();
		}
		return total;
	}
	
	/**
	 * Adds a temporary effect to the player.
	 *
	 * @param effect the effect that should be applied
	 */
	
	public void addEffect(ActiveEffect effect) {
		activeEffects.add(effect);
	}
	
	/**
	 * Applies all active effects and removes expired effects.
	 */
	
	public void applyEffects() {
		for(int i = activeEffects.size() -1; i >= 0; i--) {
			ActiveEffect effect = activeEffects.get(i);
			
			effect.apply(this);
			System.out.println(effect.getName() + " heals");
			
			if(effect.isFinished()) {
				activeEffects.remove(i);
				System.out.println(effect.getName() + " effect ended");
			}
		}
	}
	
	/**
     * Displays the player's current status.
     */
	
	public void displayStatus() {
	    System.out.println("\n========== CHARACTER ==========");
	    System.out.println("Name:      " + this.getName());
	    System.out.println("Race:      " + this.getRace());
	    System.out.println("Level:     " + this.level);
	    System.out.println("XP:        " + this.xp + "/" + this.max_xp);
	    System.out.println("Gold:      " + this.gold);
	    System.out.println("Health:    " + this.getHealth() + "/" + this.getMaxHealth());
	    System.out.println("Strength:  " + this.getStrength());
	    System.out.println("Agility:   " + this.getAgility());

	    if(equipment.getEquippedWeapon() != null) {
	        System.out.println("Weapon:    "
	                + equipment.getEquippedWeapon().getName());
	    }

	}
	
	/**
     * Uses one available stat point to increase a selected player attribute.
     *
     * @param choice selected attribute:
     *               1 = strength, 2 = agility, 3 = max health
     */
	
	public void spendStatPoint(int choice) {
		
		if(statPoints <= 0) {
			System.out.println("No stat points available!");
			return;
		}
		
		switch(choice) {
		case 1:
			this.setStrength(this.getStrength()+1);
			System.out.println("Strength increased to " + this.getStrength());
			break;
		case 2:
			this.setAgility(this.getAgility()+1);
			System.out.println("Agility increased to " + this.getAgility());
			break;
		case 3:
			this.setMaxHealth(this.getMaxHealth()+10);
			System.out.println("Health increased to " + this.getMaxHealth());
			break;
		default:
			System.out.println("Invalid choice");
			return;
			
		}
		this.statPoints--;
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
	
	/**
	 * Increases the player's level and grants a stat point.
	 * @param amount
	 */
	
	public void levelUp(int amount) {
		this.xp += amount;
		
		while(this.xp>=this.max_xp) {
		if(xp>=max_xp) {
			this.xp = this.xp - this.max_xp;
			this.max_xp = this.max_xp * 2;
			this.level ++;
			
			System.out.println("\n===== LEVEL UP =====");
		    System.out.println("You reached level " + level + "!");
		    System.out.println("You received 1 stat point!");
		    this.statPoints++;
		    
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
	
	public Inventory getInventory() {
		return this.normalInventory;
	}

	public int getStatPoints() {
		return statPoints;
	}

	public void setStatPoints(int statPoints) {
		this.statPoints = statPoints;
	}
	
	

}
