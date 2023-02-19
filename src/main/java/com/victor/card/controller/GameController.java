package com.victor.card.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.victor.card.services.GameService;
import com.victor.model.Card;
import com.victor.utils.CardUtils;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String loadWelcomePage(Model model) {
        List<Card> hand = gameService.takeCards();
        List<Card> sortedByColorHand = gameService.rankCards(hand);

        return "game";
    }
}
