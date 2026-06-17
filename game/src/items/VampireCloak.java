package items;

import character.Player;

public class VampireCloak extends Item {

    public VampireCloak() {
        super("Vampire Cloak", 1, 250);
    }

    @Override
    public void use(Player player) {
        System.out.println("The Vampire Cloak is a valuable trophy and cannot be used.");
    }

    @Override
    public String toString() {
        return getName() + " (Worth: " + getWorth() + " Gold)";
    }
}