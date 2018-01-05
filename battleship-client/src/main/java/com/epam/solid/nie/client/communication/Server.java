package com.epam.solid.nie.client.communication;

import com.epam.solid.nie.client.ui.Cell;

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
    boolean connect(String ip);

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

    Cell receiveEnemyMove();

    String receiveAllShips();

    Cell receiveFirstMove();

    /**
     * Sends a message about the defeat to the opponent.
     */
    void sendGameOverToOpponent();
}
