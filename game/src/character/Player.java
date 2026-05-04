package character;

import inventory.Inventory;

public class Player extends Character{
	
	Inventory inv;
	private int level;
	private int xp;
	private int gold;
	
	
	
	
	
	public Player(String name, String race, int health, int maxHealth) {
		super(name, race, health, maxHealth);
		inv = new Inventory();
		this.level = 1;
		this.xp = 0;
		
	}



	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}



	public int getXp() {
		return xp;
	}



	public void setXp(int xp) {
		this.xp = xp;
	}

}
