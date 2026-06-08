package dungeon;

import character.Monster;

public class Floor {
	private int floorNumber;
	private Monster monster;
	private boolean cleared;
	
	public Floor(int floorNumber, Monster monster) {
		this.floorNumber = floorNumber;
		this.monster = monster;	
		this.cleared = false;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public boolean isCleared() {
		return cleared;
	}

	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}
	
	
	
}
