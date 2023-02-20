package com.victor.model;

import java.util.List;

public class Hand {
    private List<Card> shuffledCards;
    private List<Card> sortedCards;

    public List<Card> getShuffledCards() {
        return shuffledCards;
    }

    public void setShuffledCards(List<Card> shuffledCards) {
        this.shuffledCards = shuffledCards;
    }

    public List<Card> getSortedCards() {
        return sortedCards;
    }

    public void setSortedCards(List<Card> sortedCards) {
        this.sortedCards = sortedCards;
    }

}
