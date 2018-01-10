package com.academy.solid.nie.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

class ShipSocketServer implements ShipServer {
    private static final Logger LOGGER = Logger.getLogger(ShipSocketServer.class.getName());
    private static final int PORT_NUMBER = 8081;
    private final Player first;
    private final Player second;
    private final String ip;
    private Player currentPlayer;
    private boolean isGameOver;

    ShipSocketServer(Player first, Player second, String ip) {
        this.first = first;
        this.second = second;
        this.ip = ip;
        this.currentPlayer = first;
    }

    @Override
    public void initializeGame() throws IOException {
        ServerSocket serverSocket = initializeConnection();
        registerPlayers(first, serverSocket);
        registerPlayers(second, serverSocket);
        informAboutGameBeginning();
        exchangeShips();
        LOGGER.info("Initialized");
    }

    private ServerSocket initializeConnection() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER, 0, InetAddress.getByName(ip));
        log("Server " + ip + " is here");
        return serverSocket;
    }

    private void registerPlayers(Player player, ServerSocket serverSocket) throws IOException {
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
        player.initialize(out, in);
        player.register(serverSocket);
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

    @Override
    public void play() throws IOException {
        String move = currentPlayer.makeMove();
        changeCurrentPlayer();
        currentPlayer.inform(move);

        log((currentPlayer.equals(first) ? "0" : "1") + ":" + move);

        isGameOver = move.equals("Q");
    }

    private void changeCurrentPlayer() {
        currentPlayer = currentPlayer.equals(first) ? second : first;
    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }

    private void log(String msg) {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(msg);
        }
    }
}
