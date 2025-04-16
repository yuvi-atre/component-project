
import components.list.List;
import components.mchest.MChest;
import components.mchest.MChestOnMap;

/**
 * Demonstrates the usage of the MChest class and its functionalities. This is a
 * utility class and should not be instantiated.
 */
public final class MChestDemo {

        // Private constructor to prevent instantiation
        /**
         * Private constructor to prevent instantiation of this utility class.
         */
        private MChestDemo() {
                throw new UnsupportedOperationException("Utility class");
        }

        /**
         * The main method to demonstrate the functionalities of the MChest
         * class.
         *
         * @param args
         *                Command-line arguments (not used).
         */
        public static void main(String[] args) {
                System.out.println("=== MINECRAFT CHEST DEMO ===");

                final int five = 5;
                final int three = 3;
                final int ten = 10;

                MChest chest = new MChestOnMap();

                System.out.println("\n[1] Adding Items:");
                chest.addItem("iron", five);
                chest.addItem("diamond", 2);
                chest.addItem("stick", three);
                chest.addItem("gold", ten);
                System.out.println("Contents: " + chest);

                System.out.println("\n[2] Checking item quantity:");
                System.out.println("Quantity of 'iron': "
                                + chest.itemQuantity("iron"));

                System.out.println("\n[3] Checking if item exists:");
                System.out.println("Contains 'diamond'? "
                                + chest.containsItem("diamond"));
                System.out.println("Contains 'emerald'? "
                                + chest.containsItem("emerald"));

                System.out.println("\n[4] Removing items:");
                chest.removeItem("gold", five);
                System.out.println("Removed 5 gold. New contents: " + chest);

                System.out.println("\n[5] Checking if chest is full:");
                System.out.println("Is full? " + chest.isFull());

                System.out.println("\n[6] Getting total number of items:");
                System.out.println("Total items: " + chest.totalItems());

                System.out.println("\n[7] Getting items by quantity (>= 3):");
                List<String> highQuantityItems = chest.getItemsByQuantity(3);
                for (String item : highQuantityItems) {
                        System.out.println("- " + item);
                }

                System.out.println("\n[8] Can craft using ['iron', 'stick']?");
                System.out.println("Can craft? "
                                + chest.canCraft("iron", "stick"));

                System.out.println("\n[9] Copying chest with getItems:");
                System.out.println("Items map: " + chest.getItems());

                System.out.println("\n[10] toString override:");
                System.out.println(chest.toString());

                System.out.println(
                                "\n[11] Creating another identical chest and "
                                                + "comparing with equals:");
                MChest otherChest = new MChestOnMap();
                otherChest.addItem("iron", five);
                otherChest.addItem("diamond", 2);
                otherChest.addItem("stick", three);
                otherChest.addItem("gold", five);
                System.out.println("Is equal to other chest? "
                                + chest.equals(otherChest));

                System.out.println("\n[12] Clearing the chest:");
                chest.clear();
                System.out.println("Contents after clear: " + chest);
                System.out.println("Is empty? " + (chest.totalItems() == 0));

                System.out.println("\n=== END OF DEMO ===");
        }
}
