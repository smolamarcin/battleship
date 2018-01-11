package com.academy.solid.nie.server;

import java.io.IOException;

public class ServerThread extends Thread {
    private Player first;
    private Player second;
    private int portNumber;

    ServerThread(Player first, Player second, int portNumber) {
        this.first = first;
        this.second = second;
        this.portNumber = portNumber;
    }

    @Override
    public void run() {
        ShipServer shipServer = new ShipSocketServer(first, second, ShipSocketServerTest.IP, portNumber);
        try {
            shipServer.initializeGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
