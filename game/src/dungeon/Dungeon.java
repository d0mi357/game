package dungeon;

import java.util.ArrayList;

import character.Player;
import combat.CombatEngine;
import combat.CombatView;
import util.AutoSave;
import util.InputHelper;

public class Dungeon {

    private ArrayList<Floor> floors;
    private InputHelper input = new InputHelper();

    public Dungeon() {
        floors = new ArrayList<>();
    }

    public void addFloors(Floor floor) {
        floors.add(floor);
    }

    public boolean startDungeon(Player player) {
        System.out.println("=== Dungeon startet ===");

        for(Floor floor : floors) {
            System.out.println("Floor " + floor.getFloorNumber());

            CombatEngine engine = new CombatEngine(player, floor.getMonster());
            CombatView view = new CombatView(engine);
            view.startBattle();

            if(!player.isAlive()) {
                System.out.println("You lost!");
                System.out.println("Du bist im Dungeon gestorben.");
                return false;
            }

            floor.setCleared(true);
            System.out.println("Floor " + floor.getFloorNumber() + " geschafft!");

            System.out.println();
            System.out.println("1. Continue Dungeon");
            System.out.println("2. Return to Start Point");

            int choice = input.readInt(">");

            switch(choice) {
                case 1:
                    AutoSave.save(player);
                    continue;

                case 2:
                    AutoSave.save(player);
                    return true;

                default:
                    System.out.println("Invalid choice");
                    AutoSave.save(player);
                    return true;
            }
        }

        dungeonCompletion(player);
        return true;
    }

    private void dungeonCompletion(Player player) {
        System.out.println("\n=================================");
        System.out.println("        DUNGEON COMPLETED");
        System.out.println("=================================");
        System.out.println("You cleared all floors!");
        System.out.println("You survived the dungeon.");
        System.out.println("=================================");

        AutoSave.save(player);
    }
}