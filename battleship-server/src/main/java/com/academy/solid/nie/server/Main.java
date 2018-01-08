package com.academy.solid.nie.server;

import java.io.IOException;
import java.util.logging.Logger;

public final class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private Main() {
    }

    public static void main(final String[] args) throws IOException {
        String arg = args[0];
        String emptyString = "";
        ShipServer shipServer;
        if (args.length > 0) {
            shipServer = new ShipSocketServer(arg);
        } else {
            shipServer = new ShipSocketServer(emptyString);
        }
        shipServer.initialize();
        while (!shipServer.isGameOver()) {
            shipServer.play();
        }
        LOGGER.info("Game over");
    }
}
