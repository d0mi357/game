package character;

public class Monster extends Character{
	
	
	
	public Monster(String name, int health, int maxHealth, int strength) {
		super(name, health, maxHealth, strength);
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
}
