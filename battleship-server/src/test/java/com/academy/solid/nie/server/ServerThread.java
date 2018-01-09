package com.academy.solid.nie.server;

import java.io.IOException;

public class ServerThread extends Thread {
    private Player first;
    private Player second;

    ServerThread(Player first, Player second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {
        ShipServer shipServer = new ShipSocketServer(first, second, ShipSocketServerTest.IP);
        try {
            shipServer.initializeGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
