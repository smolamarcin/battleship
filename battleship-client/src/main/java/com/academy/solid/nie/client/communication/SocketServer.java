package com.academy.solid.nie.client.communication;

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
 * @since 1.0.1
 */
public final class SocketServer implements Server {
    private static final Logger LOGGER = Logger.getLogger(SocketServer.class.getName());
    private static final int DEFAULT_WIDTH = 200;
    private static final int DEFAULT_HEIGHT = 100;
    private ShipClient server;
    private String allMoves = "";

    @Override
    public void connect(final String ip) {
        server = new SocketClient(ip);
        try {
            server.run();
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void send(final String allShips) {
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

    private Queue<Point2D> receiveAllMovesWithoutSending() {
        allMoves = "";
        Queue<Point2D> cells = new LinkedList<>();
        String moves = server.getEnemyShips();
        if (moves.equals("Q")) {
            StackPane secondaryLayout = new StackPane();
            Button button = new Button();
            button.setText("YOU LOSE");
            button.setOnAction(e -> System.exit(0));
            secondaryLayout.getChildren().add(button);
            Scene secondScene = new Scene(secondaryLayout, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            Stage secondStage = new Stage();
            secondStage.setScene(secondScene);
            secondStage.show();
        }
        String[] movesArr = moves.split(",;");
        for (String aMovesArr : movesArr) {
            String[] coordinates = aMovesArr.split(",");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            cells.add(Point2D.of(x, y));
        }
        return cells;
    }

    @Override
    public void sendPlayerMove(final String move) {
        allMoves += move + ";";
    }

    @Override
    public Queue<Point2D> receiveEnemyMoves() {
        send(allMoves);
        return receiveAllMovesWithoutSending();
    }
}
