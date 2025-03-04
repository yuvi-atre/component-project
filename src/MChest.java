package components.minecraftchest;

import components.list.List;
import components.map.Map;

public interface MinecraftChest extends Comparable<MChest>, MChestKernel {
    /**
     * Retrieves the items stored in the chest.
     *
     * @return a map where the keys are item names and the values are the
     *         quantities of each item.
     * @ensures the returned map accurately reflects the items stored in the
     *          chest
     */
    public Map<String, Integer> getItems();

    /**
     * Checks whether the chest contains the specified items.
     *
     * @param items
     *            the items to check for
     * @return true if the chest contains the items, false otherwise
     * @requires items != null && items.length > 0
     * @ensures {@code true} if all items are present in the chest,
     *          {@code false} otherwise
     */
    public boolean canCraft(String... items);

    /**
     * Counts all the items in the chest.
     *
     * @return the total number of items in the chest
     * @ensures the returned total reflects the sum of all item quantities
     *          stored in the chest
     */
    public int totalItems();

    /**
     * Returns the names of items in the chest that have a quantity greater than
     * or equal to the specified amount.
     *
     * @param minQuantity
     *            the minimum quantity of an item to retrieve
     * @return a list of item names that have a quantity greater than or equal
     *         to the specified amount
     * @requires minQuantity >= 0
     * @ensures the returned list contains only items with quantities >=
     *          {@code minQuantity}
     */
    public List<String> getItemsByQuantity(int minQuantity);

    /**
     * @ensures the chest is empty after the method call
     */
    @Override
    public void clear();
}