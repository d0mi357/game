package character;

import inventory.HealingPotion;

public class Goblin extends Monster{
	
	public Goblin(String name, int health, int maxHealth, int strength, int agility, int droppedXP) {
		super(name, health, maxHealth, strength, agility, droppedXP);
		addDrop(new HealingPotion("Small Healing Potion",1, 10, 3, 10));
	}

	@Override
	public void lastStance() {
		if(this.getStrength()<30) {
			setStrength((int) (getStrength()*1.2));
		}
		
	 
		
	}
}
