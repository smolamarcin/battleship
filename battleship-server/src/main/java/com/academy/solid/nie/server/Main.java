package com.academy.solid.nie.server;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        Player first = new NetPlayer();
        Player second = new NetPlayer();
        ShipServer shipServer = new ShipSocketServer(first, second, args.length > 0 ? args[0] : "");
        shipServer.initialize();
        while (!shipServer.isGameOver())
            shipServer.play();
        LOGGER.info("Game over");
    }
}
