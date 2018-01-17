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
        GameInitializer gameInitializer = new ServerGameInitializer(first, second, ShipSocketServerTest.IP, portNumber);
        try {
            gameInitializer.initializeGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
