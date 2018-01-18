package com.academy.solid.nie.client.ui;


import com.academy.solid.nie.client.communication.SocketServer;
import com.academy.solid.nie.client.language.Communicate;
import com.academy.solid.nie.client.language.CommunicateProviderImpl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class represents Game UI.
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
        Button button = new Button();
        button.setText(CommunicateProviderImpl.getCommunicate(Communicate.WRONG_IP));
        button.setOnMouseClicked(event -> button.setText(CommunicateProviderImpl.getCommunicate(Communicate.WELCOME)));
        root.setRight(button);
        enemyBoard = new Board(true);
        enemyBoard.initialize(getMove());
        playerBoard = new Board(false);
        shipPlacer = new ShipPlacer(enemyBoard, playerBoard, socketServer);
        playerBoard.initialize(shipPlacer.setUpPlayerShips());
        VBox vbox = new VBox(DEFAULT_SPACING, enemyBoard.getBoardFX(), playerBoard.getBoardFX());
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
            new WindowDisplayer(CommunicateProviderImpl
                    .getCommunicate(Communicate.WIN))
                    .withButtonWhoExitSystem().display();
            socketServer.sendGameOverToOpponent();
        }
        if (enemyBoard.isShipSunken(cell.getShip())) {
            enemyBoard.markShipAsSunken(cell.getShip());
        }
        socketServer.sendPlayerMove(cell.toString());
    }

    private void handleEnemyMove() {
        playerBoard.makeMoves(socketServer.receiveEnemyMoves());
    }

    void start() {
        start(new Stage());
    }

    @Override
    public void start(final Stage primaryStage) {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}
