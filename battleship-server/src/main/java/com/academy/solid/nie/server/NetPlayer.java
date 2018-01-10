package com.academy.solid.nie.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * Class represents single Player in the game.
 */
public final class NetPlayer implements Player {
    private static final Logger LOGGER = Logger.getLogger(NetPlayer.class.getName());
    private PrintWriter out;
    private BufferedReader in;

    @Override
    public void initialize(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void register(final ServerSocket serverSocket) throws IOException {
        out.println("hi. wait.");
        LOGGER.info("registered");
    }

    @Override
    public void inform(final String s) {
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
