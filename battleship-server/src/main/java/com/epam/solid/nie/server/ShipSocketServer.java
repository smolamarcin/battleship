package com.epam.solid.nie.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class ShipSocketServer implements ShipServer {
    private final Logger logger = Logger.getLogger(ShipSocketServer.class.getName());
    private final String ip;
    private List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private boolean isGameOver;
    private static   final int PORT_NUMBER = 8081;

    ShipSocketServer(String ip) {
        this.ip = ip;
    }

    @Override
    public void initialize() throws IOException {
        final ServerSocket serverSocket = new ServerSocket(PORT_NUMBER, 0, InetAddress.getByName(ip));

        if(logger.isLoggable(Level.INFO)){
            logger.info(String.format("Server %s is here", ip));
        }
        final Player first = new NetPlayer();
        first.register(serverSocket);
        players.add(first);
        final Player second = new NetPlayer();
        second.register(serverSocket);
        players.add(second);
        second.inform("Game has started. 1");
        first.inform("Game has started. 2");
        final String firstShips = first.provideShips();
        final String secondShips = second.provideShips();

        if (logger.isLoggable(Level.INFO)){
            logger.info(String.format("First's ships:%s", firstShips));
            logger.info(String.format("Second's ships: %s", secondShips));
        }

        first.inform(secondShips);
        second.inform(firstShips);
        currentPlayer = first;
        logger.info("Initialized");
    }

    @Override
    public void play() throws IOException {
        String move = currentPlayer.makeMove();
        if(logger.isLoggable(Level.INFO)){
            logger.info(String.valueOf(players.indexOf(currentPlayer)) + ":" + move);
        }
        if(move.equals("Q"))
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
