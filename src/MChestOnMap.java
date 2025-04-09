import components.map.Map;
import components.map.Map2;

/**
 * {@code MChestOnMap} represents a Minecraft chest using a Map-based
 * representation, where the keys are item names and the values are their
 * quantities. This class implements the primary methods for managing items in
 * the chest.
 *
 * @convention <pre>
 * |$this.items| > 0 and
 * for all x: String, y: Integer in $this.items
 *   (x is the item name and y is the quantity)
 *   the map entries hold valid items and their quantities where
 *   x != null and y >= 0
 * </pre>
 * @correspondence <pre>
 * this = union of entries in $this.items where each entry represents
 * an item and its quantity in the chest.
 * </pre>
 *
 * @author Yuvraj Atre
 */
public class MChestOnMap extends MChestSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Represents the chest's contents as a map of item names to quantities.
     */
    private Map<String, Integer> items;

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor that initializes an empty chest.
     *
     * @ensures this = {}
     */
    public MChestOnMap() {
        this.items = new Map2<>();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(String item, int quantity) {
        assert item != null : "Violation of: item is not null";
        assert quantity > 0 : "Violation of: quantity > 0";

        if (this.containsItem(item)) {
            // If item exists, update the quantity
            this.items.add(item, this.itemQuantity(item) + quantity);
        } else {
            // If item doesn't exist, add it to the chest
            this.items.add(item, quantity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map.Pair<String, Integer> removeItem(String item, int quantity) {
        assert item != null : "Violation of: item is not null";
        assert quantity > 0 : "Violation of: quantity > 0";
        assert this.containsItem(item) : "Violation of: item is in the chest";

        int currentQuantity = this.itemQuantity(item);

        if (currentQuantity <= quantity) {
            Map.Pair<String, Integer> removedPair = this.items.remove(item);
            return removedPair;
        } else {
            // Not gonna lie I didn't know how to bypass not making an instance
            // of Map.Pair so that's why it's scuffed
            this.items.remove(item);
            this.items.add(item, currentQuantity - quantity);
            Map.Pair<String, Integer> removedPair = this.items.remove(item);
            return removedPair;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsItem(String item) {
        assert item != null : "Violation of: item is not null";
        return this.items.hasKey(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int itemQuantity(String item) {
        assert item != null : "Violation of: item is not null";
        if (this.items.hasKey(item)) {
            return this.items.value(item);
        } else {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFull() {
        final int thousand = 1000;
        return this.totalItems() >= thousand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.items = new Map2<>(); // Reset the chest to an empty state
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String result = "MChest: ";
        for (Map.Pair<String, Integer> pair : this.items) {
            result += "[" + pair.key() + ", " + pair.value() + "] ";
        }
        return result.trim();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MChest) {
            return this.items.equals(((MChest) obj).getItems());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.items.hashCode();
    }

    // Implementing Standard<MChest> methods

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    public MChest newInstance() {
        return new MChestOnMap();
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public void transferFrom(MChest other) {
        assert other != null : "Violation of: other chest is not null";
        Map<String, Integer> otherItems = other.getItems();
        for (Map.Pair<String, Integer> pair : otherItems) {
            String item = pair.key();
            int quantity = pair.value();
            this.addItem(item, quantity); // Add each item to this chest
        }
    }

    // Implementing Comparable<MChest> method

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int compareTo(MChest other) {
        return Integer.compare(this.totalItems(), other.totalItems());
    }

}
