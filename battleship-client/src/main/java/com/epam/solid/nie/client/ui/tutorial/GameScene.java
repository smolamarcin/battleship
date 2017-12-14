package com.epam.solid.nie.client.ui.tutorial;


import com.epam.solid.nie.state.State;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

import static java.lang.System.out;


public class GameScene extends Application {
    private State state;
    private boolean running = false;
    private Board enemyBoard, playerBoard;
    private boolean enemyTurn = false;
    private Random random = new Random();

    private ShipPlacer shipPlacer;

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);
        enemyBoard = new Board(true);
        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));
        enemyBoard.initialize(setUpAIShips());
        playerBoard = new Board(false);
        shipPlacer = new ShipPlacer(enemyBoard, playerBoard);
        playerBoard.initialize(shipPlacer.setUpPlayerShips());
        VBox vbox = new VBox(50, enemyBoard, playerBoard);
        vbox.setAlignment(Pos.CENTER);
        root.setCenter(vbox);
        return root;
    }


    private EventHandler<MouseEvent> setUpAIShips() {
        return event -> {
            Cell cell = (Cell) event.getSource();
            if (!running || cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (checkForWin(enemyBoard)) {
                out.println("YOU WIN");
                System.exit(0);
            }

            if (enemyTurn)
                enemyMove();
        };
    }

    private void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;
            enemyTurn = cell.shoot();
            if (checkForWin(playerBoard)) {
                out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }

    private boolean checkForWin(Board board) {
        return board.ships == 0;
    }

    public void start() {
        start(new Stage());
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}