package components.mchest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map.Pair;

/**
 * Unit tests for the MChestOnMap class, which manages items in a chest on a
 * map.
 */
public class MChestOnMapTest {

    // Add item tests

    /**
     * Tests if adding a new item to the chest updates the quantity correctly.
     */
    @Test
    public void testAddNewItem() {
        MChest chest = new MChestOnMap();
        chest.addItem("diamond", 3);
        assertEquals(3, chest.itemQuantity("diamond"));
    }

    /**
     * Tests if adding an existing item to the chest updates the quantity
     * correctly.
     */
    @Test
    public void testAddExistingItem() {
        MChest chest = new MChestOnMap();
        chest.addItem("iron", 5);
        chest.addItem("iron", 2);
        assertEquals(7, chest.itemQuantity("iron"));
    }

    // Remove item tests

    /**
     * Tests if removing the full quantity of an item updates the chest
     * correctly and removes the item completely.
     */
    @Test
    public void testRemoveFullQuantity() {
        MChest chest = new MChestOnMap();
        chest.addItem("gold", 4);
        Pair<String, Integer> removed = chest.removeItem("gold", 4);
        assertEquals("gold", removed.key());
        assertEquals(4, (int) removed.value());
        assertFalse(chest.containsItem("gold"));
    }

    /**
     * Tests if removing a partial quantity of an item updates the chest
     * correctly and retains the remaining quantity.
     */
    @Test
    public void testRemovePartialQuantity() {
        MChest chest = new MChestOnMap();
        chest.addItem("emerald", 10);
        chest.removeItem("emerald", 4);
        assertEquals(6, chest.itemQuantity("emerald"));
    }

    // isFull tests

    /**
     * Tests if the chest correctly identifies as not full when the capacity is
     * below the maximum limit.
     */
    @Test
    public void testIsFullFalse() {
        MChest chest = new MChestOnMap();
        chest.addItem("stone", 500);
        assertFalse(chest.isFull());
    }

    /**
     * Tests if the chest correctly identifies as full when the maximum capacity
     * is reached.
     */
    @Test
    public void testIsFullTrue() {
        MChest chest = new MChestOnMap();
        chest.addItem("stone", 1000);
        assertTrue(chest.isFull());
    }
}
