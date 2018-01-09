package com.academy.solid.nie.server;

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
    private final Player first;
    private final Player second;
    private final String ip;
    private List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private boolean isGameOver;

    ShipSocketServer(Player first, Player second, String ip) {
        this.first = first;
        this.second = second;
        this.ip = ip;
    }

    @Override
    public void initialize() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER, 0, InetAddress.getByName(ip));

        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info("Server " + ip + " is here");
        }
        first.register(serverSocket);
        players.add(first);

        second.register(serverSocket);
        players.add(second);

        first.inform("Game has started. 1");
        second.inform("Game has started. 2");

        String firstShips = first.provideShips();
        String secondShips = second.provideShips();

        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info("First's ships:" + firstShips + "\n" + "Second's ships:" + secondShips);
        }
        first.inform(secondShips);
        second.inform(firstShips);

        currentPlayer = first;

        LOGGER.info("Initialized");
    }

    @Override
    public void play() throws IOException {
        String move = currentPlayer.makeMove();
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(players.indexOf(currentPlayer) + ":" + move);
        }
        if (move.equals("Q")) {
            isGameOver = true;
        }
        changeCurrentPlayer();
        currentPlayer.inform(move);
    }

    private void changeCurrentPlayer() {
        Player first = players.get(0);
        Player second = players.get(1);
        if (currentPlayer.equals(first)) {
            currentPlayer = second;
        } else {
            currentPlayer = first;
        }
    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }
}
