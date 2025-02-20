import components.map.Map;
import components.map.Map2;

/**
 * This class represents a Minecraft Chest.
 */
public class MinecraftChest {

    /*
     * Private Members --------------------------------------------------------
     */

    /**
     * Represents the max size of the Minecraft chest.
     */
    private final int maxSize = 57;

    /**
     * Represents the current size of the Minecraft chest (Amount of slots
     * taken).
     */
    private int size;

    /**
     * Represents the items in the Minecraft chest.
     */
    private Map<String, Integer> items;

    /*
     * Constructor(s)
     * -----------------------------------------------------------
     */

    /**
     * Constructor for the MinecraftChest class. Initializes a new
     * MinecraftChest object with a default size of 0 and an empty items map.
     */
    public MinecraftChest() {
        this.size = 0;
        this.items = new Map2<String, Integer>();
    }

    /*
     * Kernel Methods ---------------------------------------------------------
     */

    /**
     * Adds an item to the chest. If the item already exists, its quantity is
     * increased by the specified amount. Otherwise, the item is added with the
     * specified quantity.
     *
     * @requires isFull() = false
     * @param item
     *            the name of the item to add
     * @param quantity
     *            the quantity of the item to add
     * @ensures [item is added to the chest with the specified quantity]
     */
    public void addItem(String item, int quantity) {
        // Write exception statements later
        if (this.items.hasKey(item)) {
            this.items.replaceValue(item, this.items.value(item) + quantity);
        } else {
            this.items.add(item, quantity);
        }
        this.size++;
    }

    /**
     * Removes a specified quantity of an item from the chest. If the quantity
     * of the item becomes zero, the item is removed from the chest.
     *
     * @requires item is in the chest and @param quantity <= current item
     *           quantity
     * @param item
     *            the name of the item to remove
     * @param quantity
     *            the quantity of the item to remove
     * @return a pair containing the item name and the quantity removed
     */
    public Map.Pair<String, Integer> removeItem(String item, int quantity) {
        // Write exception statements later
        Map.Pair<String, Integer> removed = this.items.remove(item);
        if (quantity == removed.value()) {
            this.size--;
        } else {
            this.items.add(item, removed.value() - quantity);
        }
        return removed;
    }

    public boolean containsItem(String item) {
        // Check if the chest contains the item
        return this.size >= this.maxSize;
    }

    public int itemQuantity(String item) {
        // Get the quantity of the item in the chest
        return 0;
    }

    public boolean isFull() {
        // Check if the chest is full
        return false;
    }

    /*
     * Secondary Methods
     * ---------------------------------------------------------
     */

    public Map2<String, Integer> getItems() {
        return this.items;
    }

    public boolean canCraft(String... items) {

    }

    /**
     * Main method to run the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Minecraft Chest");
    }
}
