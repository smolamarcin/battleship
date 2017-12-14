package com.epam.solid.nie.client.communication;

import com.epam.solid.nie.client.ui.Cell;

import java.util.Random;

public class SocketServer implements Server {
    public SocketServer() {

    }

    @Override
    public boolean canConnect(String ip) {
        return true;
    }

    @Override
    public void passAllShips(String allShips) {
        System.out.println(allShips);
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
        return new Cell(x, y);
    }

    @Override
    public void connect(String ip) {

    }
}
