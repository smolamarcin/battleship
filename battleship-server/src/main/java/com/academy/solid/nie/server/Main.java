package com.academy.solid.nie.server;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Main class for the game.
 */
public final class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final int PORT_NUMBER = 8081;

    private Main() {
    }

    /**
     * Creates server here.
     *
     * @param args represents input
     * @throws IOException when something happens with the Server.
     */
    public static void main(final String[] args) throws IOException {
        String arg;
        if (args.length == 0) {
            arg = "";
        } else {
            arg = args[0];
        }
        GameInitializer gameInitializer;
        Player first = new NetPlayer();
        Player second = new NetPlayer();
        gameInitializer = new ServerGameInitializer(first, second, arg, PORT_NUMBER);
        gameInitializer.initializeGame();
        Game game = new Game(first, second);
        while (!game.isGameOver()) {
            game.play();
        }
        LOGGER.info("Game over");
    }
}
