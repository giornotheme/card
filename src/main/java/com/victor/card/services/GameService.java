package com.victor.card.services;

import com.victor.model.Card;
import com.victor.model.Card.CardColor;
import com.victor.model.Card.CardValues;
import com.victor.model.Game;
import com.victor.model.Hand;
import com.victor.utils.CardUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final List<Game> gamesDatabase = new ArrayList<>();

    public Game initGame() {
        Game game = new Game();

        List<Card> deck = new ArrayList<>();

        Hand hand = new Hand();
        List<Card> shuffledCards = new ArrayList<>();
        List<Card> sortedCards = new ArrayList<>();
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
        List<Card> generateAllCards;
        generateAllCards = CardUtils.initDeck(deck);
        Collections.shuffle(generateAllCards);
        return generateAllCards;
    }

    public Hand drawCards(List<Card> deck, Hand existingHand, int numberOfCards) {
        if (deck.isEmpty() || numberOfCards == 0 || numberOfCards > deck.size()) {
            return existingHand;
        }

        // Example of an async draw
        // Thread asyncDraw = new Thread(() -> {
        //     for (int i = 0; i < numberOfCards; i++) {
        //         existingHand.getShuffledCards().add(deck.get(0));
        //         deck.remove(deck.get(0));
        //     }
        // });
        // asyncDraw.start();
        
        for (int i = 0; i < numberOfCards; i++) {
            existingHand.getShuffledCards().add(deck.get(0));
            deck.remove(deck.get(0));
        }
        return existingHand;
    }

    public Hand rankCardsByColorAndValues(Hand hand, List<CardColor> colorOrder,
            List<CardValues> valueOrder) {

        // On trie d'abord la main en fonction des valeurs
        List<Card> newHandByValues = new ArrayList<>();
        for (CardValues cardValues : valueOrder) {
            for (int j = 0; j < hand.getShuffledCards().size(); j++) {
                if (hand.getShuffledCards().get(j).getValue() == cardValues) {
                    newHandByValues.add(hand.getShuffledCards().get(j));
                }
            }
        }

        // Puis on retrie la main en fonction des couleurs
        List<Card> finalHand = new ArrayList<>();
        for (CardColor cardColor : colorOrder) {
            for (Card newHandByValue : newHandByValues) {
                if (newHandByValue.getColor() == cardColor) {
                    finalHand.add(newHandByValue);
                }
            }
        }

        hand.setSortedCards(finalHand);

        return hand;
    }

    public int getGameCount() {
        return gamesDatabase.size();
    }

    public Optional<Game> getGameById(String id) {
        if (gamesDatabase.isEmpty()) {
            return Optional.empty();
        }

        for (Game game : gamesDatabase) {
            if (game.getId().toString().equals(id)) {
                return Optional.of(game);
            }
        }
        return Optional.empty();
    }

    public void deleteGame(Game game) {
        gamesDatabase.remove(game);
    }
    public void deleteAllGame() {
        gamesDatabase.clear();
    }

    public List<Game> getAllGames() {
        return gamesDatabase;
    }
}
