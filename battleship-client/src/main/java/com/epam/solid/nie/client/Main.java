package com.epam.solid.nie.client;

public class Main {
    public static void main(String[] args) {
        System.out.println("server ip is: " + (args.length > 0 ? args[0] : ""));
        ShipClient shipClient = new ShipSocketClient(args[0]);
        shipClient.run();
    }
}
