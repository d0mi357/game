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
	
	public void damageCombatItem() {
		if(this.geteEuippedWeapon() != null) {
		this.getEquippedWeapon().damageDurability(DAMAGEUSEITEM);
		}
	}
	
	public void standardEquipment() {
		Weapon weapon = new Weapon("Sword", 1, 10, 100, 5, "Melee");
		this.combatGear.add(weapon);
		this.equipWeapon(weapon);
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
	
	public void equipWeaponByIndex(int idx) {
	    // Sicherheit geht vor: Prüfen, ob der Index in der Liste existiert
	    if (idx >= 0 && idx < this.combatGear.size()) {
	        Weapon w = this.combatGear.get(idx);
	        
	        // Hier rufst du deine bestehende Logik auf, 
	        // die die Waffe letztendlich als "aktiv" setzt
	        this.equipWeapon(w); 
	        
	        System.out.println("⚔️ " + w.getName() + " was successfully equipped!");
	    } else {
	        System.out.println("❌ Invalid Index! Weapon could not been equipped!.");
	    }
	}
	
	public Weapon geteEuippedWeapon() {
		return this.equippedWeapon;
	}
		
		

}
