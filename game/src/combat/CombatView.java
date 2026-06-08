package combat;

import java.util.Scanner;

import character.Monster;
import character.Player;
import inventory.Equipment;
import inventory.HealingPotion;
import inventory.Item;
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
	
	if(engine.getPlayer().isAlive()) {
	    System.out.println("You won!");

	    int gainedXP = engine.getMonster().getDroppedXP();
	    engine.getPlayer().levelUp(gainedXP);

	    System.out.println("You gained " + gainedXP + " XP!");
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
	
	
	
	
	
	public void handlePlayerInput() {
			System.out.println("What will you do ? [Attack], [Switch Weapon], [Potion]");
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
				this.handleWeaponSwitch();
				break;
				
					
			case "potion":
				this.handlePotionUse();
				break;
			}
	}
	
	public void handleWeaponSwitch() {
		System.out.println("\n==============================================");
	    System.out.println("🔄  Weapon Switch");
	    System.out.println("----------------------------------------------");
	    
	    System.out.println("Current Weapon: [" + this.EquippedWeapon() + "]");
	    System.out.println("\nWhich Weapon do you want to chosse? (Enter name):");
	    System.out.println("----------------------------------------------");
	    
	    
	    this.getEquipment().displayCombatGear();
	    System.out.println("==============================================");
	    System.out.print("> ");
	    
	    scanner.nextLine();
	    String weaponOfChoice = scanner.next().trim();
	    
	    int idx = getEquipment().getIndexCombatGear(weaponOfChoice);
	    
	    System.out.println("\n----------------------------------------------");
	    getEquipment().equipWeaponByIndex(idx);
	    System.out.println("----------------------------------------------");
	    
	    
	}
	
	public void handlePotionUse() {
		System.out.println("Which Potion do you want to use?");
		scanner.nextLine();
		String potionName = scanner.nextLine();
		
		Item item = engine.getPlayer().normalInventory.findItem(potionName);
		
		if(item instanceof HealingPotion) {
			HealingPotion potion = (HealingPotion) item;
			potion.use(engine.getPlayer());
			
			if(potion.getQuantity() == 0) {
				engine.getPlayer().normalInventory.deleteItem(potion.getName());
			}
			
			engine.setPlayerTurn(false);		
		}
		else {
			System.out.println("Potion with this name not found!");
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
