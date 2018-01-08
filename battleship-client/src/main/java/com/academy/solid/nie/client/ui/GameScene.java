package com.academy.solid.nie.client.ui;


import com.academy.solid.nie.client.communication.SocketServer;
import com.academy.solid.nie.utils.Point2D;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Queue;


public class GameScene extends Application {
    private boolean isMyTurn = true;
    private Board enemyBoard;
    private Board playerBoard;
    private SocketServer socketServer;
    private ShipPlacer shipPlacer;

    GameScene(SocketServer socketServer) {
        this.socketServer = socketServer;
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
            if (!isMyTurn || !shipPlacer.areAllShipsPlaced() || cell.wasShot)
                return;
            handlePlayersMove(cell);
            if (!isMyTurn) {
                handleEnemyMove();
                isMyTurn = true;
            }
        };
    }

    private void handlePlayersMove(Cell cell) {
        isMyTurn = cell.shoot();
        if (checkForWin(enemyBoard)) {
            displayYouWinWindow();
            socketServer.sendGameOverToOpponent();
        }
        socketServer.sendPlayerMove(cell.toString());
    }

    private void handleEnemyMove() {
        Queue<Point2D> enemyMoves = socketServer.receiveEnemyMoves();
        makeEnemyMove(enemyMoves);
    }

    private void displayYouWinWindow() {
        StackPane secondaryLayout = new StackPane();
        Button button = new Button();
        button.setText("YOU WIN");
        button.setOnAction(e -> System.exit(0));
        secondaryLayout.getChildren().add(button);
        Scene secondScene = new Scene(secondaryLayout, 200, 100);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.show();
    }

    private void makeEnemyMove(Queue<Point2D> points) {
        for (Point2D point : points) {
            int x = point.getX();
            int y = point.getY();
            Cell cellOnBoard = playerBoard.getCell(x, y);
            cellOnBoard.shoot();
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