package character;

public class ActiveEffect {
	
	private String name;
	private int remainingTurns;
	private int healPerTurn;
	
	public ActiveEffect(String name, int remainingTurns, int healPerTurn) {
		this.name = name;
		this.remainingTurns = remainingTurns;
		this.healPerTurn = healPerTurn;
		
	}
	
	public void apply(Player player) {
		player.heal(healPerTurn);
		remainingTurns--;
	}
	
	public boolean isFinished() {
		return remainingTurns<=0;
	}
	
	public String getName() {
		return name;
	}
}
