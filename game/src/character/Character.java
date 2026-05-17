package character;

public abstract class Character {
	
	private String name;
	private int health;
	private int maxHealth;
	private boolean alive;
	private int strength;
	
	public Character(String name, int health, int maxHealth, int strength) {
		this.name = name;
		this.health = health;
		this.maxHealth = maxHealth;
		this.setAlive(true);
		this.setStrength(strength);
	}
	
	public void takesDamage(int amount) {
		health = Math.max(0, health-amount);
		alive = health > 0;
	}
	
	public void heal(int amount) {
		health = Math.min(maxHealth, health + amount);
		alive = health > 0;
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

	public void setName(String name) {
		this.name = name;
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
	
}
