package com.academy.solid.nie.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class represents single Player in the game.
 */
public final class NetPlayer implements Player {
    private static final Logger LOGGER = Logger.getLogger(NetPlayer.class.getName());
    private PrintWriter out;
    private BufferedReader in;
    private List<List<String>> shipsPositions;
    private boolean wasSunk;

    @Override
    public void register(final ServerSocket serverSocket) throws IOException {
        Socket clientSocket = serverSocket.accept();
        out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));

        out.println("hi. wait.");
        LOGGER.info("registered");
    }

    @Override
    public void inform(final String s) {
        out.println(s);
    }

    @Override
    public String provideShips() throws IOException {
        out.println("Provide ships");
        String ships = in.readLine();
        shipsPositions = Converter.convert(ships);
        return ships;
    }

    @Override
    public String makeMove() throws IOException {
        return in.readLine();
    }

    @Override
    public boolean shallPlayersBeChanged(String move) {
        for (List<String> ship : shipsPositions) {
            if (ship.remove(move)) {
                wasSunk = ship.isEmpty();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean wasSunk() {
        return wasSunk;
    }

    @Override
    public boolean isGameOver() {
        return shipsPositions.stream().allMatch(List::isEmpty);
    }
}
