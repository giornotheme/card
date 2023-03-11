package com.victor.card.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.victor.card.services.GameService;
import com.victor.model.Card;
import com.victor.model.Game;
import com.victor.model.Hand;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String startGame(Model model) {

        Game game = gameService.initGame();
        List<Card> deck = game.getDeck();
        Hand existingHand = game.getHand();

        model.addAttribute("existingHand", existingHand);
        model.addAttribute("deck", deck);
        model.addAttribute("gameId", game.getId().toString());
        model.addAttribute("colorOrder", game.getColorOrder());
        model.addAttribute("valueOrder", game.getValueOrder());

        return "game";

    }

    @PostMapping("/draw/{id}")
    public String drawCards(@RequestParam int numberOfCards, Model model,
            @PathVariable String id) {

        Optional<Game> optionalGame = gameService.getGameById(id);
        if (optionalGame.isEmpty()) {
            return startGame(model);
        }

        Game game = optionalGame.get();

        List<Card> deck = game.getDeck();

        Hand existingHand = game.getHand();

        if (numberOfCards > deck.size() || numberOfCards == 0) {
            model.addAttribute("error", "erreur");
            model.addAttribute("existingHand", existingHand);
            model.addAttribute("deck", deck);
            model.addAttribute("game", game);
            model.addAttribute("gameId", game.getId().toString());
            model.addAttribute("colorOrder", game.getColorOrder());
            model.addAttribute("valueOrder", game.getValueOrder());
            return "game";
        }

        existingHand = gameService.drawCards(deck, existingHand, numberOfCards);

        model.addAttribute("existingHand", existingHand);
        model.addAttribute("deck", deck);
        model.addAttribute("game", game);
        model.addAttribute("gameId", game.getId().toString());
        model.addAttribute("colorOrder", game.getColorOrder());
        model.addAttribute("valueOrder", game.getValueOrder());

        return "game";
    }

    @PostMapping("/sort/{id}")
    public String sortCards(Model model, @PathVariable String id) {

        Optional<Game> optionalGame = gameService.getGameById(id);
        if (optionalGame.isEmpty()) {
            return startGame(model);
        }

        Game game = optionalGame.get();

        List<Card> deck = optionalGame.map(Game::getDeck).orElse(null);
        Hand existingHand = optionalGame.map(Game::getHand).orElse(null);

        existingHand = gameService.rankCardsByColorAndValues(existingHand, game.getColorOrder(),
                game.getValueOrder());

        model.addAttribute("existingHand", existingHand);
        model.addAttribute("deck", deck);
        model.addAttribute("game", game);
        model.addAttribute("gameId", game.getId().toString());
        model.addAttribute("colorOrder", game.getColorOrder());
        model.addAttribute("valueOrder", game.getValueOrder());

        return "game";
    }

    @GetMapping("/game/{id}")
    @ResponseBody
    public ResponseEntity<?> getGameById(@PathVariable String id) {
        Optional<Game> optionalGame = gameService.getGameById(id);
        if (optionalGame.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game with ID " + id + " not found");
        }

        return ResponseEntity.ok().body(optionalGame.get());
    }

    @DeleteMapping("/game/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteGameById(@PathVariable String id) {
        Optional<Game> optionalGame = gameService.getGameById(id);
        if (optionalGame.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game with ID " + id + " not found");
        }

        gameService.deleteGame(optionalGame.get());
        return ResponseEntity.ok().body("Successful delete");
    }
}
