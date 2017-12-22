package com.epam.solid.nie.server.server;

import java.io.IOException;
import java.net.ServerSocket;

public interface Player {
    void register(ServerSocket serverSocket) throws IOException;

    void inform(String s) throws IOException;

    String provideShips() throws IOException;

    String makeMove() throws IOException;
}
