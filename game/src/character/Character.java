package character;

import java.util.Random;


/**
 * Abstract base class for all characters in the game.
 * Stores common attributes such as health, strength and agility.
 * Extended by Player and Monster.
 */

public abstract class Character {

	private final String name;
	private int health;
	private int maxHealth;
	private boolean alive;
	private int strength;
	private int agility;
	
	private static final Random random = new Random();
	
	/**
	 * Creates a new character.
	 *
	 * @param name character name
	 * @param health current health points
	 * @param maxHealth maximum health points
	 * @param strength attack strength
	 * @param agility agility used for dodge chance
	 */

	public Character(String name, int health, int maxHealth, int strength, int agility) {
		this.name = name;
		this.health = health;
		this.maxHealth = maxHealth;
		this.setAlive(true);
		this.setStrength(strength);
		this.agility = agility;
	}
	
	/**
	 * Reduces the character's health by the given damage amount.
	 * Health cannot fall below zero.
	 *
	 * @param damage damage received
	 */

	public void takesDamage(int damage) {
		health = Math.max(0, health - damage);
		alive = health > 0;
	}
	
	/**
	 * Restores health points.
	 * Health cannot exceed the maximum health value.
	 *
	 * @param amount amount of health restored
	 */

	public void heal(int amount) {
		health = Math.min(maxHealth, health + amount);
		alive = health > 0;
	}
	
	/**
	 * Determines whether the character successfully dodges an attack.
	 * The dodge chance is based on agility.
	 *
	 * @return true if the attack is dodged, otherwise false
	 */
	
	public boolean canDodge() {
		int dodgeChance = Math.min(this.getAgility()*5, 50);
		int roll = random.nextInt(100);
		return roll < dodgeChance;
	}
	
	/**
	 * Calculates the total damage dealt by the character.
	 *
	 * @return total damage value
	 */

	public abstract int calculateTotalDamage();
	
	/**
	 * Checks whether the character is still alive.
	 *
	 * @return true if health is above zero
	 */

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
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
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
