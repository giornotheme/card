package com.victor.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.victor.model.Card;
import com.victor.model.Card.CardColor;
import com.victor.model.Card.CardNumber;

public class CardUtils {

    public final static List<Card> initDeck() {
        List<Card> deck = new ArrayList<Card>();
        for (CardColor color : CardColor.values()) {
            for (CardNumber number : CardNumber.values()) {
                Card card = new Card(number, color);
                deck.add(card);
            }
        }
        return deck;
    }

    public static List<CardNumber> rankCardNumbers() {
        List<CardNumber> rankedCards = new ArrayList<CardNumber>(Arrays.asList(CardNumber.values()));
        Collections.shuffle(rankedCards);
        return rankedCards;
    }

    public static List<CardColor> rankCardColors() {
        List<CardColor> rankedColors = new ArrayList<CardColor>(Arrays.asList(CardColor.values()));
        Collections.shuffle(rankedColors);
        return rankedColors;
    }

    public static Map<String, Integer> ColorsToMap(List<CardColor> colors) {
        Map<String, Integer> colorsOrder = new HashMap<String, Integer>();
        for (int i = 0; i < colors.size(); i++) {
            String colorName = colors.get(i).name();
            colorsOrder.put(colorName, i);
        }
        return colorsOrder;
    }

}
