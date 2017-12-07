package com.epam.solid.nie.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ShipSocketClient implements ShipClient {

    public void run() {
        String hostName = "127.0.0.1";
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
                if (fromServer.equals("Bye."))
                    break;

                fromUser = stdIn.readLine();
                if (fromUser != null)
                    out.println(fromUser);
            }
        } catch (IOException ignored){
        }
    }
}
