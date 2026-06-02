package inventory;

import java.util.ArrayList;

public class Equipment {
	
	private Weapon equippedWeapon;
	private final int DAMAGEUSEITEM = 1;
	public ArrayList<Weapon> combatGear;

	public Equipment() {
		combatGear = new ArrayList<>();
		standardEquipment();
	}


	public void addWeapon(Weapon weapon) {
		if(combatGear.size()<3) {
			combatGear.add(weapon);
		}	
	}
	
	public void displayCombatGear() {
		for(Weapon w : combatGear) {
			System.out.println(w.toString()); 
		}
	}
	
	public void damageCombatItem() {
		this.getEquippedWeapon().damageDurability(DAMAGEUSEITEM);
	}
	
	public void standardEquipment() {
		Weapon weapon = new Weapon("Sword", 1, 100, 5, "Melee");
		this.combatGear.add(weapon);
	}
	
	public void equipWeapon(Weapon weapon) {
	    if (combatGear.contains(weapon)) {
	        this.equippedWeapon = weapon;
	    }
	}
	
	public Weapon getEquippedWeapon() {
	    return this.equippedWeapon; 
	}
	
	public String getEquippedWeaponName() {
	    return (equippedWeapon != null) ? equippedWeapon.getName() : "Keine Waffe";
	}
	
	public void showEquippedWeapon() {
	    System.out.println(getEquippedWeaponName());
	}
	
	public int getIndexCombatGear(String name) {
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("Weapon name can not be empty!");
		}
		for (int i = 0; i < combatGear.size(); i++) {
	        Weapon w = combatGear.get(i);
	        if (w.getName().equalsIgnoreCase(name)) {
	            return i;               // Treffer → Index zurückgeben
	        }
	    }
	    return -1;                      // kein Treffer
	}
	
	
		
		

}
