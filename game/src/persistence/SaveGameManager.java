package persistence;

import java.io.FileReader;
import com.google.gson.JsonSyntaxException;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import character.Player;
import items.Bandage;
import items.BossGoblinCrown;
import items.DragonScale;
import items.Fruit;
import items.GoblinTotem;
import items.HealingPotion;
import items.Item;
import items.OrcTusk;
import items.StrengthPotion;
import items.VampireCloak;
import items.Weapon;
import monster.Vampire;

public class SaveGameManager {

    private final Gson gson;
    private final String fileName = "savegame.json";

    public SaveGameManager() {
        RuntimeTypeAdapterFactory<Item> itemAdapter =
                RuntimeTypeAdapterFactory.of(Item.class, "type")
                        .registerSubtype(HealingPotion.class, "healingPotion")
                        .registerSubtype(Fruit.class, "fruit")
                        .registerSubtype(Weapon.class, "weapon")
                        .registerSubtype(Bandage.class, "bandage")
                        .registerSubtype(GoblinTotem.class, "goblintotem")
        				.registerSubtype(OrcTusk.class, "orctusk")
        				.registerSubtype(VampireCloak.class, "vampirecloak")
        				.registerSubtype(BossGoblinCrown.class, "bossgoblincrown")
        				.registerSubtype(DragonScale.class, "dragonscale");

        this.gson = new GsonBuilder()
                .registerTypeAdapterFactory(itemAdapter) //TypeAdapter für Item-Objekt
                .setPrettyPrinting() // besseres Fromat
                .create();
    }
    
    /**
     * Writes the player data to the save file.
     *
     * @param player the player to save
     */

    public void savePlayer(Player player) {
        try (FileWriter writer = new FileWriter(fileName)) { //öffnet datei
            gson.toJson(player, writer); // zu JSON umwandeln
            System.out.println("💾 Progress saved!");
        } catch (IOException e) { // Falls Datei nicht gespeichert werden kann
            System.err.println("❌ Failed to save game: " + e.getMessage());
        }
    }
    
    /**
     * Loads the player from the save file.
     *
     * @return the loaded player or null if no save file exists
     */

    public Player loadPlayer() {
        try (FileReader reader = new FileReader(fileName)) { //Datei öffnen
            Player loaded = gson.fromJson(reader, Player.class); //JSON -> Objekt
            System.out.println("📂 Game loaded!");
            return loaded;
        } catch (IOException | JsonSyntaxException e) { //Datei existiert nicht oder JSON Datei kaputt
            System.out.println("ℹ️ No save file found. Creatin a new Character");
            return null;
        }
    }
}
