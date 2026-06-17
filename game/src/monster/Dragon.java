package monster;

import items.DragonScale;

public class Dragon extends Monster{
	public Dragon(String name, int health, int maxHealth, int strength, int agility, int droppedXP) {
		super(name, health, maxHealth, strength, agility, droppedXP);
		this.addDrop(new DragonScale());
}

	@Override
		public void lastStance() {
			if(this.getHealth()<70) {
				setStrength((int) (getStrength()*1.4));	
			}
		}
}
