package combat;


import character.Player;
import monster.Monster;


/**
 * Handles the combat logic between the player and a monster.
 * Controls turns, attacks, damage calculation and battle state.
 */

public class CombatEngine{
	private Player player;
	private Monster monster;
	private boolean isPlayerTurn = true;
	
	 /**
     * Creates a new combat engine for a fight between a player and a monster.
     *
     * @param player  the player participating in the battle
     * @param monster the monster participating in the battle
     */
	
	
	public CombatEngine(Player player, Monster monster) {
		this.player = player;
		this.monster = monster;
		int health = 0;
		health = player.getMaxHealth();
		player.setHealth(health);
	}
	
	 /**
     * Executes an attack for the current turn.
     * If it is the player's turn, active effects are applied and the monster takes damage.
     * If it is the monster's turn, the player may dodge the attack.
     *
     * @return the amount of damage dealt
     */
	

	public int executeAttack() {
		int damage = 0;
		
			if(isPlayerTurn) {
				player.applyEffects();
				damage = player.calculateTotalDamage();
				monster.takesDamage(damage);
				player.equipment.damageCombatItem();
				}
			else {
				if(this.hpPercentage()<0.3) {
					monster.lastStance();
				}
				damage = monster.calculateTotalDamage();
				if(player.canDodge()) {
					System.out.println(player.getName() + " dodged the attack!");
					damage = 0;
				}
				else {
				player.takesDamage(damage);
					}
				}
			isPlayerTurn = !isPlayerTurn;
			return damage;
			}	
	
	
	/**
     * Checks whether the battle is over.
     *
     * @return true if either the player or the monster is defeated
     */
	
	public boolean isBattleOver() {
		if(player.isAlive() == true && monster.isAlive() == true) 
			return false;
		else
			return true;
	}
	
	/**
     * Calculates the monster's current health percentage.
     *
     * @return monster health as a value between 0.0 and 1.0
     */
	
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
