package com.mthree.guessnumber.dao;

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

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class GuessNumberDaoDBTest {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    GuessNumberDaoDB guessNumberDaoDB;

    @BeforeEach
    void setUp() {
        jdbc.update("DELETE FROM round;");
        jdbc.update("DELETE FROM game;");
    }

    @AfterEach
    void tearDown() {}

    @Test
    void createGame() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(false);
        Game testGame = guessNumberDaoDB.createGame(game);
        Game daoGame = guessNumberDaoDB.findGameById(testGame.getGameId());
        assertEquals(testGame, daoGame);
    }

    @Test
    void getAllGames() {
        Game game1 = new Game();
        game1.setAnswer("1234");
        game1.setStatus(false);
        Game testGame1 = guessNumberDaoDB.createGame(game1);

        Game game2 = new Game();
        game2.setAnswer("4321");
        game2.setStatus(false);
        Game testGame2 = guessNumberDaoDB.createGame(game2);

        List<Game> games = guessNumberDaoDB.getAllGames();
        assertEquals(2, games.size());
        assertTrue(games.contains(game1));
        assertTrue(games.contains(game2));
    }

    @Test
    void findGameById() {
        Game game1 = new Game();
        game1.setAnswer("1234");
        game1.setStatus(false);
        Game testGame1 = guessNumberDaoDB.createGame(game1);

        Game game2 = new Game();
        game2.setAnswer("4321");
        game2.setStatus(false);
        Game testGame2 = guessNumberDaoDB.createGame(game2);

        assertEquals(testGame1, guessNumberDaoDB.findGameById(testGame1.getGameId()));
        assertEquals(testGame2, guessNumberDaoDB.findGameById(testGame2.getGameId()));
    }

    @Test
    void createRound() {
        Game game1 = new Game();
        game1.setAnswer("1234");
        game1.setStatus(false);
        Game testGame1 = guessNumberDaoDB.createGame(game1);

        Round round = new Round();
        round.setGuessTime(Timestamp.valueOf("2022-03-19 01:22:43"));
        round.setRoundId(1);
        round.setGameId(testGame1.getGameId());
        round.setGuess("4321");
        round.setGuessResult("e");
        Round testRound = guessNumberDaoDB.createRound(round);
        Round daoRound = guessNumberDaoDB.getRoundById(testRound.getRoundId());

        assertEquals(testRound, daoRound);
    }
    @Test
    void getAllRounds() {
        Game game1 = new Game();
        game1.setAnswer("1234");
        game1.setStatus(false);
        Game testGame1 = guessNumberDaoDB.createGame(game1);

        Round round = new Round();
        round.setGuessTime(Timestamp.valueOf("2022-03-19 01:22:43"));
        round.setGameId(testGame1.getGameId());
        round.setGuess("4321");
        round.setGuessResult("e");
        Round testRound = guessNumberDaoDB.createRound(round);
        Round daoRound = guessNumberDaoDB.getRoundById(testRound.getRoundId());


        Round round2 = new Round();
        round2.setGuessTime(Timestamp.valueOf("2022-03-19 01:22:43"));
        round2.setGameId(testGame1.getGameId());
        round2.setGuess("1235");
        round2.setGuessResult("e333");
        Round testRound2 = guessNumberDaoDB.createRound(round2);
        Round daoRound2 = guessNumberDaoDB.getRoundById(testRound2.getRoundId());


        List<Round> roundList = guessNumberDaoDB.getAllRounds(testGame1.getGameId());
        assertEquals(2, roundList.size());
        assertTrue(roundList.contains(round));
        assertTrue(roundList.contains(round2));
    }

    @Test
    void getRoundById() {
        Game game1 = new Game();
        game1.setAnswer("1234");
        game1.setStatus(false);
        Game testGame1 = guessNumberDaoDB.createGame(game1);

        Round round = new Round();
        round.setGuessTime(Timestamp.valueOf("2022-03-19 01:22:43"));
        round.setGameId(testGame1.getGameId());
        round.setGuess("4321");
        round.setGuessResult("e");
        Round testRound = guessNumberDaoDB.createRound(round);
        Round daoRound = guessNumberDaoDB.getRoundById(testRound.getRoundId());


        Round round2 = new Round();
        round2.setGuessTime(Timestamp.valueOf("2022-03-19 01:22:43"));
        round2.setGameId(testGame1.getGameId());
        round2.setGuess("1235");
        round2.setGuessResult("e333");
        Round testRound2 = guessNumberDaoDB.createRound(round2);
        Round daoRound2 = guessNumberDaoDB.getRoundById(testRound2.getRoundId());

        assertEquals(testRound, daoRound);
        assertEquals(testRound2, daoRound2);
    }

}