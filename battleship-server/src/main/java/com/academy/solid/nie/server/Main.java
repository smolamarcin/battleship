package com.academy.solid.nie.server;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        ShipServer shipServer = new ShipSocketServer(args.length > 0 ? args[0] : "");
        shipServer.initialize();
        while (!shipServer.isGameOver())
            shipServer.play();
        LOGGER.info("Game over");
    }
}
