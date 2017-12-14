package com.epam.solid.nie.server;

public class Main {
    public static void main(String[] args) {
        ShipServer shipServer = new ShipSocketServer(args.length > 0 ? args[0] : "");
        shipServer.run();
    }
}
