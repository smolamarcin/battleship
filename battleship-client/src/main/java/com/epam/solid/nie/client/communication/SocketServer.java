package com.epam.solid.nie.client.communication;

import com.epam.solid.nie.utils.Point2D;
import com.epam.solid.nie.client.ui.Cell;

import java.io.IOException;
import java.util.Random;

public class SocketServer implements Server {

    private ShipClient server;

    public SocketServer() {

    }

    @Override
    public boolean canConnect(String ip) {
        return true;
    }

    @Override
    public void connect(String ip) {
        server = new SocketClient(ip);
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void sendPlayerMove(String move) {
        System.out.println(move);
    }

    @Override
    public Cell passEnemyMove() {
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        return new Cell(Point2D.of(x,y));
    }

    @Override
    public String receiveAllShips() {
        return server.getEnemyShips();
    }
}
