package combat;

import java.util.Scanner;

import character.Player;
import inventory.Equipment;
import items.HealingPotion;
import items.Item;
import items.Weapon;
import monster.Monster;


/**
 * Handles the user interface for battles.
 * Displays battle status and processes player combat actions.
 */

public class CombatView {

	private CombatEngine engine;
	private Scanner scanner = new Scanner(System.in);
	
	/**
     * Creates a new combat view for a combat engine.
     *
     * @param engine the combat engine controlling the battle
     */
	
	public CombatView(CombatEngine engine) {
		this.engine = engine;
	}
	
	/**
     * Starts the battle loop until the player or monster is defeated.
     */
	
	public void startBattle() {
		System.out.println("Kampf beginnt " + engine.getPlayer().getName() + " vs " + engine.getMonster().getName()); 
		
		while(!engine.isBattleOver()) {
			
			this.displayStatus();
			
			if(engine.isPlayerTurn()) {
				handlePlayerInput();
			}
			else {
				handleMonsterTurn();
			}
			
		}
	this.printStatusDashboard();
	
	if(engine.getPlayer().isAlive()) {
	    System.out.println("You won!");

	    int gainedXP = engine.getMonster().getDroppedXP();
	    engine.getPlayer().levelUp(gainedXP);

	    System.out.println("You gained " + gainedXP + " XP!");
	    
	    for(Item item : engine.getMonster().getDroppedItems()) {
	    	engine.getPlayer().getInventory().addItem(item);
	    	
	    	System.out.println("Loaded: " + item.getName());
	    }
	}
	else {
	    System.out.println("You lost!");
		}
	
	}	
	
	 /**
     * Displays damage dealt by an attacker.
     *
     * @param name   attacker name
     * @param damage dealt damage
     */
	
	public void showDamageAndHealth(String name, int damage, int health) {
		System.out.println(name +" did " + damage + " damage with his attack!");
	}
	
	/**
     * Generates a visual health bar.
     *
     * @param currentHP current health
     * @param maxHP     maximum health
     * @return formatted health bar
     */
	
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
	
	/**
     * Prints a compact status dashboard.
     */
	
	private void printStatusDashboard() {
		System.out.println("\n=================== STATUS ===================");
		
		String PlayerBar = this.generateHeathBar(this.getPlayerHP(), this.getPlayerMaxHP());
		System.out.printf("%-12s HP: [%s] %d/%d\n", this.getPlayerName(), PlayerBar, this.getPlayerHP(), this.getPlayerMaxHP());
		
		String MonsterBar = this.generateHeathBar(this.getMonsterHP(), this.getMonsterMaxHP());
		System.out.printf("%-12s HP: [%s] %d/%d\n", this.getMonsterName(), MonsterBar, this.getMonsterHP(), this.getMonsterMaxHP());
		
		System.out.println("==============================================");	
		
	}
	
	/**
     * Displays the current combat status of player and monster.
     */
	
	public void displayStatus() {

	    Player player = engine.getPlayer();
	    Monster monster = engine.getMonster();

	    System.out.println("=========================================================");

	    System.out.printf("%-30s %-30s%n",
	            "PLAYER",
	            "MONSTER");

	    System.out.println("=========================================================");

	    System.out.printf("%-30s %-30s%n",
	            player.getName(),
	            monster.getName());

	    System.out.printf("%-30s %-30s%n",
	            "HP: " + player.getHealth() + "/" + player.getMaxHealth(),
	            "HP: " + monster.getHealth() + "/" + monster.getMaxHealth());

	    System.out.println("=========================================================");
	}
	
	
	/**
     * Reads and handles the player's combat action.
     */
	
	
	public void handlePlayerInput() {
			System.out.println("===== ACTION MENU =====");
			System.out.println("1. Attack");
			System.out.println("2. Switch Weapon");
			System.out.println("3. Use Item");
			System.out.println(">");
			
			try {
			
				int choice = Integer.parseInt(scanner.nextLine());
			
				switch(choice) {
				case 1:
					int damage = engine.executeAttack();
					this.showDamageAndHealth(getPlayerName(), damage, engine.getMonster().getHealth());
					Weapon equipped = getEquipment().getEquippedWeapon();
					if(equipped != null) {
						System.out.println("Nach Angriff: " + equipped.getName() + " - Durabilty " + equipped.getDurability());
					}
					break;
			
				case 2:
					this.handleWeaponSwitch();
					break;
					
				case 3:
					this.handlePotionUse();
					break;
				default:
					System.out.println("Please enter a number between 1 and 3.");
				}
			}catch(NumberFormatException e) {
			System.out.println("Please enter a valid number");
		}
	}
	
	/**
     * Allows the player to switch the equipped weapon.
     */
	
	public void handleWeaponSwitch() {
		System.out.println("\n==============================================");
	    System.out.println("🔄  Weapon Switch");
	    System.out.println("----------------------------------------------");
	    
	    System.out.println("Current Weapon: [" + this.EquippedWeapon() + "]");
	    System.out.println("\nWhich Weapon do you want to chosse? (Enter a number):");
	    System.out.println("----------------------------------------------");
	    
	    
	    this.getEquipment().displayCombatGear();
	    System.out.println("==============================================");
	    System.out.print("> ");
	    
	    try {
	    	int weaponOfChoice = Integer.parseInt(scanner.nextLine());
	    
	    		System.out.println("\n----------------------------------------------");
	    		getEquipment().equipWeaponByIndex(weaponOfChoice-1);
	    		System.out.println("----------------------------------------------");
	    		
	    }catch(NumberFormatException e) {
	    	System.out.println("Please enter a valid number.");
	    }
	    
	    
	}
	
	/**
     * Allows the player to use an item from the inventory.
     */
	
	public void handlePotionUse() {
		engine.getPlayer().getInventory().displayInventory();
		System.out.println("Which Item do you want to use?");
		System.out.println(">");
		
		try {
		
			int choice = Integer.parseInt(scanner.nextLine());
		
			Item item = engine.getPlayer().normalInventory.getItemByIndex(choice-1);
		
			if(item != null) {
				//System.out.println("DEBUG item class: " + item.getClass().getName());
				item.use(engine.getPlayer());
			
				if(item.getQuantity() == 0) {
					engine.getPlayer().normalInventory.deleteItem(item);
				}
			
				engine.setPlayerTurn(false);		
			}
			else {
				System.out.println("Potion with this name not found!");
			}
		}catch(NumberFormatException e) {
			System.out.println("Please enter a valid number. ");
		}
	}
	
	
	
	

	private String getPlayerName() {
		return engine.getPlayer().getName();
	}
	private Equipment getEquipment() {
		return engine.getPlayer().equipment;
	}
	private Weapon EquippedWeapon() {
		return this.getEquipment().getEquippedWeapon();
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
