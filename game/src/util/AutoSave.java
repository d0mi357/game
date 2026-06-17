package util;

import character.Player;
import persistence.SaveGameManager;

public class AutoSave {
	 private static final SaveGameManager saveManager =
	            new SaveGameManager();

	 /**
	  * Saves the current player progress to the save file.
	  *
	  * @param player the player whose data should be saved
	  */
	 
	    public static void save(Player player) {
	        saveManager.savePlayer(player);
	    }
}
