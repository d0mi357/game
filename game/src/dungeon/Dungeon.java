package dungeon;

import java.util.ArrayList;

import character.Player;
import combat.CombatEngine;
import combat.CombatView;

public class Dungeon {
	
	ArrayList<Floor> floors;
	
	public Dungeon() {
		floors = new ArrayList<>();	
		
		}
	public void addFloors(Floor floor) {
		floors.add(floor);
	}
	
	 public void startDungeon(Player player) {
	        System.out.println("=== Dungeon startet ===");

	        for (Floor floor : floors) {
	            System.out.println("Floor " + floor.getFloorNumber());

	            CombatEngine engine = new CombatEngine(player, floor.getMonster());
	            CombatView view = new CombatView(engine);
	            view.startBattle();

	            if (player.isAlive()) {
	                floor.setCleared(true);
	                System.out.println("Floor " + floor.getFloorNumber() + " geschafft!");
	            } else {
	                System.out.println("Du bist im Dungeon gestorben.");
	                break;
	            }
	        }

	        System.out.println("=== Dungeon beendet ===");
	    }
	
	
	
}
