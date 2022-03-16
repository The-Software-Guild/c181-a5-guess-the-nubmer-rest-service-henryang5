package com.mthree.guessnumber.dao;

import com.mthree.guessnumber.dto.Game;
import com.mthree.guessnumber.dto.Round;

import java.util.List;

public interface GuessNumberDao {

    Game createGame(Game game);
    List<Game> getAllGames();
    Game findGameById(int id);
    void updateGame(Game game);
    void deleteGameById(int id);

    Round createRound(Round round);
    List<Round> getAllRounds(int gameId);
    Round getRoundById(int id);
    void updateRound(Round round);
    void deleteRoundById(int id);

}
