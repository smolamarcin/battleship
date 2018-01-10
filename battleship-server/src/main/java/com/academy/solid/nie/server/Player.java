package com.academy.solid.nie.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;

/**
 * Provides methods for the player.
 */
public interface Player {

    void initialize(PrintWriter out, BufferedReader in);

    /**
     * Marks the connection of the player to the game.
     *
     * @param serverSocket represent single socket on the server
     * @throws IOException If something goes wrong on the server.
     */
    void register(ServerSocket serverSocket) throws IOException;

    /**
     * Informs player based on provided message.
     *
     * @param s represents message
     * @throws IOException when something fail
     */
    void inform(String s);

    /**
     * Provides ships to the player.
     *
     * @return string representation of the ships
     * @throws IOException when cannot provide the ships.
     */
    String provideShips() throws IOException;

    /**
     * Makes a move.
     *
     * @return string representation of the single move.
     * @throws IOException when cannot perform a move.
     */
    String makeMove() throws IOException;
}
