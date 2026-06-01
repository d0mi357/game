package inventory;

public class UseableItem extends Item{
	
	private int durability;
	
	public UseableItem(String name, int quantity, int durability) {
		super(name, quantity);
		this.durability = durability;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}
	
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
