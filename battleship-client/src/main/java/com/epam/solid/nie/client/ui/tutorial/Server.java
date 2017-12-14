package com.epam.solid.nie.client.ui.tutorial;

public interface Server {
    void connect(String ip);
    boolean canConnect(String ip);
    void passAllShips(String allShips);
    String passLastMove(String move);

}
