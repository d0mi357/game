package combat;


import character.Monster;
import character.Player;


public class CombatEngine{
	private Player player;
	private Monster monster;
	private boolean isPlayerTurn = true;
	
	public CombatEngine(Player player, Monster monster) {
		this.player = player;
		this.monster = monster;
		int health = 0;
		health = player.getMaxHealth();
		player.setHealth(health);
	}
	

	public int executeAttack() {
		int damage = 0;
		
			if(isPlayerTurn) {
				damage = player.calculateTotalDamage();
				monster.takesDamage(damage);
				player.equipment.damageCombatItem();
				}
			else {
				if(this.hpPercentage()<0.3) {
					monster.lastStance();
				}
				damage = monster.calculateTotalDamage();
				player.takesDamage(damage);
				}
			isPlayerTurn = !isPlayerTurn;
			return damage;
			}	
	
	public boolean isBattleOver() {
		if(player.isAlive() == true && monster.isAlive() == true) 
			return false;
		else
			return true;
	}
	
	public float hpPercentage() {
		return monster.getHealth() / monster.getMaxHealth();
	}
	
	
	public boolean isPlayerTurn() {
		return isPlayerTurn;
	}


	public void setPlayerTurn(boolean isPlayerTurn) {
		this.isPlayerTurn = isPlayerTurn;
	}


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}



}
