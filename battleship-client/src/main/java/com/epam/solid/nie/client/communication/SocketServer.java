package com.epam.solid.nie.client.communication;

import com.epam.solid.nie.client.ui.Cell;
import com.epam.solid.nie.utils.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;


/**
 * SocketServer implementation to communicate with server side
 */
public class SocketServer implements Server {
    private static Logger logger = Logger.getLogger(SocketServer.class.getName());
    private ShipClient server;
    private String allMoves = "";
    private Queue<Cell> cells = new LinkedList<>();

    @Override
    public boolean connect(String ip) {
        server = new SocketClient(ip);
        try {
            return server.run();
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
        return false;
    }

    @Override
    public void send(String allShips) {
        logger.info(allShips);
        try {
            server.send(allShips);
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    @Override
    public String receiveAllShips() {
        return server.getEnemyShips();
    }

    @Override
    public Cell receiveFirstMove() {
        if (cells.isEmpty())
            receiveAllMovesWithoutSending();
        return cells.poll();
    }

    @Override
    public void sendGameOverToOpponent() {
        server.sendGameOverToOpponent();
    }

    private void receiveAllMovesWithoutSending() {
        allMoves = "";
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
            cells.add(new Cell(Point2D.of(Integer.valueOf(coordinates[0]), Integer.valueOf(coordinates[1]))));
        }
    }

    @Override
    public void sendPlayerMove(String move) {
        allMoves += move + ";";
    }

    @Override
    public Cell receiveEnemyMove() {
        if (cells.isEmpty())
            receiveAllMoves();
        return cells.poll();
    }

    private void receiveAllMoves() {
        send(allMoves);
        receiveAllMovesWithoutSending();
    }
}
