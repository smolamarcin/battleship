package com.academy.solid.nie.client.ui;


import com.academy.solid.nie.client.communication.SocketServer;
import com.academy.solid.nie.utils.Point2D;
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

import java.util.Queue;

/**
 *  Class represents Game UI.
 */
class GameScene extends Application {
    private static final int DEFAULT_ROOT_WIDTH = 500;
    private static final int DEFAULT_ROOT_HEIGHT = 1000;
    private static final int DEFAULT_SPACING = 50;
    private boolean isMyTurn = true;
    private Board enemyBoard;
    private Board playerBoard;
    private SocketServer socketServer;
    private ShipPlacer shipPlacer;

    GameScene(final SocketServer socketServer) {
        this.socketServer = socketServer;
    }

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(DEFAULT_ROOT_WIDTH, DEFAULT_ROOT_HEIGHT);
        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));
        enemyBoard = new Board(true);
        enemyBoard.initialize(getMove());
        playerBoard = new Board(false);
        shipPlacer = new ShipPlacer(enemyBoard, playerBoard, socketServer);
        playerBoard.initialize(shipPlacer.setUpPlayerShips());
        VBox vbox = new VBox(DEFAULT_SPACING, enemyBoard, playerBoard);
        vbox.setAlignment(Pos.CENTER);
        root.setCenter(vbox);
        return root;
    }

    private EventHandler<MouseEvent> getMove() {
        return event -> {
            Cell cell = (Cell) event.getSource();
            if (!isMyTurn || !shipPlacer.areAllShipsPlaced() || cell.wasShot()) {
                return;
            }
            handlePlayersMove(cell);
            if (!isMyTurn) {
                handleEnemyMove();
                isMyTurn = true;
            }
        };
    }

    private void handlePlayersMove(final Cell cell) {
        isMyTurn = cell.shoot();
        if (enemyBoard.areAllShipsSunk()) {
            new WindowDisplayer("YOU WIN").withButtonWhoExitSystem().display();
            socketServer.sendGameOverToOpponent();
        }
        socketServer.sendPlayerMove(cell.toString());
    }

    private void handleEnemyMove() {
        Queue<Point2D> enemyMoves = socketServer.receiveEnemyMoves();
        makeEnemyMove(enemyMoves);
    }

    private void makeEnemyMove(final Queue<Point2D> points) {
        for (Point2D point : points) {
            int x = point.getX();
            int y = point.getY();
            Cell cellOnBoard = playerBoard.getCell(x, y);
            cellOnBoard.shoot();
        }
    }

    void start() {
        start(new Stage());
    }

    @Override
    public void start(final Stage primaryStage) {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
