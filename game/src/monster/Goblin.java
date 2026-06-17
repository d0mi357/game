package monster;

import items.GoblinTotem;
import items.HealingPotion;

public class Goblin extends Monster{
	
	public Goblin(String name, int health, int maxHealth, int strength, int agility, int droppedXP) {
		super(name, health, maxHealth, strength, agility, droppedXP);
		addDrop(new HealingPotion("Small Healing Potion",1, 10, 3, 10));
		addDrop(new GoblinTotem());
	}

	@Override
	public void lastStance() {
		if(this.getHealth()<30) {
			setStrength((int) (getStrength()*1.2));
		}
		
	 
		
	}
}
