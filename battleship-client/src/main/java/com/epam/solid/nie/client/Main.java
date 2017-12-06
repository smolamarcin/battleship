package com.epam.solid.nie.client;

public class Main {
    public static void main(String[] args) {
        ShipClient shipServer = new ShipSocketClient();
        shipServer.run();
    }
}
