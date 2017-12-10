package com.epam.solid.nie.client.ui.tutorial;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Random;

public class BattleshipMain extends Application {

    private boolean running = false;
    private Board enemyBoard, playerBoard;
    private int shipsToplace = 5;
    private boolean enemyTurn = false;
    private Random random=new Random();
    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
