package character;

import java.util.Random;

public abstract class Character {

	private final String name;
	private int health;
	private final int maxHealth;
	private boolean alive;
	private int strength;
	private int agility;
	
	private static final Random random = new Random();

	public Character(String name, int health, int maxHealth, int strength, int agility) {
		this.name = name;
		this.health = health;
		this.maxHealth = maxHealth;
		this.setAlive(true);
		this.setStrength(strength);
		this.agility = agility;
	}
	

	public void takesDamage(int damage) {
		health = Math.max(0, health - damage);
		alive = health > 0;
	}

	public void heal(int amount) {
		health = Math.min(maxHealth, health + amount);
		alive = health > 0;
	}
	
	public boolean canDodge() {
		int dodgeChance = Math.min(this.getAgility()*5, 50);
		int roll = random.nextInt(100);
		return roll < dodgeChance;
	}
	
	

	public abstract int calculateTotalDamage();

	public boolean isAlive() {
		return health > 0;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public abstract String toString();

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

}
