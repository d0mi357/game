package inventory;

import character.Player;

public class Bandage extends Item{
	
	private int healAmount;
	
	public Bandage(String name, int quantity, int healAmount, int worth) {
		super(name, quantity, worth);
		this.healAmount = healAmount;
	}
	
	@Override 
	public void use(Player player) {
		
		if(this.getQuantity() <= 0) {
			System.out.println("No bandages left!");
			return;
		}
		
		player.heal(healAmount);
		
		System.out.println("You used a Bandage and healed " + this.healAmount + " HP!");
		
		this.decreaseQuantity();		
		
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	
	
	
	
	
}
