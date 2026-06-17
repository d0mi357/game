package inventory;

import character.Player;
import character.PlayerCreator;
import dungeon.Dungeon;
import dungeon.Floor;
import items.HealingPotion;
import items.Weapon;
import monster.BossGoblin;
import monster.Dragon;
import monster.Goblin;
import monster.Orc;
import monster.Vampire;
import persistence.SaveGameManager;
import shop.StartPoint;
import util.AutoSave;


public class Demo {
	public static void main(String[] args) {
		
		SaveGameManager saveManager = new SaveGameManager();
		Player player1 = saveManager.loadPlayer();

		
		
		if(player1 == null) {
			
			PlayerCreator playercreator = new PlayerCreator();
			player1 = playercreator.createPlayer();
			player1.startStatus();
			
			HealingPotion potion = new HealingPotion("Small Healing Potion", 3, 10, 3, 10);
			player1.normalInventory.addItem(potion);
			
			if(player1.equipment.getIndexCombatGear("Bow") == -1) {
				Weapon weapon2 = new Weapon("Bow", 100, 15, 500, 5, "Long Range");
				player1.equipment.addWeapon(weapon2);
			}
		
			AutoSave.save(player1);
		}
		
		
		
		Dungeon dungeon = new Dungeon();
		
		Goblin goblin1 = new Goblin("Goblin", 50, 60, 10, 3, 100);

		
		dungeon.addFloors(new Floor(1, goblin1));
		dungeon.addFloors(new Floor(2, new BossGoblin("Boss Goblin", 5, 120, 20, 5, 500)));
		dungeon.addFloors(new Floor(3, new Orc("Orc", 250, 250, 30, 5, 1500)));
		dungeon.addFloors(new Floor(4, new Vampire("Vampire", 300, 300, 45, 10, 2500)));
		dungeon.addFloors(new Floor(5, new Dragon("Strom Dragon", 500, 500, 60, 10, 7000)));
		
		
		
		StartPoint startpoint = new StartPoint();
		
		
		boolean running = true;
		
		while(running) {
		    boolean enterDungeon = startpoint.enter(player1);

		    if(enterDungeon) {

		        
		        boolean survived = dungeon.startDungeon(player1);
		        
		        if(!survived) {
		        	running = false;
		        }
		        
		    }else
		    	running = false;
		}	
		
		AutoSave.save(player1);
		System.out.println("Thanks for playing");
			
		
	}
}
