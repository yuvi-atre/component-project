import components.list.List;
import components.list.List2;
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

    /**
     * Checks the chest for the specified item.
     *
     * @param item
     *            the name of the item to check for
     * @return true if the chest contains the item, false otherwise
     */
    public boolean containsItem(String item) {
        return this.items.hasKey(item);
    }

    /**
     * Returns the quantity of the specified item in the chest.
     *
     * @requires item is in the chest
     * @param item
     *            the name of the item to check for
     * @return the quantity of the item in the chest
     */
    public int itemQuantity(String item) {
        return this.items.value(item);
    }

    /**
     * Returns whether or not the chest is full.
     *
     * @return true if the chest is full, false otherwise
     */
    public boolean isFull() {
        return this.size == this.maxSize;
    }

    /*
     * Secondary Methods
     * ---------------------------------------------------------
     */

    /**
     * Retrieves the items stored in the chest.
     *
     * @return a map where the keys are item names and the values are the
     *         quantities of each item.
     */
    public Map<String, Integer> getItems() {
        return this.items;
    }

    /**
     * Checks whether the chest can contains the specified items.
     *
     * @param items
     * @return true if the chest contains the items, false otherwise
     */
    public boolean canCraft(String... items) {
        boolean canCraft = true;
        for (String item : items) {
            if (!this.containsItem(item)) {
                canCraft = false;
            }
        }
        return canCraft;
    }

    /**
     * Counts all the items in the chest.
     *
     * @return the total number of items in the chest
     */
    public int totalItems() {
        // This made more sense with hashMaps because I could call the keySet method
        int total = 0;
        for (Map.Pair<String, Integer> item : this.items) {
            total += this.itemQuantity(item.key());
        }
        return total;
    }

    /**
     * Returns the name of items in the chest that have a quantity greater than
     * or equal to the specified amount.
     *
     * @param minQuantity
     *            the minimum quantity of an item to retrieve
     * @return a list of item names that have a quantity greater than or equal
     *         to the specified amount
     */
    public List<String> getItemsByQuantity(int minQuantity) {
        List<String> items = new List2<String>();
        for (Map.Pair<String, Integer> item : this.items) {
            String itemName = item.key();
            if (this.containsItem(itemName)
                    && this.itemQuantity(itemName) >= minQuantity) {
                items.addRightFront(itemName);
            }
        }
        return items;
    }

    /**
     * Clears the chest of all items, resets chest to default state.
     */
    public void clear() {
        this.items.clear();
        this.size = 0;
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
