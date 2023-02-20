package com.victor.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.victor.model.Card.CardColor;
import com.victor.model.Card.CardValues;
import com.victor.utils.CardUtils;

public class Game {

    private UUID id = UUID.randomUUID();
    private List<CardColor> colorOrder;
    private List<CardValues> valueOrder;
    private List<Card> deck;
    private Hand hand;

    public UUID getId() {
        return id;
    }

    public List<CardColor> getColorOrder() {
        return colorOrder;
    }

    public void setColorOrder() {
        this.colorOrder = new ArrayList<CardColor>(Arrays.asList(CardColor.values()));
        Collections.shuffle(this.colorOrder);
    }

    public List<CardValues> getValueOrder() {
        return valueOrder;
    }

    public void setValueOrder() {
        this.valueOrder = new ArrayList<CardValues>(Arrays.asList(CardValues.values()));
        Collections.shuffle(valueOrder);
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = CardUtils.initDeck(deck);
        Collections.shuffle(this.deck);
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

}
