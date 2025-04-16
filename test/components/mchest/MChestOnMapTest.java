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

    // --- Add Item Tests ---

    /**
     * Tests adding a new item to the chest.
     */
    @Test
    public void testAddNewItem() {
        MChest chest = new MChestOnMap();
        final int three = 3;
        chest.addItem("diamond", three);
        assertEquals(three, chest.itemQuantity("diamond"));
    }

    /**
     * Tests adding to an existing item in the chest.
     */
    @Test
    public void testAddExistingItem() {
        MChest chest = new MChestOnMap();
        final int five = 5;
        final int two = 2;
        final int seven = 7;
        chest.addItem("iron", five);
        chest.addItem("iron", two);
        assertEquals(seven, chest.itemQuantity("iron"));
    }

    /**
     * Tests adding items to reach the chest’s full capacity.
     */
    @Test
    public void testAddItemToReachFull() {
        MChest chest = new MChestOnMap();
        final int almostFull = 999;
        final int one = 1;
        chest.addItem("feather", almostFull);
        assertFalse(chest.isFull());
        chest.addItem("stick", one);
        assertTrue(chest.isFull());
    }

    // --- Remove Item Tests ---

    /**
     * Tests removing the full quantity of an item.
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
     * Tests removing a partial quantity and retaining the remainder.
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

    /**
     * Tests removing an item after multiple additions.
     */
    @Test
    public void testRemoveAfterMultipleAdds() {
        MChest chest = new MChestOnMap();
        final int three = 3;
        final int two = 2;
        final int five = 5;
        chest.addItem("arrow", three);
        chest.addItem("arrow", two);
        Pair<String, Integer> removed = chest.removeItem("arrow", five);
        assertEquals(five, (int) removed.value());
        assertFalse(chest.containsItem("arrow"));
    }

    // --- isFull Tests ---

    /**
     * Tests isFull() when the chest is not full.
     */
    @Test
    public void testIsFullFalse() {
        MChest chest = new MChestOnMap();
        final int halfFull = 500;
        chest.addItem("stone", halfFull);
        assertFalse(chest.isFull());
    }

    /**
     * Tests isFull() when the chest reaches its full capacity.
     */
    @Test
    public void testIsFullTrue() {
        MChest chest = new MChestOnMap();
        final int full = 1000;
        chest.addItem("stone", full);
        assertTrue(chest.isFull());
    }

    // --- containsItem Tests ---

    /**
     * Tests containsItem() when the item is in the chest.
     */
    @Test
    public void testContainsItemTrue() {
        MChest chest = new MChestOnMap();
        final int one = 1;
        chest.addItem("apple", one);
        assertTrue(chest.containsItem("apple"));
    }

    /**
     * Tests containsItem() when the item is not in the chest.
     */
    @Test
    public void testContainsItemFalse() {
        MChest chest = new MChestOnMap();
        assertFalse(chest.containsItem("netherite"));
    }

    // --- itemQuantity Tests ---

    /**
     * Tests itemQuantity() for an existing item.
     */
    @Test
    public void testItemQuantityExisting() {
        MChest chest = new MChestOnMap();
        final int four = 4;
        chest.addItem("bread", four);
        assertEquals(four, chest.itemQuantity("bread"));
    }

    /**
     * Tests itemQuantity() for a non-existing item.
     */
    @Test
    public void testItemQuantityNonexistent() {
        MChest chest = new MChestOnMap();
        final int zero = 0;
        assertEquals(zero, chest.itemQuantity("obsidian"));
    }
}
