package items;

import character.Player;

public class OrcTusk extends Item {

    public OrcTusk() {
        super("Orc Tusk", 1, 100);
    }

    @Override
    public void use(Player player) {
        System.out.println("The Orc Tusk is a valuable trophy and cannot be used.");
    }

    @Override
    public String toString() {
        return getName() + " (Worth: " + getWorth() + " Gold)";
    }
}