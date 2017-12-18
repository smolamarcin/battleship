package com.epam.solid.nie.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

class ShipSocketClient implements ShipClient {
    /**
     * dummy implementation
     */
    private final int portNumber = 8080;
    private final Logger logger = Logger.getLogger("ShipSocketClient");
    private String hostName;

    ShipSocketClient(String arg) {
        hostName = arg;
    }

    public void run() {
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

                fromUser = stdIn.readLine();
                if (fromUser != null)
                    out.println(fromUser);
            }
        }
        catch (IOException e){
            logger.warning(e.getMessage());
        }
    }
}
