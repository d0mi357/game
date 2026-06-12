package inventory;

import character.Player;

public abstract class Item {
	
	private String name;
	private int quantity;
	private int worth;
	
	public Item() {}
	public Item(String name, int quantity, int worth) {
		this.name = name;
		this.quantity = quantity;
		this.worth = worth;

	}
	
	public String getName() {
		return name;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void decreaseQuantity() {
		if(this.quantity > 0 )
			this.quantity--;
	}
	
	
	
	public int getWorth() {
		return worth;
	}
	public void setWorth(int worth) {
		this.worth = worth;
	}
	@Override
	public String toString() {
	    return name + " x" + quantity;
	}
	
	public void use(Player player) {
		System.out.println(this.name + " cannot be used.");
	}

}
