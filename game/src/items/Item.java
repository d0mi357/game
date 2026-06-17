package items;

import character.Player;

/**
 * Abstract base class for all items in the game.
 * Stores common item properties such as name,
 * quantity and gold value.
 */

public abstract class Item {
	
	private String name;
	private int quantity;
	private int worth;
	
	public Item() {}
	
	/**
	 * Creates a new item.
	 *
	 * @param name item name
	 * @param quantity amount of items
	 * @param worth gold value of the item
	 */
	
	public Item(String name, int quantity, int worth) {
		this.name = name;
		this.quantity = quantity;
		this.worth = worth;

	}
	
	/**
	 * Returns the item name.
	 *
	 * @return item name
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the current quantity of the item.
	 *
	 * @return item quantity
	 */
	
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Sets the item name.
	 *
	 * @param name new item name
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the item quantity.
	 *
	 * @param quantity new quantity
	 */
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Reduces the item quantity by one.
	 * Quantity cannot become negative.
	 */
	
	public void decreaseQuantity() {
		if(this.quantity > 0 )
			this.quantity--;
	}
	
	/**
	 * Returns the item's gold value.
	 *
	 * @return item worth in gold
	 */
	
	public int getWorth() {
		return worth;
	}
	
	/**
	 * Sets the item's gold value.
	 *
	 * @param worth new gold value
	 */
		
	public void setWorth(int worth) {
		this.worth = worth;
	}
	@Override
	public String toString() {
	    return name + " x" + quantity;
	}
	/**
	 * Uses the item and applies its effect.
	 *
	 * @param player the player using the item
	 */
	
	public void use(Player player) {
		System.out.println(this.name + " cannot be used.");
	}

}
