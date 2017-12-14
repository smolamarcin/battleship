package com.epam.solid.nie.client.ui.tutorial;

public interface Server {
    void connect(String ip);
    boolean canConnect(String ip);
    void passAllShips(String allShips);
    void sendPlayerMove(String move);
    Cell passEnemyMove();
}
