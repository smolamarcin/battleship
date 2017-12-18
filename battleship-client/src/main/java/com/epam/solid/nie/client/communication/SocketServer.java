package com.epam.solid.nie.client.communication;

import com.epam.solid.nie.utils.Point2D;
import com.epam.solid.nie.client.ui.Cell;

import java.io.IOException;
import java.util.*;

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
    public void sendPlayerMove(String move) {
        allMoves += move + ";";
    }

    @Override
    public Cell passEnemyMove() {
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
        for (int i = 0; i < movesArr.length - 1; i++) {
            String[] coordinates = movesArr[i].split(",");
            cells.add(new Cell(Point2D.of(Integer.valueOf(coordinates[0]), Integer.valueOf(coordinates[1]))));
        }
    }
}
