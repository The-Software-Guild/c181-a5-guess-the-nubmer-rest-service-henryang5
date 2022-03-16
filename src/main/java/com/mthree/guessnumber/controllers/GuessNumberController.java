package com.mthree.guessnumber.controllers;


import com.mthree.guessnumber.dto.Game;
import com.mthree.guessnumber.dto.Round;
import com.mthree.guessnumber.service.GuessNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guessnumber")
public class GuessNumberController {

    private final GuessNumberService service;

    public GuessNumberController(GuessNumberService service) {
        this.service = service;
    }

    //"begin" - POST – Starts a game, generates an answer, and sets the correct status. Should return a 201 CREATED message as well as the created gameId.
    @PostMapping("/begin")
    public ResponseEntity<Integer> beginGame(){
        return new ResponseEntity(service.startGame(), HttpStatus.CREATED);
    }

    // "guess" – POST – Makes a guess by passing the guess and gameId in as JSON. The program must calculate the results of the guess and mark the game finished if the guess is correct. It returns the Round object with the results filled in.
    @PostMapping("/guess")
    public Round guess(@RequestBody Round round) {
        return service.makeGuess(round.getGuess(), round.getGameId());
    }

    // "game" – GET – Returns a list of all games. Be sure in-progress games do not display their answer.
    @GetMapping("/game")
    public List<Game> getAllGames() {
        List<Game> gameList = service.getAllGames();
        for(Game game: gameList)
        {
            if(!game.isStatus())
            {
                game.setAnswer("****");
            }
        }
        return gameList;
    }

    // "game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-progress games do not display their answer.
    @GetMapping("/game/{game_id}")
    public Game getGame(@PathVariable("game_id") int id) {
        Game game = service.getGameById(id);
        if(!game.isStatus())
        {
            game.setAnswer("****");
        }
        return game;
    }

    // "rounds/{gameId} – GET – Returns a list of rounds for the specified game sorted by time.
    @GetMapping("rounds/{game_id}")
    public List<Round> getRounds(@PathVariable("game_id") int id) {
        return service.getAllRounds(id);
    }
}
