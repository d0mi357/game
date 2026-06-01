package combat;

import java.util.Scanner;

import inventory.Weapon;

public class CombatView {

	private CombatEngine engine;
	private Scanner scanner = new Scanner(System.in);
	
	public CombatView(CombatEngine engine) {
		this.engine = engine;
	}
	
	public void startBattle() {
		System.out.println("Kampf beginnt " + engine.getPlayer().getName() + " vs " + engine.getMonster().getName()); 

		while(!engine.isBattleOver()) {
			if(engine.isPlayerTurn()) {
				handlePlayerInput();
				engine.getPlayer().displayCombatGear();
			}
			else {
				handleMonsterTurn();
			}
			
		}
	if(engine.getPlayer().isAlive() == true) {
		System.out.println("You won!");
		}
	else {
		System.out.println("You lost!");
	}
	}
	
	public void showDamageAndHealth(String name, int damage, int health) {
		System.out.println(name +" greift an und macht " + damage + " schaden!");
		System.out.println("Verbleibende HP: " + health); 
	}	
	
	public void handlePlayerInput() {
			System.out.println("What will you do ? [Attack], [Switch Weapon]");
			String choice = scanner.next().trim().toLowerCase();
			
			switch(choice) {
			case "attack":
				int damage = engine.executeAttack();
				this.showDamageAndHealth(getPlayerName(), damage, engine.getMonster().getHealth());
				
				Weapon equipped = engine.getPlayer().getEquippedWeapon();
				if(equipped != null) {
					System.out.println("Nach Angriff: " + equipped.getName() + " - Durabilty " + equipped.getDurability());
				}
				break;
			
			case "switch":
			case "switch weapon":
				System.out.println("Your current equpied Weapon: "+ engine.getPlayer().getEquppedWeaponName());
				System.out.println("Which Weapon do you want to equip?");
				System.out.println("Combat Inventory: ");
				engine.getPlayer().displayCombatGear();
				String weaponOfChoice = scanner.next().trim();
				int idx = engine.getPlayer().getIndexCombatGear(weaponOfChoice);
				engine.getPlayer().equipWeapon(idx);
				engine.getPlayer().showEquippedWeapon();
				System.out.println("You have succesfully switch your equpied Weapon to \n"+engine.getPlayer().combatGear.get(idx).getName());
				break;
				
				}	
			}
	
	private String getPlayerName() {
		return engine.getPlayer().getName();
	}
			
	public void handleMonsterTurn() {
		int damage = engine.executeAttack();
		this.showDamageAndHealth(engine.getMonster().getName(), damage, engine.getMonster().getHealth());
	}
	
	
}
