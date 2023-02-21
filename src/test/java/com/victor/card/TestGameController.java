package com.victor.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.card.services.GameService;
import com.victor.model.Game;

@SpringBootTest
@AutoConfigureMockMvc
public class TestGameController {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    GameService gameService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetGameByIdOK() throws Exception {
        for (int i = 0; i < 4; i++) {
            gameService.initGame();
        }

        Game game = gameService.initGame();

        Assertions.assertEquals(5, gameService.getGameCount());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/game/" + game.getId().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        Game gameFromJson = objectMapper.readValue(json, Game.class);

        Assertions.assertEquals(5, gameService.getGameCount());
        Assertions.assertTrue(gameFromJson.getId().toString().equals(game.getId().toString()));
        Assertions.assertTrue(gameFromJson.getColorOrder().size() == game.getColorOrder().size() &&
                gameFromJson.getColorOrder().containsAll(game.getColorOrder()) &&
                game.getColorOrder().containsAll(gameFromJson.getColorOrder()));
    }

    @Test
    public void testGetGameByIdKO() throws Exception {
        for (int i = 0; i < 5; i++) {
            gameService.initGame();
        }

        Assertions.assertEquals(5, gameService.getGameCount());

        mockMvc.perform(MockMvcRequestBuilders.get("/game/" + "badId"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Assertions.assertEquals(5, gameService.getGameCount());
    }

    @Test
    public void testDeleteGameOK() throws Exception {
        for (int i = 0; i < 4; i++) {
            gameService.initGame();
        }

        Game game = gameService.initGame();

        Assertions.assertEquals(5, gameService.getGameCount());

        mockMvc.perform(MockMvcRequestBuilders.delete("/game/" + game.getId().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertEquals(4, gameService.getGameCount());
        Assertions.assertFalse(gameService.getAllGames().contains(game));
    }

    @Test
    public void testDeleteGameKO() throws Exception {
        for (int i = 0; i < 5; i++) {
            gameService.initGame();
        }

        Assertions.assertEquals(5, gameService.getGameCount());

        mockMvc.perform(MockMvcRequestBuilders.delete("/game/" + "badId"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Assertions.assertEquals(5, gameService.getGameCount());
    }

}
