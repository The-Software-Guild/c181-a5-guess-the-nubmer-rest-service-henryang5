package com.mthree.guessnumber.dao;

import com.mthree.guessnumber.dto.Game;

public interface GuessNumberDao {

    Game createGame(Game game);
}
