package combat;
import character.Character;


public class Combat{
	private Character player;
	private Character monster;
	
	public Combat(Character player, Character monster) {
		this.player = player;
		this.monster = monster;
	}
	
	public void showDamageAndHealth(String name, int damage, int health) {
		System.out.println(player.getName()+" greift an und macht " + damage + " schaden!");
		System.out.println("Verbleibende HP: " + health); 
		
	}
	
	public void startBattle() {
		System.out.println("Kampf beginnt " + player.getName() + " vs " + monster.getName()); 
		int whichturn = 1;
		int damage = 0;
		while(player.isAlive() && monster.isAlive()) {
			if(whichturn == 1) {
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
