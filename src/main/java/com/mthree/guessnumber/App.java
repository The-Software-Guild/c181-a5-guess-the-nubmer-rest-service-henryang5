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

    public static void main(String args[]) {
        SpringApplication.run(App.class, args);
    }

}
