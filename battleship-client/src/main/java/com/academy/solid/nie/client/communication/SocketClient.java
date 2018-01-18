package com.academy.solid.nie.client.communication;

import lombok.Builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of ShipClient interface.
 * Allows you to send and receive information from the server.
 *
 * @since 1.0.1
 */
@Builder
public final class SocketClient implements ShipClient {
    private static final Logger LOGGER = Logger.getLogger(SocketClient.class.getName());
    private String ip;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String enemyShips;
    private static final int PORT_NUMBER = 8081;

    @Override
    public void run() throws IOException {
        String fromServer = in.readLine();
        while (!("Provide ships").equals(fromServer)) {
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info("Server: " + fromServer);
            }
            fromServer = in.readLine();
        }
    }

    @Override
    public void send(final String allShips) throws IOException {
        out.println(allShips);
        enemyShips = in.readLine();
    }

    @Override
    public String getEnemyShips() {
        return enemyShips;
    }

    @Override
    public void sendGameOverToOpponent() {
        out.println("Q");
    }
}
