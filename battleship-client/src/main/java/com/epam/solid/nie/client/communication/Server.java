package com.epam.solid.nie.client.communication;

import com.epam.solid.nie.client.ui.Cell;

/**
 *
 */
public interface Server {
    boolean connect(String ip);
    void send(String allShips);
    void sendPlayerMove(String move);
    Cell receiveEnemyMove();
    String receiveAllShips();
    Cell receiveFirstMove();
}
