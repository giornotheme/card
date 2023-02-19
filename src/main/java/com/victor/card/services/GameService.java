package com.victor.card.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.victor.model.Card;
import com.victor.model.Card.CardColor;
import com.victor.model.Card.CardNumber;
import com.victor.utils.CardUtils;
import com.victor.utils.ColorComparator;

@Service
public class GameService {
    public List<Card> takeCards() {
        List<Card> generateAllCards = new ArrayList<Card>();
        generateAllCards = CardUtils.initDeck();
        Collections.shuffle(generateAllCards);

        List<Card> hand = new ArrayList<Card>();
        for (int i = 0; i < 10; i++) {
            hand.add(generateAllCards.get(i));
        }
        return hand;
    }

    public List<Card> rankCards(List<Card> hand) {

        List<CardColor> rankedColors = CardUtils.rankCardColors();
        List<CardNumber> rankedNumbers = CardUtils.rankCardNumbers();

        List<Card> newHand = new ArrayList<Card>();
        for (int i = 0; i < rankedColors.size(); i++){
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(j).getColor() == rankedColors.get(i)){
                    newHand.add(hand.get(j));
                }
            }
        }

        for (int i = 0; i < rankedNumbers.size(); i++){
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(j).getValue() == rankedNumbers.get(i)){
                    newHand.add(hand.get(j));
                }
            }
        }

        return newHand;
    }

}
