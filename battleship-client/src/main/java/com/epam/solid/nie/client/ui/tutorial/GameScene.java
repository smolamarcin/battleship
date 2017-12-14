package com.epam.solid.nie.client.ui.tutorial;


import com.epam.solid.nie.client.ui.SocketServer;
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

import java.util.Random;


public class GameScene extends Application {

    private boolean running = false;
    private Board enemyBoard, playerBoard;
    private int shipsToPlace = 5;
    //    private boolean enemyTurn = false;
    private Random random = new Random();
    private SocketServer socketServer;

    public GameScene(SocketServer socketServer) {
        this.socketServer = socketServer;
    }

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);
        enemyBoard = new Board(true);
        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));
        enemyBoard.initialize(getMove());
        playerBoard = new Board(false);
        playerBoard.initialize(setUpPlayerShips());
        VBox vbox = new VBox(50, enemyBoard, playerBoard);
        vbox.setAlignment(Pos.CENTER);
        root.setCenter(vbox);
        return root;
    }

    private EventHandler<MouseEvent> getMove() {
        return event -> {
            Cell cell = (Cell) event.getSource();
            if (!running || cell.wasShot)
                return;
            running = cell.shoot();
            if (checkForWin(enemyBoard)) {
                System.out.println("YOU WIN");
                System.exit(0);
            }
            socketServer.sendPlayerMove(cell.toString());
            Cell enemyMove =socketServer.passEnemyMove();
            if (!running) {
                makeEnemyMove(enemyMove);
            }

        };
    }

    private EventHandler<MouseEvent> setUpPlayerShips() {
        return event -> {
            if (running)
                return;
            Cell cell = (Cell) event.getSource();
            Ship ship = new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY);
            if (playerBoard.isShipPositionValid(ship, cell) && --shipsToPlace == 0) {
                socketServer.passAllShips(playerBoard.getAllpositions());
                running = placeShipsRandomly();
            }
        };
    }

    private void makeEnemyMove(Cell cell1) {
        while (!running) {
            int x = cell1.getCellX();
            int y = cell1.getCellY();
            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot){
                cell1 = socketServer.passEnemyMove();
                continue;
            }
            running = !cell.shoot();
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
            Ship ship = new Ship(numberOfShipTypes, Math.random() < 0.5);
            if (enemyBoard.isShipPositionValid(ship, new Cell(x, y))) {
                numberOfShipTypes--;
            }
        }
        return true;
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

