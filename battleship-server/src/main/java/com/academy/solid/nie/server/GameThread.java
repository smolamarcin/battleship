package com.academy.solid.nie.server;

import java.io.IOException;
import java.util.logging.Logger;

class GameThread implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(GameThread.class.getName());
    private int port;

    GameThread(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            GameInitializer gameInitializer;
            Player first = new NetPlayer();
            Player second = new NetPlayer();
            gameInitializer = new ServerGameInitializer(first, second, port);
            gameInitializer.initializeGame();
            Game game = new Game(first, second);
            while (!game.isGameOver()) {
                game.play();
            }
            LOGGER.info("Game over");
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
