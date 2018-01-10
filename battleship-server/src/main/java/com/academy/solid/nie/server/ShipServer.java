package com.academy.solid.nie.server;

import java.io.IOException;

interface ShipServer {
    void initializeGame() throws IOException;

    void play() throws IOException;

    boolean isGameOver();
}
