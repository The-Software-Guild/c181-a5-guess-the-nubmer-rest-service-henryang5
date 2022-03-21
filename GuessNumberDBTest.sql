DROP DATABASE IF EXISTS GuessTheNumberDBTest;
CREATE DATABASE GuessTheNumberDBTest;
USE GuessTheNumberDBTest;

CREATE TABLE GAME(
    game_id INT AUTO_INCREMENT,
    answer VARCHAR(4) NOT NULL,
    status VARCHAR(10) NOT NULL,
    CONSTRAINT pk_game_id
        PRIMARY KEY (game_id)
);


CREATE TABLE ROUND(
    round_id INT AUTO_INCREMENT,
    game_id INT NOT NULL,
    guess_time DATETIME,
    guess VARCHAR(4) NOT NULL,
    result VARCHAR(10),
    CONSTRAINT pk_round_id
        PRIMARY KEY (round_id),
	CONSTRAINT fk_game_id
    	FOREIGN KEY (game_id)
    	REFERENCES game(game_id)
);
