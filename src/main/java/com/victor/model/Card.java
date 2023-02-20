package com.victor.model;

public class Card {

    public enum CardValues {
        DEUX(2), TROIS(3), QUATRE(4), CINQ(5), SIX(6), SEPT(7),
        HUIT(8), NEUF(9), DIX(10), VALET(11), DAME(12), ROI(13),
        AS(14);

        private int number;

        private CardValues(int i) {
            this.number = i;
        }

        public int getValue() {
            return number;
        }
    }

    public enum CardColor {
        PIQUE, CARREAU, COEUR, TREFLE;
    }

    private final CardValues value;
    private final CardColor color;

    public Card(CardValues value, CardColor color) {
        this.value = value;
        this.color = color;
    }

    public CardValues getValue() {
        return value;
    }

    public CardColor getColor() {
        return color;
    }

}
