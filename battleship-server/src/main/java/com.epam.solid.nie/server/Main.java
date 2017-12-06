package com.epam.solid.nie.server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ShipServer shipServer = new ShipSocketServer();
        shipServer.run();
    }
}
