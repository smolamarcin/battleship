package com.epam.solid.nie.client.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class SocketClient implements ShipClient {
    private Logger logger = Logger.getLogger(SocketClient.class.getName());
    private String ip;
    private PrintWriter out;
    private BufferedReader in;
    private String enemyShips;

    SocketClient(String ip) {
        this.ip = ip;
    }

    public boolean run() throws IOException {
        boolean result = false;

        int portNumber = 8081;
        Socket socket = new Socket(ip, portNumber);
        try{
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String fromServer;
            while (!(fromServer = in.readLine()).equals("Provide ships")) {
                logger.info(String.format("Server: %s", fromServer));
                if (fromServer.equals("Game has started. 1"))
                    result = true;
            }

        } finally {
            socket.close();
        }
                return result;
    }

    @Override
    public void send(String allShips) throws IOException {
        out.println(allShips);
        enemyShips = in.readLine();
    }

    public String getEnemyShips() {
        return enemyShips;
    }
}
