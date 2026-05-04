package character;

public abstract class Character {
	
	private String name;
	private String race;
	private int health;
	private int maxHealth;
	private boolean alive;
	
	public Character(String name, String race, int health, int maxHealth) {
		this.race = race;
		this.health = health;
		this.maxHealth = maxHealth;
		alive = true;
	}
	
	public void takesDamage(int amount) {
		if(this.health > 0) {
			this.health -= amount;
		}
		else if(this.health <= 0) {
			this.alive = false;
		}
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
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
	
	
	
}
