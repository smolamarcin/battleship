package com.academy.solid.nie.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

class ServerGameInitializer implements GameInitializer {
    private static final Logger LOGGER = Logger.getLogger(ServerGameInitializer.class.getName());
    /**
     * 0 stands for first available port
     */
    private final Player first;
    private final Player second;
    private final int portNumber;

    ServerGameInitializer(Player first, Player second, int portNumber) {
        this.first = first;
        this.second = second;
        this.portNumber = portNumber;
    }

    @Override
    public void initializeGame() throws IOException {
        registerPlayers(initializeConnection());
        informAboutGameBeginning();
        exchangeShips();
        LOGGER.info("Initialized");
    }

    private ServerSocket initializeConnection() throws IOException {
        ServerSocket serverSocket = new ServerSocket(this.portNumber);
        log("Server is here");
        return serverSocket;
    }

    private void registerPlayers(ServerSocket serverSocket) throws IOException {
        first.register(serverSocket);
        second.register(serverSocket);
    }

    private void informAboutGameBeginning() {
        first.inform("Game has started. 1");
        second.inform("Game has started. 2");
    }

    private void exchangeShips() throws IOException {
        String firstShips = first.provideShips();
        String secondShips = second.provideShips();
        first.inform(secondShips);
        second.inform(firstShips);

        log("First's ships:" + firstShips + "\n" + "Second's ships:" + secondShips);
    }

    private void log(String msg) {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(msg);
        }
    }
}
