import components.list.List;
import components.list.List2;
import components.map.Map;
import components.map.Map.Pair;
import components.map.Map2;

/**
 * Abstract class that implements the secondary methods of MChest.
 */
public abstract class MChestSecondary implements MChest {

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public Map<String, Integer> getItems() {
        Map<String, Integer> itemsCopy = new Map2<>();
        for (Pair<String, Integer> pair : this.getItems()) {
            itemsCopy.add(pair.key(), pair.value());
        }
        return itemsCopy;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public boolean canCraft(String... items) {
        assert items != null
                && items.length > 0 : "items must not be null or empty";
        for (String item : items) {
            if (!this.containsItem(item)) {
                return false;
            }
        }
        return true;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int totalItems() {
        int total = 0;
        for (Pair<String, Integer> pair : this.getItems()) {
            total += pair.value();
        }
        return total;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public List<String> getItemsByQuantity(int minQuantity) {
        assert minQuantity >= 0 : "minQuantity must be non-negative";
        List<String> result = new List2<>();
        for (Pair<String, Integer> pair : this.getItems()) {
            if (pair.value() >= minQuantity) {
                result.addRightFront(pair.key());
            }
        }
        return result;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public void clear() {
        while (this.getItems().size() > 0) {
            Pair<String, Integer> pair = this.getItems().removeAny();
            this.removeItem(pair.key(), pair.value());
        }
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        String result = "MChest: ";
        for (Pair<String, Integer> pair : this.getItems()) {
            result += "[" + pair.key() + ", " + pair.value() + "] ";
        }
        return result.trim();
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public boolean equals(Object obj) {
        return obj == this
                || (obj instanceof MChest
                && this.getItems().equals(((MChest) obj).getItems()));
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        return this.getItems().hashCode();
    }
}