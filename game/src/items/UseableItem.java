package items;

/**
 * Represents an item that can be used by the player.
 * Usable items provide temporary or permanent effects such as
 * healing, stat boosts or other beneficial effects.
 */

public class UseableItem extends Item{
	
	private int durability;
	
	public UseableItem() {super();}
	
	/**
	 * Creates a new usable item.
	 *
	 * @param name item name
	 * @param quantity amount of items
	 * @param worth gold value of the item
	 */
	
	public UseableItem(String name, int quantity, int worth, int durability) {
		super(name, quantity, worth);
		this.durability = durability;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	/**
	 * Reduces the item's durability by the specified amount.
	 * Durability cannot fall below zero.
	 *
	 * @param amount durability damage to apply
	 */
	
	public void damageDurability(int amount) {
		if(amount > 0) {
			//throw new IllegalArgumentException("damage amount must be >= 0");
		}
		int newDurability = Math.max(0, this.durability - amount);
		setDurability(newDurability);
	}

	@Override
	public String toString() {
		return "UsableItem: " + getName() + ", Quantity: " + getQuantity() +  ", Durability: " + getDurability();
	}
	

	
}
