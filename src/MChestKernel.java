package components.mchest;

import components.standard.Standard;

public interface MChestKernel extends Standard<MChest> {
    public record Card(String suit, String rank) {
    }

    public static void main(String[] args) {
        System.out.println(new Card("Hearts", "Ace"));
        Card c = new Card("Spades", "Queen");
    }
}