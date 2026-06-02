package combat;

import java.util.Scanner;

import inventory.Equipment;
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
			
			this.printStatusDashboard();
			
			if(engine.isPlayerTurn()) {
				handlePlayerInput();
			}
			else {
				handleMonsterTurn();
			}
			
		}
	this.printStatusDashboard();
	if(engine.getPlayer().isAlive() == true) {
		System.out.println("You won!");
		}
	else {
		System.out.println("You lost!");
		}
	}
	
	
	
	
	public void showDamageAndHealth(String name, int damage, int health) {
		System.out.println(name +" did " + damage + " damage with his attack!");
	}
	
	public String generateHeathBar(float currentHP, float maxHP) {
		int barLength = 10;
		
		
		float hpPercentage = (float)currentHP / maxHP;
		int numFilledBlocks = (int) (hpPercentage*barLength);
		if(numFilledBlocks < 0) numFilledBlocks = 0;
		if (numFilledBlocks > barLength) numFilledBlocks = barLength;
		
		StringBuilder bar = new StringBuilder();
		for(int i = 0;i < barLength;i++) {
			if(i < numFilledBlocks) {
				bar.append("■");
			}else {
				bar.append("□");
			}
		}
		return bar.toString();
	}
	
	private void printStatusDashboard() {
		System.out.println("\n=================== STATUS ===================");
		
		String PlayerBar = this.generateHeathBar(this.getPlayerHP(), this.getPlayerMaxHP());
		System.out.printf("%-12s HP: [%s] %d/%d\n", this.getPlayerName(), PlayerBar, this.getPlayerHP(), this.getPlayerMaxHP());
		
		String MonsterBar = this.generateHeathBar(this.getMonsterHP(), this.getMonsterMaxHP());
		System.out.printf("%-12s HP: [%s] %d/%d\n", this.getMonsterName(), MonsterBar, this.getMonsterHP(), this.getMonsterMaxHP());
		
		System.out.println("==============================================");	
		
	}
	
	
	
	
	
	public void handlePlayerInput() {
			System.out.println("What will you do ? [Attack], [Switch Weapon]");
			System.out.println(">");
			String choice = scanner.next().trim().toLowerCase();
			
			switch(choice) {
			case "attack":
				int damage = engine.executeAttack();
				this.showDamageAndHealth(getPlayerName(), damage, engine.getMonster().getHealth());
				Weapon equipped = getEquipment().getEquippedWeapon();
				if(equipped != null) {
					System.out.println("Nach Angriff: " + equipped.getName() + " - Durabilty " + equipped.getDurability());
				}
				break;
			
			case "switch":
			case "switch weapon":
				System.out.println("Your current equpied Weapon: "+ getEquipment().getEquippedWeaponName());
				System.out.println("Which Weapon do you want to equip?");
				System.out.println("Combat Inventory: ");
				getEquipment().displayCombatGear();
				String weaponOfChoice = scanner.next().trim();
				int idx = getEquipment().getIndexCombatGear(weaponOfChoice);
				getEquipment().equipWeapon(getEquipment().combatGear.get(idx));
				getEquipment().showEquippedWeapon();
				System.out.println("You have succesfully switch your equpied Weapon to \n"+getEquipment().combatGear.get(idx).getName());
				break;
				
				}	
			}
	
	private String getPlayerName() {
		return engine.getPlayer().getName();
	}
	private Equipment getEquipment() {
		return engine.getPlayer().equipment;
	}
	private int getPlayerHP() {
		return engine.getPlayer().getHealth();
	}
	private int getPlayerMaxHP() {
		return engine.getPlayer().getMaxHealth();
	}
	private int getMonsterHP() {
		return engine.getMonster().getHealth();
	}
	private int getMonsterMaxHP() {
		return engine.getMonster().getMaxHealth();
	}
	private String getMonsterName() {
		return engine.getMonster().getName();
	}
			
	public void handleMonsterTurn() {
		int damage = engine.executeAttack();
		if(engine.hpPercentage()<0.3) {
			engine.getMonster().lastStance();
			System.out.println("FINAL STANCE! MONSTER DAMAGE INCREASED BY 1.2!");
		}
		this.showDamageAndHealth(engine.getMonster().getName(), damage, engine.getMonster().getHealth());
	}
	
	
}
