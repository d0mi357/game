package inventory;

import character.Player;

public class Item {
	
	private String name;
	private int quantity;
	
	public Item() {}
	public Item(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;

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
	
	
	@Override
	public String toString() {
	    return name + " x" + quantity;
	}
	
	public void use(Player player) {
		System.out.println(this.name + " cannot be used.");
	}

}
