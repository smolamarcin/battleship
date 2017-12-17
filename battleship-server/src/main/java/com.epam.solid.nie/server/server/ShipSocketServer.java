package com.epam.solid.nie.server.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class ShipSocketServer implements ShipServer {
    /**
     * dummy implementation
     */
    private final int portNumber = 8080;
    private Logger logger = Logger.getLogger("ShipSocketServer");
    private final String ip;
    private List<Player> players = new ArrayList<>();
    private ServerSocket serverSocket;
    private Player currentPlayer;
    private boolean isGameOver;

    ShipSocketServer(String ip) {
        this.ip = ip;
    }

    @Override
    public void initialize() throws IOException {
        serverSocket = new ServerSocket(portNumber, 0, InetAddress.getByName(ip));

        System.out.println("Server " + ip + " is here");

        Player first = new NetPlayer();
        first.register(serverSocket);
        players.add(first);

        Player second = new NetPlayer();
        second.register(serverSocket);
        players.add(second);

        second.inform("Game has started.");
        first.inform("Game has started.");

        String firstShips = first.provideShips();
        System.out.println("First's ships:" + firstShips);

        String secondShips = second.provideShips();
        System.out.println("Second's ships:" + secondShips);

        first.inform(secondShips);

        second.inform(firstShips);

        currentPlayer = first;

        System.out.println("Initialized");
    }

    @Override
    public void play() throws IOException {
        System.out.println("play");
        String move = currentPlayer.makeMove();
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
