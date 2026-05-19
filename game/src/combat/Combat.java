package combat;
import java.util.Scanner;

import character.Monster;
import character.Player;


public class Combat{
	private Player player;
	private Monster monster;
	private Scanner scanner = new Scanner(System.in);
	
	public Combat(Player player, Monster monster) {
		this.player = player;
		this.monster = monster;
	}
	
	public void showDamageAndHealth(String name, int damage, int health) {
		System.out.println(name +" greift an und macht " + damage + " schaden!");
		System.out.println("Verbleibende HP: " + health); 
		
	}
	public void PlayerTurn() {
		boolean turnEnded = false;
		
		
		while(!turnEnded) {
			System.out.println("What will you do ? [Attack], [Switch Weapon]");
			String choice = scanner.next().trim().toLowerCase();
			
			switch(choice) {
			case "attack":
				int damage = player.calculateTotalDamage();
				monster.takesDamage(damage);
				this.showDamageAndHealth(player.getName(), damage, monster.getHealth());
				turnEnded = true;
				break;
			
			case "switch":
			case "switch weapon":
				System.out.println("Which Weapon do you want to equip?");
				System.out.println("Combat Inventory: ");
				player.displayCombatGear();
				String weaponOfChoice = scanner.next().trim();
				
				}
			
				
			}
			
			
			
		}
		
		
	public void MonsterTurn() {
				int damage = monster.calculateTotalDamage();
				player.takesDamage(damage);
				this.showDamageAndHealth(monster.getName(), damage, player.getHealth());
		
	}
	
	
	public void startBattle() {
		System.out.println("Kampf beginnt " + player.getName() + " vs " + monster.getName()); 
		boolean PlayerTurn = true;
		while(player.isAlive() && monster.isAlive()) {
			if(PlayerTurn) {
				PlayerTurn();
			}
			else
				MonsterTurn();
			
			PlayerTurn = false;
		
				
			
		}
	}
}
