package character;

/**
 * Represents a temporary effect applied to a player.
 * Effects can heal the player over multiple turns and
 * automatically expire after a specified duration.
 */

public class ActiveEffect {
	
	private String name;
	private int remainingTurns;
	private int healPerTurn;
	
	/**
	 * Creates a new active effect.
	 *
	 * @param name name of the effect
	 * @param remainingTurns number of turns the effect remains active
	 * @param healPerTurn amount of health restored each turn
	 */
	
	public ActiveEffect(String name, int remainingTurns, int healPerTurn) {
		this.name = name;
		this.remainingTurns = remainingTurns;
		this.healPerTurn = healPerTurn;
		
	}
	
	/**
	 * Applies the effect to the player for one turn.
	 * The player's health is restored and the remaining
	 * duration is reduced by one turn.
	 *
	 * @param player the player affected by this effect
	 */
	
	public void apply(Player player) {
		player.heal(healPerTurn);
		remainingTurns--;
	}
	
	/**
	 * Checks whether the effect has expired.
	 *
	 * @return true if no turns remain, otherwise false
	 */
	
	public boolean isFinished() {
		return remainingTurns<=0;
	}
	
	public String getName() {
		return name;
	}
}
