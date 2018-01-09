package com.academy.solid.nie.server;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Main class for the game.
 */
public final class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private Main() {
    }

    /**
     * Creates server here.
     *
     * @param args represents input
     * @throws IOException when something happens with the Server.
     */
    public static void main(final String[] args) throws IOException {
        String arg = args[0];
        String emptyString = "";
        ShipServer shipServer;
        Player first = new NetPlayer();
        Player second = new NetPlayer();
        if (args.length > 0) {
            shipServer = new ShipSocketServer(first, second, arg);
        } else {
            shipServer = new ShipSocketServer(first, second, emptyString);
        }
        shipServer.initialize();
        while (!shipServer.isGameOver()) {
            shipServer.play();
        }
        LOGGER.info("Game over");
    }
}
