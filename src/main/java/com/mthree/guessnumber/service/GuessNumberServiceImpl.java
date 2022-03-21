package com.mthree.guessnumber.service;

import com.mthree.guessnumber.dao.GuessNumberDaoDB;
import com.mthree.guessnumber.dto.Game;
import com.mthree.guessnumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class GuessNumberServiceImpl implements GuessNumberService{

    @Autowired
    GuessNumberDaoDB guessNumberDao;

    @Override
    public int startGame() {
        Game game = new Game();
        game.setStatus(false);
        game.setAnswer(createAnswer());
        guessNumberDao.createGame(game);

        return game.getGameId();
    }

    @Override
    public Game getGameById(int gameId)
    {
        return guessNumberDao.findGameById(gameId);
    }

    @Override
    public String createAnswer() {
        List<Integer> nums = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
        Collections.shuffle(nums);
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < 4; i++)
        {
            answer.append(nums.get(i));
        }
        return answer.toString();
    }

    @Override
    public List<Round> getAllRounds(int gameId) {
        return guessNumberDao.getAllRounds(gameId);
    }

    @Override
    public List<Game> getAllGames() {
        return guessNumberDao.getAllGames();
    }

    @Override
    public Round makeGuess(String guess, int gameId) {
        Game game = guessNumberDao.findGameById(gameId);
        String answer = game.getAnswer();
        String result = calculateGuessResult(guess, answer);
        Round round = new Round();
        round.setGameId(gameId);
        round.setGuess(guess);
        round.setGuessTime(new Timestamp(System.currentTimeMillis()));
        round.setGuessResult(result);
        if(guess.equals(game.getAnswer()))
        {
            game.setStatus(true);
            guessNumberDao.updateGame(game);
        }
        return guessNumberDao.createRound(round);
    }

    @Override
    public String calculateGuessResult(String guess, String answer) {
        int exactMatch = 0;
        int partialMatch = 0;

        char[] guessArr = guess.toCharArray();
        char[] answerArr = answer.toCharArray();

        for(int i = 0; i < answerArr.length; i++)
        {
            if(guess.indexOf(answerArr[i]) >= 0) // search if guess digit is in answer
            {
                if(guessArr[i] == answerArr[i]) // same index
                {
                    exactMatch++;
                }
                else // not same index
                {
                    partialMatch++;
                }
            }
        }
        return "e:" + exactMatch + ":p:" + partialMatch;
    }
}
