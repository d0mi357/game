package monster;

import items.OrcTusk;

public class Orc extends Monster{
	public Orc(String name, int health, int maxHealth, int strength, int agility, int droppedXP) {
		super(name, health, maxHealth, strength, agility, droppedXP);
		this.addDrop(new OrcTusk());
}

	@Override
		public void lastStance() {
			if(this.getHealth()<50) {
				setStrength((int) (getStrength()*1.4));	
			}
		}
		
	}
