package combat;
import character.Character;


public class Combat{
	private Character player;
	private Character monster;
	
	public Combat(Character player, Character monster) {
		this.player = player;
		this.monster = monster;
	}
	
	public void startBattle() {
		System.out.println("Kampf beginnt " + player.getName() + " vs " + monster.getName()); 
		int whichturn = 1;
		int damage = 0;
		while(player.isAlive() && monster.isAlive()) {
			if(whichturn == 1) {
				damage = player.calculateTotalDamage();
				//System.out.println("Calculated damage: "+ damage);
				monster.takesDamage(damage);
				//System.out.println("Health from Monster: "+ monster.getHealth());
				whichturn++;
				
			}
			if(whichturn == 2){
				damage = monster.calculateTotalDamage();
				//System.out.println("Calculated damage: "+ damage);
				player.takesDamage(damage);
				//System.out.println("Health from Player: "+ player.getHealth());
				whichturn--;			}
		}
	}
}
