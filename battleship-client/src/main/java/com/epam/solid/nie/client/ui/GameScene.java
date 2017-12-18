package com.epam.solid.nie.client.ui;


import com.epam.solid.nie.client.communication.SocketServer;
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



/**
 *
 */
public class GameScene extends Application {
    private State state;
    static boolean running = false;
    private Board enemyBoard, playerBoard;
    private SocketServer socketServer;
    private ShipPlacer shipPlacer;

    GameScene(SocketServer socketServer) {
        this.socketServer = socketServer;
    }

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);
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
            if (!running || cell.wasShot)
                return;
            running = cell.shoot();
            if (checkForWin(enemyBoard)) {
                System.out.println("YOU WIN");
                System.exit(0);
            }
            socketServer.sendPlayerMove(cell.toString());
            Cell enemyMove = socketServer.passEnemyMove();
            if (!running) {
                makeEnemyMove(enemyMove);
            }

        };
    }

    private void makeEnemyMove(Cell cell1) {
        while (!running) {
            int x = cell1.getCellX();
            int y = cell1.getCellY();
            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot) {
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