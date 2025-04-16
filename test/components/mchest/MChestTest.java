package components.mchest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.list.List;

/**
 * Unit tests for the MChest class and its functionalities.
 */
public class MChestTest {

    /*
     * canCraft tests
     */

    /**
     * Tests the canCraft method when all required items are present.
     */
    @Test
    public void testCanCraftTrue() {
        MChest chest = new MChestOnMap();
        chest.addItem("wood", 2);
        chest.addItem("string", 1);
        assertTrue(chest.canCraft("wood", "string"));
    }

    /**
     * Tests the canCraft method when a required item is missing.
     */
    @Test
    public void testCanCraftFalseMissingItem() {
        MChest chest = new MChestOnMap();
        chest.addItem("wood", 2);
        assertFalse(chest.canCraft("wood", "string"));
    }

    /*
     * totalItems tests
     */

    /**
     * Tests the totalItems method when the chest contains multiple items.
     */
    @Test
    public void testTotalItemsMultipleItems() {
        MChest chest = new MChestOnMap();
        final int three = 3;
        final int seven = 7;
        final int ten = 10;
        chest.addItem("stone", three);
        chest.addItem("torch", seven);
        assertEquals(ten, chest.totalItems());
    }

    /**
     * Tests the totalItems method when the chest is empty.
     */
    @Test
    public void testTotalItemsEmptyChest() {
        MChest chest = new MChestOnMap();
        assertEquals(0, chest.totalItems());
    }

    /*
     * getItemsByQuantity tests
     */

    /**
     * Tests the getItemsByQuantity method when there is exactly one matching
     * item.
     */
    @Test
    public void testGetItemsByQuantityOneMatch() {
        MChest chest = new MChestOnMap();
        final int six = 6;
        final int five = 5;
        chest.addItem("iron", 2);
        chest.addItem("gold", six);

        // Retrieve items with quantity >= 5
        List<String> items = chest.getItemsByQuantity(five);

        // Move cursor to the start
        items.moveToStart();

        // Check that there is one item
        int count = 0;
        while (items.rightLength() > 0) {
            String item = items.rightFront();
            assertEquals("gold", item);
            items.advance();
            count++;
        }

        assertEquals(1, count);
    }

    /**
     * Tests the getItemsByQuantity method when there are multiple matching
     * items.
     */
    @Test
    public void testGetItemsByQuantityMultipleMatches() {
        MChest chest = new MChestOnMap();
        final int five = 5;
        final int six = 6;
        chest.addItem("emerald", five);
        chest.addItem("coal", six);
        chest.addItem("lapis", 2);

        // Retrieve items with quantity >= 5
        List<String> items = chest.getItemsByQuantity(five);

        // Move cursor to the start
        items.moveToStart();

        // Check that there are two items
        int count = 0;
        boolean hasEmerald = false;
        boolean hasCoal = false;

        while (items.rightLength() > 0) {
            String item = items.rightFront();
            if (item.equals("emerald")) {
                hasEmerald = true;
            } else if (item.equals("coal")) {
                hasCoal = true;
            }
            items.advance();
            count++;
        }

        assertEquals(2, count);
        assertTrue(hasEmerald);
        assertTrue(hasCoal);
    }

    /*
     * getItems tests
     */

    /**
     * Tests the getItems method to ensure it returns an accurate copy of the
     * items in the chest.
     */
    @Test
    public void testGetItems() {
        MChest chest = new MChestOnMap();
        final int eight = 8;
        chest.addItem("arrow", eight);
        chest.addItem("bow", 1);
        components.map.Map<String, Integer> items = chest.getItems();
        assertEquals(eight, (int) items.value("arrow"));
        assertEquals(1, (int) items.value("bow"));
    }

    /**
     * Tests the getItems method when the chest is empty to ensure it returns an
     * empty map.
     */
    @Test
    public void testGetItemsEmptyChest() {
        MChest chest = new MChestOnMap();
        components.map.Map<String, Integer> items = chest.getItems();
        assertEquals(0, items.size());
    }

    /*
     * clear tests
     */

    /**
     * Tests the clear method when the chest is non-empty to ensure it removes
     * all items.
     */
    @Test
    public void testClearNonEmpty() {
        MChest chest = new MChestOnMap();
        final int three = 3;
        chest.addItem("apple", three);
        chest.clear();
        assertEquals(0, chest.totalItems());
        assertFalse(chest.containsItem("apple"));
    }

    /**
     * Tests the clear method to ensure it is idempotent when called multiple
     * times.
     */
    @Test
    public void testClearTwice() {
        MChest chest = new MChestOnMap();
        chest.addItem("melon", 1);
        chest.clear();
        chest.clear();
        assertEquals(0, chest.totalItems());
    }

    /*
     * equals and hashCode
     */

    /**
     * Tests the equals method to ensure it returns true when two MChest objects
     * contain the same items in the same quantities.
     */
    @Test
    public void testEqualsTrue() {
        MChest chest1 = new MChestOnMap();
        MChest chest2 = new MChestOnMap();
        chest1.addItem("torch", 2);
        chest2.addItem("torch", 2);
        assertTrue(chest1.equals(chest2));
        assertEquals(chest1.hashCode(), chest2.hashCode());
    }

    /**
     * Tests the equals method to ensure it returns false when two MChest
     * objects contain different items or quantities.
     */
    @Test
    public void testEqualsFalse() {
        MChest chest1 = new MChestOnMap();
        MChest chest2 = new MChestOnMap();
        chest1.addItem("torch", 2);
        chest2.addItem("torch", 1);
        assertFalse(chest1.equals(chest2));
    }

    /*
     * toString
     */

    /**
     * Tests the toString method to ensure it includes the correct item and
     * quantity when the chest is non-empty.
     */
    @Test
    public void testToStringNonEmpty() {
        MChest chest = new MChestOnMap();
        chest.addItem("book", 1);
        String output = chest.toString();
        assertTrue(output.contains("book"));
        assertTrue(output.contains("1"));
    }

    /**
     * Tests the toString method to ensure it includes the correct format when
     * the chest is empty.
     */
    @Test
    public void testToStringEmpty() {
        MChest chest = new MChestOnMap();
        String output = chest.toString();
        assertTrue(output.contains("MChest:"));
    }
}
