package com.academy.solid.nie.client.communication;

import com.academy.solid.nie.client.output.Output;
import lombok.Builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Implementation of ShipClient interface.
 * Allows you to send and receive information from the server.
 *
 * @since 1.0.1
 */
@Builder
public final class SocketClient implements ShipClient {
    private static final int PORT_NUMBER = 8081;
    private String ip;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String enemyShips;
    private Output output;

    @Override
    public boolean receiveServerInitialMessage() throws IOException {
        String fromServer = in.readLine();
        boolean firstPlayer = false;
        while (!("Provide ships").equals(fromServer)) {
            output.send("Server: " + fromServer);
            fromServer = in.readLine();
            if ("Game has started. 1".equals(fromServer)) {
                firstPlayer = true;
            }
        }
        return firstPlayer;
    }

    @Override
    public void send(final String allShips) throws IOException {
        out.println(allShips);
    }

    @Override
    public String getEnemyShips() {
        try {
            return in.readLine();
        } catch (IOException e) {
            output.send(e.getMessage());
        }
        return null;
    }

    @Override
    public void sendGameOverToOpponent() {
        out.println("Q");
    }

    @Override
    public String receiveMoves() throws IOException {
        return in.readLine();
    }
}
