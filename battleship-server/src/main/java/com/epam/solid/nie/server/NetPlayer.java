package com.epam.solid.nie.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class NetPlayer implements Player {
    private static final Logger LOGGER = Logger.getLogger(NetPlayer.class.getName());
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    @Override
    public void register(ServerSocket serverSocket) throws IOException {
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        out.println("hi. wait.");
        LOGGER.info("registered");
    }

    @Override
    public void inform(String s) throws IOException {
        out.println(s);
    }

    @Override
    public String provideShips() throws IOException {
        out.println("Provide ships");
        return in.readLine();
    }

    @Override
    public String makeMove() throws IOException {
        return in.readLine();
    }
}
