package com.epam.solid.nie.client.communication;

import com.epam.solid.nie.utils.Point2D;
import com.epam.solid.nie.client.ui.Cell;

import java.io.IOException;
import java.util.*;


public class SocketServer implements Server {
    private ShipClient server;
    private String allMoves = "";
    private Queue<Cell> cells = new LinkedList<>();

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
    public void send(String allShips) {
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
        send(allMoves);
        receiveAllMovesWithoutSending();
    }
}
