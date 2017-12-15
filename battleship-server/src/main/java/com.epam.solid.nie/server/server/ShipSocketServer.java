package com.epam.solid.nie.server.server;

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
    private final String ip;

    ShipSocketServer(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber, 0, InetAddress.getByName(ip))
        ) {
            System.out.println("Server is here");
            for (int nbOfPlayer = 0; nbOfPlayer < 2; nbOfPlayer++) {
                Socket clientSocket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(clientSocket);
                serverThread.start();
                System.out.println("registered");
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }
}
