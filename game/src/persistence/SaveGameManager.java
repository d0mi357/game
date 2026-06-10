package persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import character.Player;
import inventory.Fruit;
import inventory.HealingPotion;
import inventory.Item;
import inventory.Weapon;
import util.RuntimeTypeAdapterFactory;

public class SaveGameManager {

    private final Gson gson;
    private final String fileName = "savegame.json";

    public SaveGameManager() {
        RuntimeTypeAdapterFactory<Item> itemAdapter =
                RuntimeTypeAdapterFactory.of(Item.class, "type")
                        .registerSubtype(HealingPotion.class, "healingPotion")
                        .registerSubtype(Fruit.class, "fruit")
                        .registerSubtype(Weapon.class, "weapon");

        this.gson = new GsonBuilder()
                .registerTypeAdapterFactory(itemAdapter)
                .setPrettyPrinting()
                .create();
    }

    public void savePlayer(Player player) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(player, writer);
            System.out.println("💾 Spielstand erfolgreich in '" + fileName + "' gespeichert!");
        } catch (IOException e) {
            System.err.println("❌ Fehler beim Speichern: " + e.getMessage());
        }
    }

    public Player loadPlayer() {
        try (FileReader reader = new FileReader(fileName)) {
            Player loaded = gson.fromJson(reader, Player.class);
            System.out.println("📂 Spielstand erfolgreich geladen!");
            return loaded;
        } catch (IOException e) {
            System.out.println("ℹ️ Kein Spielstand gefunden. Ein neuer Charakter wird erstellt.");
            return null;
        }
    }
}
