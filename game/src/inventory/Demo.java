package inventory;

import character.Goblin;

import character.Player;
import combat.CombatEngine;
import combat.CombatView;
import persistence.SaveGameManager;


public class Demo {
	public static void main(String[] args) {
		
		SaveGameManager saveManager = new SaveGameManager();
		
		
		Player player1 = saveManager.loadPlayer();

		
		if(player1 == null) {
			
			player1 = new Player("destroyer", "Elf", 75, 100, 10, 2);
		}
		
		
		player1.startStatus();
		
		Weapon weapon2 = new Weapon("Bow", 100, 500, 5, "Long Range");
		player1.equipment.addWeapon(weapon2);
		
	
		
		
		Goblin goblin1 = new Goblin("Goblin", 50, 60, 10, 3, 100);
		player1.equipment.equipWeapon(weapon2);
		CombatEngine battle1 = new CombatEngine(player1, goblin1);
		//battle1.startBattle();
		
		CombatView view1 = new CombatView(battle1);
		view1.startBattle();
		
		System.out.println("--- STARTE TEST-SPEICHERUNG ---");
		saveManager.savePlayer(player1);
		System.out.println("--- TEST-SPEICHERUNG FERTIG ---");
		
		
		
		
		
		
		
	}
}
