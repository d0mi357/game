package items;

import character.ActiveEffect;
import character.Player;

public class StrengthPotion extends Item{
	private int strengthBoost;
	private int duration;
	
	public StrengthPotion(String name, int quantity, int worth, int duration, int strengthBoost) {
		super(name, quantity, worth);
		this.duration = duration;
		this.strengthBoost = strengthBoost;
	}
	
	@Override
	public void use(Player player) {
		if(getQuantity() <= 0) {
            System.out.println("No more Strength Potions available.");
            return;
        }

        player.addEffect(new ActiveEffect(getName(), duration, 0, strengthBoost));

        decreaseQuantity();

        System.out.println("Strength increased by " + strengthBonus + " for " + duration + " turns!");
	}

	public int getStrengthBoost() {
		return strengthBoost;
	}

	public void setStrengthBoost(int strengthBoost) {
		this.strengthBoost = strengthBoost;
	}
	
	
}
