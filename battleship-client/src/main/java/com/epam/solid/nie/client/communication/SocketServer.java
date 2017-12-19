package com.epam.solid.nie.client.communication;

import com.epam.solid.nie.utils.Point2D;
import com.epam.solid.nie.client.ui.Cell;

import java.io.IOException;
import java.util.*;

import static com.epam.solid.nie.client.ui.GameScene.running;


public class SocketServer implements Server {

    private ShipClient server;
    private String allMoves = "";
    private Queue<Cell> cells = new LinkedList<>();//Arrays.asList(new Cell(Point2D.of(0, 0)), new Cell(Point2D.of(9, 9))));

    public SocketServer() {

    }

    @Override
    public boolean canConnect(String ip) {
        return true;
    }

    @Override
    public boolean connect(String ip) {
        server = new SocketClient(ip);
        try {
            return server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void passAllShips(String allShips) {
        System.out.println(allShips);
        try {
            server.send(allShips);
        } catch (IOException e) {
            e.printStackTrace();
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

    private void receiveAllMovesWithoutSending() {
        allMoves = "";
        String moves = server.getEnemyShips();
        String[] movesArr = moves.split(",;");
        for (int i = 0; i < movesArr.length; i++) {
            String[] coordinates = movesArr[i].split(",");
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
        System.out.println(allMoves);
        try {
            server.send(allMoves);
        } catch (IOException e) {
            e.printStackTrace();
        }

        allMoves = "";
        String moves = server.getEnemyShips();
        String[] movesArr = moves.split(",;");
        for (int i = 0; i < movesArr.length; i++) {
            String[] coordinates = movesArr[i].split(",");
            String x = coordinates[0];
            String y = coordinates[1];
            Cell c = new Cell(Point2D.of(Integer.valueOf(x), Integer.valueOf(y)));
            cells.add(c);
        }
    }
}
