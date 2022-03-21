package com.mthree.guessnumber.service;

import com.mthree.guessnumber.dao.GuessNumberDaoDB;
import com.mthree.guessnumber.dto.Game;
import com.mthree.guessnumber.dto.Round;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class GuessNumberServiceImplTest {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    GuessNumberService service;

    @Autowired
    GuessNumberDaoDB guessNumberDaoDB;

    @BeforeEach
    void setUp() {
        jdbc.update("DELETE FROM round;");
        jdbc.update("DELETE FROM game;");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void startGame() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(false);
        int gameId = service.startGame();
        Game testGame = service.getGameById(gameId);

        assertNotNull(testGame);
        assertNotNull(testGame.getAnswer());
        assertEquals(false, testGame.isStatus());
        assertEquals(4, testGame.getAnswer().length());
        assertEquals(gameId, testGame.getGameId());

    }

    @Test
    void createAnswer() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(false);
        int gameId = service.startGame();
        Game testGame = service.getGameById(gameId);

        assertNotNull(testGame.getAnswer());
        assertEquals(4, testGame.getAnswer().length());
    }

    @Test
    void makeGuess() {
        Game game = new Game();
        game.setStatus(false);
        game.setAnswer("1234");
        game.setGameId(1);
        Game testGame = guessNumberDaoDB.createGame(game);

        Round round = new Round();
        round.setRoundId(0);
        round.setGameId(1);
        round.setGuess("1234");
        round.setGuessResult("e:4:p:0");

        Round testRound = service.makeGuess("1234", testGame.getGameId());
        assertNotNull(testRound);
        assertEquals(game.getAnswer(), "1234");
        assertEquals(round.getGuessResult(), testRound.getGuessResult());

    }
}
