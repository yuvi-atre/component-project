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
        final int three = 3;
        chest.addItem("diamond", three);
        assertEquals(three, chest.itemQuantity("diamond"));
    }

    /**
     * Tests if adding an existing item to the chest updates the quantity
     * correctly.
     */
    @Test
    public void testAddExistingItem() {
        MChest chest = new MChestOnMap();
        final int five = 5;
        final int seven = 7;
        chest.addItem("iron", five);
        chest.addItem("iron", 2);
        assertEquals(seven, chest.itemQuantity("iron"));
    }

    // Remove item tests

    /**
     * Tests if removing the full quantity of an item updates the chest
     * correctly and removes the item completely.
     */
    @Test
    public void testRemoveFullQuantity() {
        MChest chest = new MChestOnMap();
        final int four = 4;
        chest.addItem("gold", four);
        Pair<String, Integer> removed = chest.removeItem("gold", four);
        assertEquals("gold", removed.key());
        assertEquals(four, (int) removed.value());
        assertFalse(chest.containsItem("gold"));
    }

    /**
     * Tests if removing a partial quantity of an item updates the chest
     * correctly and retains the remaining quantity.
     */
    @Test
    public void testRemovePartialQuantity() {
        MChest chest = new MChestOnMap();
        final int ten = 10;
        final int four = 4;
        final int six = 6;
        chest.addItem("emerald", ten);
        chest.removeItem("emerald", four);
        assertEquals(six, chest.itemQuantity("emerald"));
    }

    // isFull tests

    /**
     * Tests if the chest correctly identifies as not full when the capacity is
     * below the maximum limit.
     */
    @Test
    public void testIsFullFalse() {
        MChest chest = new MChestOnMap();
        final int size = 500;
        chest.addItem("stone", size);
        assertFalse(chest.isFull());
    }

    /**
     * Tests if the chest correctly identifies as full when the maximum capacity
     * is reached.
     */
    @Test
    public void testIsFullTrue() {
        MChest chest = new MChestOnMap();
        final int size = 1000;
        chest.addItem("stone", size);
        assertTrue(chest.isFull());
    }
}
