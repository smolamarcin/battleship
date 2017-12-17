package com.epam.solid.nie.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ShipSocketClient implements ShipClient {

    private String hostName;

    ShipSocketClient(String arg) {
        hostName = arg;
    }

    public void run() {
        int portNumber = 8080;

        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ){
            String fromServer;
            String fromUser;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;

                if (fromServer.equals("Provide ships") || fromServer.equals("Make move")) {
                    fromUser = stdIn.readLine();
                    if (fromUser != null) {
                        out.println(fromUser);
                        System.out.println("Me: " + fromUser);
                    }
                }
            }
        } catch (IOException ignored){
        }
    }
}
