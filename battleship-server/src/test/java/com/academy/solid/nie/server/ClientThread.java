package com.academy.solid.nie.server;

import com.academy.solid.nie.client.communication.SocketServer;

import static com.academy.solid.nie.server.ServerGameInitializerTest.IP;

public class ClientThread extends Thread {

    @Override
    public void run() {
        SocketServer socketServer = new SocketServer();
        socketServer.connect(IP);
    }
}
