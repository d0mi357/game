package monster;

import items.HealingPotion;
import items.VampireCloak;

public class Vampire extends Monster{
	public Vampire(String name, int health, int maxHealth, int strength, int agility, int droppedXP) {
		super(name, health, maxHealth, strength, agility, droppedXP);
		this.addDrop(new VampireCloak());
		this.addDrop(new HealingPotion("Large Healing Potion", 2, 50, 3, 50));
}

	@Override
		public void lastStance() {
			if(this.getHealth()<50) {
				setStrength((int) (getStrength()*1.4));	
			}
		}

}
