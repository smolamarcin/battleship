package com.epam.solid.nie.client.ui;


import com.epam.solid.nie.client.communication.SocketServer;
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
//todo: write java doc

/**
 * JAVA DOC HERE
 */
class GameScene extends Application {
    //todo: naming!!!!
    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 1000;
    public static final int DEFAULT_SPACING = 50;
    public static final int DEFAULT_SCENE_WIDTH = 200;
    public static final int DEFAULT_SCENE_HEIGHT = 100;
    private boolean whichPlayer;
    static boolean running = false;
    private Board enemyBoard;
    private Board playerBoard;
    private SocketServer socketServer;

    GameScene(final SocketServer socketServer, final boolean whichPlayer) {
        this.socketServer = socketServer;
        this.whichPlayer = whichPlayer;
    }

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        enemyBoard = new Board(true);
        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));
        enemyBoard.initialize(getMove());
        playerBoard = new Board(false);
        ShipPlacer shipPlacer = new ShipPlacer(enemyBoard, playerBoard, socketServer);
        playerBoard.initialize(shipPlacer.setUpPlayerShips());
        VBox vbox = new VBox(DEFAULT_SPACING, enemyBoard, playerBoard);
        vbox.setAlignment(Pos.CENTER);
        root.setCenter(vbox);
        return root;
    }

    private EventHandler<MouseEvent> getMove() {
        return event -> {
            Cell cell = (Cell) event.getSource();
            if (whichPlayer) {
                if (!running || cell.wasShot) {
                    return;
                }
                handlePlayersMove(cell);
            }
            if (!running) {
                handleEnemyMove();
            }
            whichPlayer = true;
        };
    }

    private void handlePlayersMove(final Cell cell) {
        running = cell.shoot();
        if (checkForWin(enemyBoard)) {
            displayYouWinWindow();
            socketServer.sendGameOverToOpponent();
        }
        socketServer.sendPlayerMove(cell.toString());
    }

    private void handleEnemyMove() {
        Cell enemyMove;
        if (!whichPlayer) {
            enemyMove = socketServer.receiveFirstMove();
        } else {
            enemyMove = socketServer.receiveEnemyMove();
        }
        makeEnemyMove(enemyMove);
    }

    private void displayYouWinWindow() {
        StackPane secondaryLayout = new StackPane();
        Button button = new Button();
        button.setText("YOU WIN");
        button.setOnAction(e -> System.exit(0));
        secondaryLayout.getChildren().add(button);
        Scene secondScene = new Scene(secondaryLayout, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.show();
    }

    private void makeEnemyMove(Cell cell) {
        while (!running) {
            int x = cell.getCellX();
            int y = cell.getCellY();
            cell = playerBoard.getCell(x, y);
            if (cell.wasShot) {
                cell = socketServer.receiveEnemyMove();
            } else {
                running = !cell.shoot();
            }
        }
    }

    private boolean checkForWin(final Board board) {
        return board.areAllShipsSunk();
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
