package inventory;

import character.Monster;
import character.Player;
import combat.CombatEngine;
import combat.CombatView;

public class Demo {
	public static void main(String[] args) {
		
		
		
		Player player1 = new Player("destroyer", "Elf", 75, 100, 10);
		player1.startStatus();
		
		Weapon weapon2 = new Weapon("Bow", 100, 500, 5, "Long Range");
		player1.addWeapon(weapon2);
		
		
		Monster monster1 = new Monster("Goblin", 50, 60, 10, 100);
		player1.equipWeapon(0);
		CombatEngine battle1 = new CombatEngine(player1, monster1);
		//battle1.startBattle();
		
		CombatView view1 = new CombatView(battle1);
		view1.startBattle();
		
		
		
		
		
		
		
		
	}
}
