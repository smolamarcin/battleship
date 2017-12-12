package com.epam.solid.nie.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ServerThread extends Thread{
    private final Socket clientSocket;

    ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


}
