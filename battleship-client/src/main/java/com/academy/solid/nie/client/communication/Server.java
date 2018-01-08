package com.academy.solid.nie.client.communication;

import com.academy.solid.nie.utils.Point2D;

import java.util.Queue;

/**
 * Lists methods for the server.
 * Enables communication between players.
 *
 * @since 1.0.1
 */
public interface Server {
    /**
     * Returns true if connection was successful.
     *
     * @param ip as String
     * @return true if the connection attempt was successful.
     */
    void connect(String ip);

    /**
     * Send a list of ships between clients
     *
     * @param allShips
     */
    void send(String allShips);

    /**
     * Send single move of a player.
     *
     * @param move as String
     */
    void sendPlayerMove(String move);

    Queue<Point2D> receiveEnemyMoves();

    String receiveAllShips();

    /**
     * Sends a message about the defeat to the opponent
     */
    void sendGameOverToOpponent();
}
