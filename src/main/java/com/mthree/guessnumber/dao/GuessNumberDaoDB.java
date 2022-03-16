package com.mthree.guessnumber.dao;

import com.mthree.guessnumber.dto.Game;
import com.mthree.guessnumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Repository
@Profile("database")
public class GuessNumberDaoDB implements GuessNumberDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuessNumberDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game createGame(Game game) {
        final String sql = "INSERT INTO game(answer, status) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            statement.setString(2, String.valueOf(game.isStatus()));
            return statement;

        }, keyHolder);

        game.setGameId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT * FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game findGameById(int id) {
        final String sql = "SELECT * FROM game WHERE game_id= ?;";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    public void updateGame(Game game) {
        final String sql = "UPDATE game SET "
                + "game_id = ?,"
                + "answer = ?, "
                + "status = ?;";

        jdbcTemplate.update(sql,
                game.getGameId(),
                game.getAnswer(),
                game.isStatus());

    }

    @Override
    public void deleteGameById(int id) {
        final String sql = "DELETE FROM game WHERE id = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Round createRound(Round round) {
        final String sql = "INSERT INTO round(game_id, guess, guess_time, result) VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getGameId());
            statement.setString(2, round.getGuess());
            statement.setString(3, round.getGuessTime().toString());
            statement.setString(4, round.getGuessResult());
            return statement;

        }, keyHolder);

        round.setRoundId(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    public List<Round> getAllRounds(int gameId) {
        final String sql = "SELECT * FROM round WHERE game_id=? ORDER BY guess_time;";
        return jdbcTemplate.query(sql, new RoundMapper(), gameId);
    }

    @Override
    public Round getRoundById(int id) {
        final String sql = "SELECT * FROM round WHERE round_id= ?;";
        return jdbcTemplate.queryForObject(sql, new RoundMapper(), id);
    }

    @Override
    public void updateRound(Round round) {
        final String sql = "UPDATE round SET "
                + "game_id = ?,"
                + "guess = ?, "
                + "guess_time = ?"
                + "result = ?;";

        jdbcTemplate.update(sql,
                round.getGameId(),
                round.getGuess(),
                round.getGuessTime(),
                round.getGuessResult());
    }

    @Override
    public void deleteRoundById(int id) {
        final String sql = "DELETE FROM round WHERE round_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("game_id"));
            game.setAnswer(rs.getString("answer"));
            game.setStatus(rs.getBoolean("status"));
            return game;
        }
    }
    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("round_id"));
            round.setGameId(rs.getInt("game_id"));
            round.setGuess(rs.getString("guess"));
            round.setGuessTime(rs.getTimestamp("guess_time"));
            round.setGuessResult(rs.getString("result"));
            return round;
        }
    }
}
