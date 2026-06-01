package character;

public class Monster extends Character{
	
	private int droppedXP;
	
	public Monster(String name, int health, int maxHealth, int strength, int droppedXP) {
		super(name, health, maxHealth, strength);
		this.setDroppedXP(droppedXP);
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


	public int getDroppedXP() {
		return droppedXP;
	}


	public void setDroppedXP(int droppedXP) {
		this.droppedXP = droppedXP;
	}
}
