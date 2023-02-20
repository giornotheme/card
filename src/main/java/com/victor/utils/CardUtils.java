package com.victor.utils;

import java.util.List;

import com.victor.model.Card;
import com.victor.model.Card.CardColor;
import com.victor.model.Card.CardValues;

public class CardUtils {

    public final static List<Card> initDeck(List<Card> deck) {
        for (CardColor color : CardColor.values()) {
            for (CardValues number : CardValues.values()) {
                Card card = new Card(number, color);
                deck.add(card);
            }
        }
        return deck;
    }
}
