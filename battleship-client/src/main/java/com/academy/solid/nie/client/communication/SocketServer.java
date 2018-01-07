package com.academy.solid.nie.client.communication;

import com.academy.solid.nie.client.ui.Cell;
import com.academy.solid.nie.utils.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;


/**
 * SocketServer implementation to communicate with the server.
 *
 *
 * @since 1.0.1
 */
public class SocketServer implements Server {
    private static final Logger LOGGER = Logger.getLogger(SocketServer.class.getName());
    private ShipClient server;
    private String allMoves = "";

    @Override
    public boolean connect(String ip) {
        server = new SocketClient(ip);
        try {
            return server.run();
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
        return false;
    }

    @Override
    public void send(String allShips) {
        LOGGER.info(allShips);
        try {
            server.send(allShips);
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public String receiveAllShips() {
        return server.getEnemyShips();
    }

    @Override
    public void sendGameOverToOpponent() {
        server.sendGameOverToOpponent();
    }

    private Queue<Cell> receiveAllMovesWithoutSending() {
        allMoves = "";
        Queue<Cell> cells = new LinkedList<>();
        String moves = server.getEnemyShips();
        if (moves.equals("Q")) {
            StackPane secondaryLayout = new StackPane();
            Button button = new Button();
            button.setText("YOU LOSE");
            button.setOnAction(e -> System.exit(0));
            secondaryLayout.getChildren().add(button);
            Scene secondScene = new Scene(secondaryLayout, 200, 100);
            Stage secondStage = new Stage();
            secondStage.setScene(secondScene);
            secondStage.show();
        }
        String[] movesArr = moves.split(",;");
        for (String aMovesArr : movesArr) {
            String[] coordinates = aMovesArr.split(",");
            cells.add(new Cell(Point2D.of(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]))));
        }
        return cells;
    }

    @Override
    public void sendPlayerMove(String move) {
        allMoves += move + ";";
    }

    @Override
    public Queue<Cell> receiveEnemyMoves() {
        send(allMoves);
        return receiveAllMovesWithoutSending();
    }
}
