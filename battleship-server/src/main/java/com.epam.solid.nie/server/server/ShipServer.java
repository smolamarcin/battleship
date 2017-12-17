package com.epam.solid.nie.server.server;

import java.io.IOException;

interface ShipServer {
    void initialize() throws IOException;

    void play() throws IOException;

    boolean isGameOver();
}
