package com.victor.card.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.victor.model.Card;
import com.victor.model.Card.CardColor;
import com.victor.model.Card.CardValues;
import com.victor.model.Game;
import com.victor.model.Hand;
import com.victor.utils.CardUtils;

@Service
public class GameService {

    private List<Game> gamesDatabase = new ArrayList<Game>();

    public Game initGame() {
        Game game = new Game();

        List<Card> deck = new ArrayList<Card>();

        Hand hand = new Hand();
        List<Card> shuffledCards = new ArrayList<Card>();
        List<Card> sortedCards = new ArrayList<Card>();
        hand.setShuffledCards(shuffledCards);
        hand.setSortedCards(sortedCards);

        game.setColorOrder();
        game.setValueOrder();
        game.setDeck(deck);
        game.setHand(hand);

        gamesDatabase.add(game);
        return game;
    }

    public List<Card> createDeck(List<Card> deck) {
        List<Card> generateAllCards = new ArrayList<Card>();
        generateAllCards = CardUtils.initDeck(deck);
        Collections.shuffle(generateAllCards);
        return generateAllCards;
    }

    public Hand drawCards(List<Card> deck, Hand existingHand, int numberOfCards) {
        if (deck.isEmpty() || numberOfCards == 0 || numberOfCards > deck.size()) {
            return existingHand;
        }
        for (int i = 0; i < numberOfCards; i++) {
            existingHand.getShuffledCards().add(deck.get(0));
            deck.remove(deck.get(0));
        }
        return existingHand;
    }

    public Hand rankCardsByColorAndValues(Hand hand, List<CardColor> colorOrder,
            List<CardValues> valueOrder) {

        // On trie d'abord la main en fonction des valeurs
        List<Card> newHandByValues = new ArrayList<Card>();
        for (int i = 0; i < valueOrder.size(); i++) {
            for (int j = 0; j < hand.getShuffledCards().size(); j++) {
                if (hand.getShuffledCards().get(j).getValue() == valueOrder.get(i)) {
                    newHandByValues.add(hand.getShuffledCards().get(j));
                }
            }
        }

        // Puis on retrie la main en fonction des couleurs
        List<Card> finalHand = new ArrayList<Card>();
        for (int i = 0; i < colorOrder.size(); i++) {
            for (int j = 0; j < newHandByValues.size(); j++) {
                if (newHandByValues.get(j).getColor() == colorOrder.get(i)) {
                    finalHand.add(newHandByValues.get(j));
                }
            }
        }

        hand.setSortedCards(finalHand);

        return hand;
    }

    public int getGameCount() {
        return gamesDatabase.size();
    }

    public Game getGameById(String id) {
        if (gamesDatabase.isEmpty()) {
            return null;
        }

        for (Game game : gamesDatabase) {
            if (game.getId().toString().equals(id)) {
                return game;
            }
        }
        return null;
    }

    public void deleteGame(Game game) {
        gamesDatabase.remove(game);
    }

    public List<Game> getAllGames() {
        return gamesDatabase;
    }
}
