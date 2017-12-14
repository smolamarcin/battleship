package com.epam.solid.nie.client.ui;

import com.epam.solid.nie.client.ui.tutorial.Server;

public class SocketServer implements Server {
    public SocketServer() {

    }

    @Override
    public boolean canConnect(String ip) {
        return true;
    }

    @Override
    public void passAllShips(String allShips) {

    }

    @Override
    public String passLastMove(String move) {
        return null;
    }

    @Override
    public void connect(String ip) {

    }
}
