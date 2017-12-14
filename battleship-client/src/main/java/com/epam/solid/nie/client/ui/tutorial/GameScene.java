package com.epam.solid.nie.client.ui.tutorial;


import com.epam.solid.nie.ships.BattleShip;
import com.epam.solid.nie.ships.HorizontalShipFactory;
import com.epam.solid.nie.ships.ShipFactory;
import com.epam.solid.nie.state.State;
import com.epam.solid.nie.utils.Point2D;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GameScene extends Application {
    private State state;
    private boolean running = false;
    private Board enemyBoard, playerBoard;
    private int shipsToPlace = 5;
    private boolean enemyTurn = false;
    private Random random = new Random();
    private ShipFactory shipFactory;

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);
        enemyBoard = new Board(true);
        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));
        enemyBoard.initialize(setUpAIShips());
        playerBoard = new Board(false);
        playerBoard.initialize(setUpPlayerShips());
        VBox vbox = new VBox(50, enemyBoard, playerBoard);
        vbox.setAlignment(Pos.CENTER);
        root.setCenter(vbox);

        return root;
    }

    private BattleShip createShip(List<Point2D> points) {
        ShipFactory shipFactory = new HorizontalShipFactory();
        return shipFactory.createShip(points);
    }

    private EventHandler<MouseEvent> setUpAIShips() {
        return event -> {
            Cell cell = (Cell) event.getSource();
            if (!running || cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (checkForWin(enemyBoard)) {
                System.out.println("YOU WIN");
                System.exit(0);
            }

            if (enemyTurn)
                enemyMove();
        };
    }

    private EventHandler<MouseEvent> setUpPlayerShips() {
        return event -> {
            if (running)
                return;
            Cell cell = (Cell) event.getSource();
            Point2D point2D = new Point2D(cell.getCellX(), cell.getCellY());
            if(event.getButton() == MouseButton.PRIMARY){
                Ship ship = new Ship(createShip(Collections.singletonList(point2D)));
                if (playerBoard.isShipPositionValid(ship, cell) && --shipsToPlace == 0) {
                    running = placeShipsRandomly();
                }
            }

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
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }

    private boolean checkForWin(Board board) {
        return board.ships == 0;
    }

    /**
     * There are 5 types of ships.
     * Method picks random cell to place ship.
     * Ship type is changed in every iteration
     */
    private boolean placeShipsRandomly() {
        int numberOfShipTypes = 5;
        while (numberOfShipTypes > 0) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            Point2D point2D = new Point2D(x, y);
            Ship ship = new Ship(createShip(Collections.singletonList(point2D)));
            if (enemyBoard.isShipPositionValid(ship, new Cell(point2D))) {
                numberOfShipTypes--;
            }
        }
        return true;
    }

    public void start(){
        start(new Stage());
    }
    @Override
    public void start(Stage primaryStage)  {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}

