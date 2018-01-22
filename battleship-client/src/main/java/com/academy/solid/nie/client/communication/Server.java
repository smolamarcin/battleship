package com.academy.solid.nie.client.communication;

import com.academy.solid.nie.client.ui.Point2D;

import java.io.IOException;
import java.util.List;

/**
 * Lists methods for the server.
 * Enables communication between players.
 *
 * @since 1.0.1
 */
public interface Server {
    /**
     * Tries to connect to server.
     *
     * @param ip   as String
     * @param port you want to connect
     * @throws IOException when there is no server with provided IP
     */
    void connect(String ip, int port) throws IOException;

    /**
     * Send a list of ships between clients.
     *
     * @param allShips as String
     */
    void send(String allShips);

    /**
     * Send single move of a player.
     *
     * @param move as String
     */
    void sendPlayerMove(String move);


    /**
     * @return collection of positions
     */
    List<Point2D> receiveEnemyMoves();

    /**
     * @return ships positions as String
     */
    String receiveAllShips();

    /**
     * Sends a message about the defeat to the opponent.
     */
    void sendGameOverToOpponent();
}
