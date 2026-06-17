package items;

import character.Player;

/**
 * Represents a loot item obtained from defeated monsters.
 * Loot items usually have no direct use and can be sold
 * for gold or collected as trophies.
 */	

public abstract class LootItem extends Item{
	
	/**
	 * Creates a new loot item.
	 *
	 * @param name name of the loot item
	 * @param quantity amount of loot items
	 * @param worth gold value of the loot item
	 */
		
	public LootItem(String name, int quantity, int worth) {
		super(name, quantity, worth);
		}
	
	@Override
	public void use(Player player) {
		System.out.println(this.getName()+" connot be used!");	
		}
	
	}
