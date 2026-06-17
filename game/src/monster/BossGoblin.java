package monster;

import items.BossGoblinCrown;
import items.StrengthPotion;

public class BossGoblin extends Monster{
	public BossGoblin(String name, int health, int maxHealth, int strength, int agility, int droppedXP) {
		super(name, health, maxHealth, strength, agility, droppedXP);
		//this.addDrop(new StrengthPotion("Small Strength Potion", 2, 30, 3, 7));
		this.addDrop(new BossGoblinCrown());
	}

	
	
	@Override
	public void lastStance() {
		if(this.getHealth()<50) {
			setStrength((int) (getStrength()*1.4));	
		}
	}
}
