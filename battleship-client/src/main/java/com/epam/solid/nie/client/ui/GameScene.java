package com.epam.solid.nie.client.ui;


import com.epam.solid.nie.client.communication.SocketServer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameScene extends Application {
    private boolean whichPlayer;
    public static boolean running = false;
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
                handlePlayersMove(cell);
            }
            if (!running)
                handleEnemyMove();
            whichPlayer = true;
        };
    }

    private void handlePlayersMove(Cell cell) {
        running = cell.shoot();
        if (checkForWin(enemyBoard)) {
            displayYouWinWindow();
            socketServer.sendGameOverToOpponent();
        }
        socketServer.sendPlayerMove(cell.toString());
    }

    private void handleEnemyMove() {
        Cell enemyMove;
        if (!whichPlayer)
            enemyMove = socketServer.receiveFirstMove();
        else
            enemyMove = socketServer.receiveEnemyMove();
        makeEnemyMove(enemyMove);
    }

    private void displayYouWinWindow() {
        StackPane secondaryLayout = new StackPane();
        Button button=new Button();
        button.setText("YOU WIN");
        button.setOnAction(e -> System.exit(0));
        secondaryLayout.getChildren().add(button);
        Scene secondScene = new Scene(secondaryLayout,200,100);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.show();
    }

    private void makeEnemyMove(Cell cell) {
        while (!running) {
            int x = cell.getCellX();
            int y = cell.getCellY();
            cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                cell = socketServer.receiveEnemyMove();
            else
                running = !cell.shoot();
        }
    }

    private boolean checkForWin(Board board) {
        return board.areAllShipsSunk();
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