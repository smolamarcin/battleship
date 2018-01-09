package com.academy.solid.nie.client.communication;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of ShipClient interface.
 * Allows you to send and receive information from the server.
 *
 * @since 1.0.1
 */
public final class SocketClient implements ShipClient {
    private static final Logger LOGGER = Logger.getLogger(SocketClient.class.getName());
    private String ip;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String enemyShips;
    private static final int PORT_NUMBER = 8081;

    SocketClient(final String ip) {
        this.ip = ip;
    }

    @Override
    public boolean run() throws IOException {
        boolean result = false;

        socket = new Socket(ip, PORT_NUMBER);
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

        String fromServer = in.readLine();
        while (!("Provide ships").equals(fromServer)) {
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info("Server: " + fromServer);
            }
            if (fromServer.equals("Game has started. 1")) {
                result = true;
            }
        }
        return result;
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
