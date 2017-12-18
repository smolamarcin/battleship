package com.epam.solid.nie.client.communication;

import com.epam.solid.nie.client.ui.Cell;

public interface Server {
    boolean connect(String ip);
    boolean canConnect(String ip);
    void passAllShips(String allShips);
    void sendPlayerMove(String move);
    Cell passEnemyMove();

    String receiveAllShips();
}
