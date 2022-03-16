package com.mthree.guessnumber;

import com.mthree.guessnumber.controllers.GuessNumberController;
import com.mthree.guessnumber.dto.Game;
import com.mthree.guessnumber.dto.Round;
import com.mthree.guessnumber.service.GuessNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class App {

    @Autowired
    private JdbcTemplate jdbc;

   // private static Scanner sc;

    public static void main(String args[]) {
        SpringApplication.run(App.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        sc = new Scanner(System.in);
//
//        do {
//            System.out.println("Bulls and Cows Guessing Game");
//            System.out.println("1. Start a Game");
//            System.out.println("2. List ALl Games");
//            System.out.println("3. Get Game By Id");
//            System.out.println("4. Get Rounds By Game Id");
//            System.out.println("5. Exit");
//
//            System.out.println("Enter an option:");
//            String option = sc.nextLine();
//            try {
//                switch (option) {
//                    case "1":
//                        startGame();
//                        break;
//                    case "2":
//                        listAllGames();
//                        break;
//                    case "3":
//                        getGameById();
//                        break;
//                    case "4":
//                        getRoundById();
//                        break;
//                    case "5":
//                        System.out.println("Goodbye");
//                        System.exit(0);
//                    default:
//                        System.out.println("Please enter a valid option");
//                }
//            } catch (Exception ex) {
//                System.out.println("Error communicating with database");
//                System.out.println(ex.getMessage());
//                System.exit(0);
//            }
//
//        } while (true);
//    }
//
//    private void startGame() {
//        int gameId = service.startGame();
//        System.out.println("Enter an guess:");
//        String guess = sc.nextLine();
//    }
//
//    private void getGameById() {
//        System.out.println("Enter an game id:");
//        String gameID = sc.nextLine();
//        System.out.println("=== Game in Game Id " + gameID + " ===");
//        List<Game> gameList = jdbc.query("SELECT * FROM game WHERE game_id = ?", new GameMapper(), gameID);
//        System.out.println("=== Listing All Games ===");
//
//        for (Game game : gameList) {
//            boolean status = game.isStatus();
//            String answer = status ? game.getAnswer() : "****";  // status true print answer else print ****
//                    System.out.printf("%s, %s, %s\n",
//                    game.getGameId(),
//                    answer,
//                    status);
//        }
//        System.out.println("");
//    }
//
//    private void getRoundById() {
//        System.out.println("Enter an game id:");
//        String gameID = sc.nextLine();
//        System.out.println("=== Listing Rounds in Game Id " + gameID + " ===");
//        List<Round> roundList = jdbc.query("SELECT * FROM round WHERE game_id = ?", new RoundMapper(), gameID);
//        for (Round round : roundList) {
//            System.out.printf("%s, %s, %s, %s, %s\n",
//                    round.getGameId(),
//                    round.getRoundId(),
//                    round.getGuessTime(),
//                    round.getGuess(),
//                    round.getGuessResult());
//        }
//        System.out.println("");
//    }
//
//    private void listAllGames() throws SQLException {
//        List<Game> gameList = jdbc.query("SELECT * FROM game", new GameMapper());
//        System.out.println("=== Listing All Games ===");
//        for (Game game : gameList) {
//            boolean status = game.isStatus();
//            String answer = status ? game.getAnswer() : "****"; // status true print answer else print ****
//            System.out.printf("%s, %s, %s\n",
//                    game.getGameId(),
//                    answer,
//                    status);
//        }
//        System.out.println("");
//    }
//
//    private static final class GameMapper implements RowMapper<Game> {
//
//        @Override
//        public Game mapRow(ResultSet rs, int index) throws SQLException {
//            Game game = new Game();
//            game.setGameId(rs.getInt("game_id"));
//            game.setAnswer(rs.getString("answer"));
//            game.setStatus(rs.getBoolean("status"));
//            return game;
//        }
//    }
//    private static final class RoundMapper implements RowMapper<Round> {
//
//        @Override
//        public Round mapRow(ResultSet rs, int index) throws SQLException {
//            Round round = new Round();
//            round.setRoundId(rs.getInt("round_id"));
//            round.setGameId(rs.getInt("game_id"));
//            round.setGuess(rs.getString("guess"));
//            round.setGuessTime(rs.getTimestamp("guess_time"));
//            round.setGuessResult(rs.getString("result"));
//            return round;
//        }
//    }
}
