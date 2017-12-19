package com.epam.solid.nie.client.communication;

import java.io.IOException;

interface ShipClient {
    boolean run() throws IOException;
    void send(String allShips) throws IOException;
    String getEnemyShips();
}
