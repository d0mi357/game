package inventory;

import character.Goblin;

import character.Player;
import character.PlayerCreator;
import combat.CombatEngine;
import combat.CombatView;
import dungeon.Dungeon;
import dungeon.Floor;
import persistence.SaveGameManager;


public class Demo {
	public static void main(String[] args) {
		
		SaveGameManager saveManager = new SaveGameManager();
		
		
		Player player1 = saveManager.loadPlayer();

		
		if(player1 == null) {
			
			//player1 = new Player("destroyer", "Elf", 75, 100, 10, 2);
			PlayerCreator playercreator = new PlayerCreator();
			player1 = playercreator.createPlayer();
			player1.startStatus();
		}
		
		
		
		
		if(player1.equipment.getIndexCombatGear("Bow") == -1) {
			Weapon weapon2 = new Weapon("Bow", 100, 500, 5, "Long Range");
			player1.equipment.addWeapon(weapon2);
		}
	
		
		
		Goblin goblin1 = new Goblin("Goblin", 50, 60, 10, 3, 100);
		//player1.equipment.equipWeapon(weapon2);
		CombatEngine battle1 = new CombatEngine(player1, goblin1);
	
		
		Dungeon dungeon = new Dungeon();
		
		dungeon.addFloors(new Floor(1, goblin1));
		dungeon.addFloors(new Floor(2, new Goblin("Boss Goblin", 120, 120, 20, 5, 500)));
		
		dungeon.startDungeon(player1);
		
		System.out.println("--- STARTE TEST-SPEICHERUNG ---");
		saveManager.savePlayer(player1);
		System.out.println("--- TEST-SPEICHERUNG FERTIG ---");
		
		
		
		
		
		
		
	}
}
