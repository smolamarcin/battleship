package com.academy.solid.nie.server;

import com.academy.solid.nie.client.communication.SocketServer;
import com.academy.solid.nie.client.output.LoggerOutputDispatcher;

import static com.academy.solid.nie.server.ServerGameInitializerTest.IP;
import static com.academy.solid.nie.server.ServerGameInitializerTest.PORT;

public class ClientThread extends Thread {

    @Override
    public void run() {
        SocketServer socketServer = new SocketServer(new LoggerOutputDispatcher());
        socketServer.connect(IP,PORT);
    }
}
