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
        GameInitializer gameInitializer;
        Player first = new NetPlayer();
        Player second = new NetPlayer();
        int port = Integer.parseInt(args[0]);
        gameInitializer = new ServerGameInitializer(first, second, port);
        gameInitializer.initializeGame();
        Game game = new Game(first, second);
        while (!game.isGameOver()) {
            game.play();
        }
        LOGGER.info("Game over");
    }
}
