package com.victor.model;

public class Card {

    public enum CardNumber {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13),
        ACE(14);

        private int number;

        private CardNumber(int i) {
            this.number = i;
        }

        public int getNumber() {
            return number;
        }
    }

    public enum CardColor {
        PIQUE, CARREAU, COEUR, TREFLE;
    }
    
    private final CardNumber value;
    private final CardColor color;
    // private final String rank;

    public Card(CardNumber value, CardColor color) {
        this.value = value;
        this.color = color;
    }

    public CardNumber getValue() {
        return value;
    }

    public CardColor getColor() {
        return color;
    }
    
}
