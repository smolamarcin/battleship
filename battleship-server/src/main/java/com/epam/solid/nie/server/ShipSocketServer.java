package com.epam.solid.nie.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class ShipSocketServer implements ShipServer {
    private static final Logger LOGGER = Logger.getLogger(ShipSocketServer.class.getName());
    private static final int PORT_NUMBER = 8081;
    private final String ip;
    private List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private boolean isGameOver;

    ShipSocketServer(String ip) {
        this.ip = ip;
    }

    @Override
    public void initialize() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER, 0, InetAddress.getByName(ip));

        if (LOGGER.isLoggable(Level.INFO))
            LOGGER.info("Server " + ip + " is here");

        Player first = new NetPlayer();
        first.register(serverSocket);
        players.add(first);

        Player second = new NetPlayer();
        second.register(serverSocket);
        players.add(second);

        second.inform("Game has started. 1");
        first.inform("Game has started. 2");

        String firstShips = first.provideShips();
        String secondShips = second.provideShips();

        if (LOGGER.isLoggable(Level.INFO))
            LOGGER.info("First's ships:" + firstShips + "\n" + "Second's ships:" + secondShips);

        first.inform(secondShips);
        second.inform(firstShips);

        currentPlayer = first;

        LOGGER.info("Initialized");
    }

    @Override
    public void play() throws IOException {
        String move = currentPlayer.makeMove();
        if (LOGGER.isLoggable(Level.INFO))
            LOGGER.info(players.indexOf(currentPlayer) + ":" + move);
        if (move.equals("Q"))
            isGameOver = true;
        changeCurrentPlayer();
        currentPlayer.inform(move);
    }

    private void changeCurrentPlayer() {
        Player first = players.get(0);
        Player second = players.get(1);
        currentPlayer = currentPlayer.equals(first) ? second : first;
    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }
}
