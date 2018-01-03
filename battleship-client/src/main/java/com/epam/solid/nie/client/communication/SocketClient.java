package com.epam.solid.nie.client.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Implementation of ShipClient interface.
 * Allows you to send and receive information from the server.
 *
 * @since 1.0.1
 */
public class SocketClient implements ShipClient {
    private String ip;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String enemyShips;
    private int portNumber = 8081;

    SocketClient(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean run() throws IOException {
        boolean result = false;

        socket = new Socket(ip, portNumber);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String fromServer;
        while (!(fromServer = in.readLine()).equals("Provide ships")) {
            System.out.println("Server: " + fromServer);
            if (fromServer.equals("Game has started. 1"))
                result = true;
        }
        return result;
    }

    @Override
    public void send(String allShips) throws IOException {
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
