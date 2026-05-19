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
	
	public void startBattle() {
		System.out.println("Kampf beginnt " + player.getName() + " vs " + monster.getName()); 
		int whichturn = 1;
		int damage = 0;
		while(player.isAlive() && monster.isAlive()) {
			if(whichturn == 1) {
				System.out.println("Attack(1), Switch Weapon(2)");
				int choice = scanner.nextInt();
				if(choice == 2) {
					System.out.println("Combat Inventory: ");
					player.displayCombatGear();
				}
				
				damage = player.calculateTotalDamage();
				monster.takesDamage(damage);
				this.showDamageAndHealth(player.getName(), damage, monster.getHealth());
				whichturn=2;		
			}
			else{
				damage = monster.calculateTotalDamage();
				player.takesDamage(damage);
				this.showDamageAndHealth(monster.getName(), damage, player.getHealth());
				whichturn=1;			
				}
			
		}
	}
}
