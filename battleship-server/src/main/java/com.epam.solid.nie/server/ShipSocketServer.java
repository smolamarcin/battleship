package com.epam.solid.nie.server;

class ShipSocketServer implements ShipServer {
    @Override
    public void run() {
        int nbOfPlayer = 0;
        while (nbOfPlayer < 2) {
            ServerThread serverThread = new ServerThread();
            serverThread.start();
            nbOfPlayer++;
            System.out.println("registered");
        }
    }
}
