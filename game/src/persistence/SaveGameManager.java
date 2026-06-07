package persistence;

import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import character.Player;

public class SaveGameManager {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private final String fileName = "savegame.json";

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
