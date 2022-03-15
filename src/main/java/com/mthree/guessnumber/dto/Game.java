package com.mthree.guessnumber.dto;

import java.util.Objects;

public class Game {
    private int gameId;
    private String answer;
    private boolean status;

    public Game(){}

    public Game(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameId == game.gameId && status == game.status && answer.equals(game.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, answer, status);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", answer='" + answer + '\'' +
                ", status=" + status +
                '}';
    }
}
