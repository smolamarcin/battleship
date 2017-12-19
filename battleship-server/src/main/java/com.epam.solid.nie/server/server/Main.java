package com.epam.solid.nie.server.server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ShipServer shipServer = new ShipSocketServer(args.length > 0 ? args[0] : "");
        shipServer.initialize();
        while (!shipServer.isGameOver())
            shipServer.play();
        System.out.println("Game over");
    }
}
