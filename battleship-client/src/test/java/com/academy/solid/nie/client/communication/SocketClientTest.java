package com.academy.solid.nie.client.communication;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class SocketClientTest {
    private SocketClient socketClient;
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;


    @BeforeMethod(groups = "unit")
    public void setUp() {
        socket = mock(Socket.class);
        String ip = "";
        printWriter = mock(PrintWriter.class);
        bufferedReader = mock(BufferedReader.class);
        socketClient = SocketClient.builder().
                ip(ip).socket(socket).
                out(printWriter).
                in(bufferedReader).
                build();
    }

    @Test(groups = {"unit"})
    public void should_println_readLine_whenCallRunOnClient() throws IOException {
        String message = "asda";
        //given when
        socketClient.send(message);
        //then
        verify(printWriter).println(message);
        verify(bufferedReader).readLine();
    }

    @Test(groups = {"unit"})
    public void shouldSend_GameOverMessage_toOpponent() {
        //given
        String gameOverMessage = "Q";
        //when
        socketClient.sendGameOverToOpponent();
        //then
        verify(printWriter).println(gameOverMessage);
    }
}
