package com.academy.solid.nie.server;

import com.academy.solid.nie.client.communication.SocketServer;
import com.academy.solid.nie.client.output.LoggerOutputDispatcher;

import java.io.IOException;

import static com.academy.solid.nie.server.ServerGameInitializerTest.IP;
import static com.academy.solid.nie.server.ServerGameInitializerTest.PORT;

public class ClientThread extends Thread {

    @Override
    public void run() {
        SocketServer socketServer = new SocketServer();
        socketServer.connect(IP,PORT);
    }
}
