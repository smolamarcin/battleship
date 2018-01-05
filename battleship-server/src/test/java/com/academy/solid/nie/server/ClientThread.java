package com.academy.solid.nie.server;

import com.academy.solid.nie.client.communication.SocketServer;

import static com.academy.solid.nie.server.ShipSocketServerTest.IP;

public class ClientThread extends Thread{
    @Override
    public void run() {
        SocketServer socketServer = new SocketServer();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        socketServer.connect(IP);
    }
}
