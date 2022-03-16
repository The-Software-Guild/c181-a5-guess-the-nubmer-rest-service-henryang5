package com.mthree.guessnumber.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

public class Round {
    private int gameId;
    private int roundId;
    private Timestamp guessTime;
    private String guess;
    private String guessResult;

    public Round() {}

    public Round(int gameId, int roundId, Timestamp guessTime, String guess, String guessResult) {
        this.gameId = gameId;
        this.roundId = roundId;
        this.guessTime = guessTime;
        this.guess = guess;
        this.guessResult = guessResult;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public Timestamp getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(Timestamp guessTime) {
        this.guessTime = guessTime;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getGuessResult() {
        return guessResult;
    }

    public void setGuessResult(String guessResult) {
        this.guessResult = guessResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return gameId == round.gameId && roundId == round.roundId && guessTime.equals(round.guessTime) && guess.equals(round.guess) && guessResult.equals(round.guessResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, roundId, guessTime, guess, guessResult);
    }

    @Override
    public String toString() {
        return "Round{" +
                "gameId=" + gameId +
                ", roundId=" + roundId +
                ", guessTime=" + guessTime +
                ", guess='" + guess + '\'' +
                ", guessResult='" + guessResult + '\'' +
                '}';
    }
}
