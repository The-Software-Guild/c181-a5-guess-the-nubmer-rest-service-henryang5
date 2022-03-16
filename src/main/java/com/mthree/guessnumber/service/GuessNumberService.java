package com.mthree.guessnumber.service;

import com.mthree.guessnumber.dto.Game;
import com.mthree.guessnumber.dto.Round;

import java.util.List;

public interface GuessNumberService {

    int startGame(); // returns game id
    String createAnswer();
    Game getGameById(int gameId);
    List<Round> getAllRounds(int gameId);
    List<Game> getAllGames();
    Round makeGuess(String guess, int gameId);
    String calculateGuessResult(String guess, String answer);
}
