package components.mchest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.list.List;

public class MChestTest {

    /*
     * canCraft
     */
    @Test
    public void testCanCraft_True() {
        MChest chest = new MChestOnMap();
        chest.addItem("wood", 2);
        chest.addItem("string", 1);
        assertTrue(chest.canCraft("wood", "string"));
    }

    @Test
    public void testCanCraft_False_MissingItem() {
        MChest chest = new MChestOnMap();
        chest.addItem("wood", 2);
        assertFalse(chest.canCraft("wood", "string")); // missing "string"
    }

    /*
     * totalItems
     */
    @Test
    public void testTotalItems_MultipleItems() {
        MChest chest = new MChestOnMap();
        chest.addItem("stone", 3);
        chest.addItem("torch", 7);
        assertEquals(10, chest.totalItems());
    }

    @Test
    public void testTotalItems_EmptyChest() {
        MChest chest = new MChestOnMap();
        assertEquals(0, chest.totalItems());
    }

    /*
     * getItemsByQuantity
     */
    @Test
    public void testGetItemsByQuantity_OneMatch() {
        MChest chest = new MChestOnMap();
        chest.addItem("iron", 2);
        chest.addItem("gold", 6);
        List<String> result = chest.getItemsByQuantity(5);
        assertEquals(1, result.length());
        assertEquals("gold", result.entry(0));
    }

    @Test
    public void testGetItemsByQuantity_MultipleMatches() {
        MChest chest = new MChestOnMap();
        chest.addItem("emerald", 5);
        chest.addItem("coal", 5);
        chest.addItem("lapis", 3);
        List<String> result = chest.getItemsByQuantity(4);
        assertTrue(result.contains("emerald"));
        assertTrue(result.contains("coal"));
        assertFalse(result.contains("lapis"));
    }

    /*
     * getItems
     */
    @Test
    public void testGetItems_AccurateCopy() {
        MChest chest = new MChestOnMap();
        chest.addItem("arrow", 8);
        chest.addItem("bow", 1);
        components.map.Map<String, Integer> items = chest.getItems();
        assertEquals(8, (int) items.value("arrow"));
        assertEquals(1, (int) items.value("bow"));
    }

    @Test
    public void testGetItems_EmptyChest() {
        MChest chest = new MChestOnMap();
        components.map.Map<String, Integer> items = chest.getItems();
        assertEquals(0, items.size());
    }

    /*
     * clear
     */
    @Test
    public void testClear_NonEmpty() {
        MChest chest = new MChestOnMap();
        chest.addItem("apple", 3);
        chest.clear();
        assertEquals(0, chest.totalItems());
        assertFalse(chest.containsItem("apple"));
    }

    @Test
    public void testClear_Twice() {
        MChest chest = new MChestOnMap();
        chest.addItem("melon", 1);
        chest.clear();
        chest.clear(); // should be idempotent
        assertEquals(0, chest.totalItems());
    }

    /*
     * equals and hashCode
     */
    @Test
    public void testEquals_True() {
        MChest chest1 = new MChestOnMap();
        MChest chest2 = new MChestOnMap();
        chest1.addItem("torch", 2);
        chest2.addItem("torch", 2);
        assertTrue(chest1.equals(chest2));
        assertEquals(chest1.hashCode(), chest2.hashCode());
    }

    @Test
    public void testEquals_False() {
        MChest chest1 = new MChestOnMap();
        MChest chest2 = new MChestOnMap();
        chest1.addItem("torch", 2);
        chest2.addItem("torch", 1);
        assertFalse(chest1.equals(chest2));
    }

    /*
     * toString
     */
    @Test
    public void testToString_NonEmpty() {
        MChest chest = new MChestOnMap();
        chest.addItem("book", 1);
        String output = chest.toString();
        assertTrue(output.contains("book"));
        assertTrue(output.contains("1"));
    }

    @Test
    public void testToString_Empty() {
        MChest chest = new MChestOnMap();
        String output = chest.toString();
        assertTrue(output.contains("MChest:"));
    }
}
