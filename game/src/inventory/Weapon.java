package inventory;

public class Weapon extends UseableItem {
	private int attack;
	private String type;

	public Weapon() {super();}

	
	public Weapon(String name, int quantity, int worth, int durability, int attack, String type) {
		super(name, quantity, worth, durability);
		this.attack = attack;
		this.setType(type);
		
		
	}
	

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Weapon: " + getName() + ", Quantity: " + getQuantity() +  ", Durability: " + getDurability() + ", Attack: " + this.attack+", Type: " + this.type;
	}

	
}
