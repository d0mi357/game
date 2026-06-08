package character;

import java.util.Scanner;

public class PlayerCreator {
	
	private Scanner scanner = new Scanner(System.in);
	
	public Player createPlayer() {
		System.out.println("=== Create your own Character ===");
		
		System.out.println("Name: ");
		String name = scanner.nextLine();
		
		System.out.println("Race: ");
		String race = scanner.nextLine();
		
		Player player = new Player(name, race, 100, 100, 10, 5);
		
		player.startStatus();
		
		return player;
		
	}
}
