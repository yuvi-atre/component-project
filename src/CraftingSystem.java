import components.mchest.MChest;
import components.mchest.MChestOnMap;

/**
 * The CraftingSystem class provides functionality to craft items by consuming
 * required components from an MChest.
 */
public class CraftingSystem {

    /**
     * The MChest instance used to store and manage crafting components.
     */
    private MChest chest;

    /**
     * Constructs a CraftingSystem with the specified MChest.
     *
     * @param chest
     *            the MChest instance to be used for crafting
     */
    public CraftingSystem(MChest chest) {
        this.chest = chest;
    }

    /**
     * Attempts to craft an item by consuming the required components.
     *
     * @param recipeName
     *            name of the item to craft
     * @param recipeItems
     *            required items to craft it
     */
    public void craftItem(String recipeName, String... recipeItems) {
        System.out.println("Attempting to craft: " + recipeName);
        if (this.chest.canCraft(recipeItems)) {
            for (String item : recipeItems) {
                this.chest.removeItem(item, 1);
            }
            this.chest.addItem(recipeName, 1);
            System.out.println("Crafted " + recipeName + "! Updated chest:");
        } else {
            System.out.println(
                    "Cannot craft " + recipeName + ". Missing components.");
        }
        System.out.println(this.chest);
    }

    /**
     * The main method to demonstrate crafting functionality.
     *
     * @param args
     *            command-line arguments (not used)
     */
    public static void main(String[] args) {
        final int three = 3;
        MChest chest = new MChestOnMap();
        chest.addItem("wood", three);
        chest.addItem("stick", 2);

        CraftingSystem system = new CraftingSystem(chest);
        system.craftItem("sword", "wood", "stick");
        system.craftItem("pickaxe", "wood", "stick", "stone");
    }
}
