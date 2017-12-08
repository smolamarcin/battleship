package com.epam.solid.nie.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

class ShipSocketServer implements ShipServer {
    /**
     * dummy implementation
     */
    private final int portNumber = 8080;
    private Logger logger = Logger.getLogger("ShipSocketServer");

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(portNumber, 0, InetAddress.getLoopbackAddress())) {
            for (int nbOfPlayer = 0; nbOfPlayer < 2; nbOfPlayer++) {
                Socket clientSocket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(clientSocket);
                serverThread.start();
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }
}
