package com.academy.solid.nie.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Provides methods for the player.
 */
public interface Player {
    String getShips();
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
     * @return String representation of the ships
     * @throws IOException when cannot provide the ships.
     */
    String provideShips() throws IOException;

    /**
     * Makes a move.
     *
     * @return String representation of the single move.
     * @throws IOException when cannot perform a move.
     */
    String makeMove() throws IOException;
}
