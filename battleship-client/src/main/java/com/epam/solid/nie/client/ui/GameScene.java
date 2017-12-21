package com.epam.solid.nie.client.ui;


import com.epam.solid.nie.client.communication.SocketServer;
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

import java.util.logging.Logger;


public class GameScene extends Application {
    private Logger logger = Logger.getLogger(GameScene.class.getName());
    private boolean whichPlayer;
    static boolean running = false;
    private Board enemyBoard, playerBoard;
    private SocketServer socketServer;
    private ShipPlacer shipPlacer;

    GameScene(SocketServer socketServer, boolean whichPlayer) {
        this.socketServer = socketServer;
        this.whichPlayer = whichPlayer;
    }

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(500, 1000);
        enemyBoard = new Board(true);
        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));
        enemyBoard.initialize(getMove());
        playerBoard = new Board(false);
        shipPlacer = new ShipPlacer(enemyBoard, playerBoard, socketServer);
        playerBoard.initialize(shipPlacer.setUpPlayerShips());
        VBox vbox = new VBox(50, enemyBoard, playerBoard);
        vbox.setAlignment(Pos.CENTER);
        root.setCenter(vbox);
        return root;
    }

    private EventHandler<MouseEvent> getMove() {
        return event -> {
            Cell cell = (Cell) event.getSource();
            if (whichPlayer) {
                if (!running || cell.wasShot)
                    return;
                running = cell.shoot();
                if (checkForWin(enemyBoard)) {
                    logger.info("YOU WIN");
                    System.exit(0);
                }
                socketServer.sendPlayerMove(cell.toString());
            }
            Cell enemyMove;
            if (!running) {
                if (!whichPlayer)
                    enemyMove = socketServer.receiveFirstMove();
                else
                    enemyMove = socketServer.receiveEnemyMove();
                makeEnemyMove(enemyMove);
            }
            whichPlayer = true;

        };
    }

    private void makeEnemyMove(Cell cell1) {
        while (!running) {
            int x = cell1.getCellX();
            int y = cell1.getCellY();
            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot) {
                cell1 = socketServer.receiveEnemyMove();
            } else {
                running = !cell.shoot();
                if (checkForWin(playerBoard)) {
                    logger.info("YOU LOSE");
                    System.exit(0);
                }
            }
        }
    }

    private boolean checkForWin(Board board) {
        return board.areAllShipsSunk();
    }

    void start() {
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