package com.epam.solid.nie.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class ServerThread extends Thread{
    private final int portNumber = 8080;
    public void run() {

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber, 0, InetAddress.getLoopbackAddress());
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String inputLine;
            String outputLine;

            Protocol protocol = new CommunicationProtocol();
            outputLine = protocol.processInput("initial value");
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = protocol.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException ignored){
        }
    }
}
