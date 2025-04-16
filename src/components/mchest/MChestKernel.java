package components.mchest;

import components.map.Map;
import components.standard.Standard;

/**
 * Minecraft chest kernel component with primary methods for managing items.
 *
 * @mathmodel type MinecraftChestKernel is modeled by a Map<String, Integer>
 *            where the key is the name of the item and the value is the number
 *            of items of that type in the chest.
 */
public interface MChestKernel extends Standard<MChest> {

    /**
     * Adds an item to the chest. If the item already exists, its quantity is
     * increased.
     *
     * @param item
     *            the name of the item to add
     * @param quantity
     *            the quantity of the item to add
     * @updates this
     * @requires isFull() = false
     * @ensures the item is added to the chest with the specified quantity
     */
    void addItem(String item, int quantity);

    /**
     * Removes a specified quantity of an item from the chest.
     *
     * @param item
     *            the name of the item to remove
     * @param quantity
     *            the quantity of the item to remove
     * @return a pair containing the item name and the quantity removed
     * @requires item is in the chest and quantity <= current item quantity
     * @ensures the item is removed or updated correctly
     */
    Map.Pair<String, Integer> removeItem(String item, int quantity);

    /**
     * Checks if the chest contains a specific item.
     *
     * @param item
     *            the name of the item to check for
     * @return true if the chest contains the item, false otherwise
     */
    boolean containsItem(String item);

    /**
     * Returns the quantity of a specific item in the chest.
     *
     * @param item
     *            the name of the item to check for
     * @return the quantity of the item in the chest
     * @requires item is in the chest
     */
    int itemQuantity(String item);

    /**
     * Returns whether or not the chest is full.
     *
     * @return true if the chest is full, false otherwise
     */
    boolean isFull();
}
