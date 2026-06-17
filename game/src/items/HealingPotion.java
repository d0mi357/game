package items;

import character.ActiveEffect;
import character.Player;

public class HealingPotion extends Item{
	
	private int duration;
	private int healPerTurn;
	
	public HealingPotion(String name, int quantity, int worth, int duration, int healPerTurn) {
		super(name, quantity, worth);
		this.duration = duration;
		this.healPerTurn = healPerTurn;
	}
	
	@Override
	public void use(Player player) {
		if(getQuantity() <= 0) {
			System.out.println("No more Potions available");
			return;
		}
		player.heal(this.healPerTurn);
		
		player.addEffect(new ActiveEffect(getName(), duration - 1, healPerTurn));
		this.decreaseQuantity();
		
		System.out.println("You have used a " + this.getName() + "!");
		
		
		
	} @Override
    public String toString() {
        return "HealingPotion: " + getName()
                + ", Quantity: " + getQuantity()
                + ", Duration: " + duration
                + ", Heal per Turn: " + healPerTurn;
    }

	
	
	
	
}
