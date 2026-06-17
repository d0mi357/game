package inventory;

import java.util.ArrayList;

import items.Weapon;

/**
 * Manages the player's combat equipment.
 * Handles weapon storage, equipping weapons and durability loss.
 */

public class Equipment {
	
	private Weapon equippedWeapon;
	private final int DAMAGEUSEITEM = 1;
	public ArrayList<Weapon> combatGear;

	/**
	 * Creates a new equipment inventory and adds the default weapon.
	 */
	
	public Equipment() {
		combatGear = new ArrayList<>();
		standardEquipment();
	}

	/**
	 * Adds a weapon to the combat inventory.
	 * A maximum of three weapons can be stored.
	 *
	 * @param weapon the weapon to add
	 */
	
	public void addWeapon(Weapon weapon) {
		if(combatGear.size()<3) {
			combatGear.add(weapon);
		}	
	}
	
	/**
	 * Displays all weapons currently stored in the combat inventory.
	 */
	
	public void displayCombatGear() {
		    System.out.println("\n=================== Combat Inventory ===================");
		    
		    // Tabellen-Kopfzeile mit fester Spaltenbreite:
		    // %-3s  = Index (3 Zeichen Platz, linksbündig)
		    // %-12s = Name (12 Zeichen Platz)
		    // %-12s = Haltbarkeit (12 Zeichen Platz)
		    // %-10s = Schaden (10 Zeichen Platz)
		    // %-10s = Typ
		    System.out.printf("   %-3s | %-12s | %-12s | %-10s | %-10s\n", "Nr.", "Weapon", "Durability", "Attack", "Typ");
		    System.out.println("------------------------------------------------------------");

		    // Alle Waffen aus der Liste durchlaufen
		    for (int i = 0; i < combatGear.size(); i++) {
		        Weapon w = combatGear.get(i);
		        
		        // Die Haltbarkeit hübsch formatieren (z.B. 498/500)
		        String durString = w.getDurability() + "/500"; 

		        // Die Zeile für jede Waffe ausgeben
		        System.out.printf("   [%d] | %-12s | %-12s | +%-9d | %-10s\n", 
		            i+1, 
		            w.getName(), 
		            durString, 
		            w.getAttack(), 
		            w.getType()
		        );
		    }
		    System.out.println("============================================================\n");
		
		
		
	}

	/**
	 * Reduces the durability of the currently equipped weapon.
	 */
	
	public void damageCombatItem() {
		if(this.geteEuippedWeapon() != null) {
		this.getEquippedWeapon().damageDurability(DAMAGEUSEITEM);
		}
	}
	
	/**
	 * Removes a weapon from the combat inventory.
	 *
	 * @param idx index of the weapon to remove
	 */
	
	
	public void deleteCombatGear(int idx) {
		this.combatGear.remove(idx);
	}
	
	/**
	 * Creates and equips the player's starting weapon.
	 */
	
	public void standardEquipment() {
		Weapon weapon = new Weapon("Sword", 1, 10, 100, 5, "Melee");
		this.combatGear.add(weapon);
		this.equipWeapon(weapon);
	}
	
	/**
	 * Equips a weapon from the combat inventory.
	 *
	 * @param weapon the weapon to equip
	 */
	
	public void equipWeapon(Weapon weapon) {
	    if (combatGear.contains(weapon)) {
	        this.equippedWeapon = weapon;
	    }
	}
	
	/**
	 * Returns the currently equipped weapon.
	 *
	 * @return equipped weapon or null if no weapon is equipped
	 */
	
	public Weapon getEquippedWeapon() {
	    return this.equippedWeapon; 
	}
	
	/**
	 * Returns the name of the currently equipped weapon.
	 *
	 * @return weapon name or "Keine Waffe" if none is equipped
	 */
	
	public String getEquippedWeaponName() {
	    return (equippedWeapon != null) ? equippedWeapon.getName() : "Keine Waffe";
	}
	
	/**
	 * Prints the name of the currently equipped weapon.
	 */
	
	public void showEquippedWeapon() {
	    System.out.println(getEquippedWeaponName());
	}
	
	/**
	 * Searches for a weapon by name and returns its index.
	 *
	 * @param name name of the weapon
	 * @return index of the weapon or -1 if not found
	 * @throws IllegalArgumentException if the name is empty
	 */
	
	public int getIndexCombatGear(String name) {
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("Weapon name can not be empty!");
		}
		for (int i = 0; i < combatGear.size(); i++) {
	        Weapon w = combatGear.get(i);
	        if (w.getName().equalsIgnoreCase(name)) {
	            return i;              
	        }
	    }
	    return -1;                      
	}
	
	/**
	 * Equips a weapon based on its position in the combat inventory.
	 *
	 * @param idx index of the weapon to equip
	 */
	
	public void equipWeaponByIndex(int idx) {
	    
	    if (idx >= 0 && idx < this.combatGear.size()) {
	        Weapon w = this.combatGear.get(idx);
	        
	        
	        this.equipWeapon(w); 
	        
	        System.out.println("⚔️ " + w.getName() + " was successfully equipped!");
	    } else {
	        System.out.println("❌ Invalid Index! Weapon could not been equipped!.");
	    }
	}
	
	public Weapon geteEuippedWeapon() {
		return this.equippedWeapon;
	}


	public ArrayList<Weapon> getCombatGear() {
		return combatGear;
	}


	public void setCombatGear(ArrayList<Weapon> combatGear) {
		this.combatGear = combatGear;
	}
	
	
		
		

}
