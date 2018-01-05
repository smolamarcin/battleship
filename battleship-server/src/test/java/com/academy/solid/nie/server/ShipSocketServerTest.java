package com.academy.solid.nie.server;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Test(groups = {"integration"})
public class ShipSocketServerTest {
    static final String IP = "127.0.0.1";
    private Player first;
    private Player second;
    private ServerThread serverThread;
    private ClientThread firstClientThread;
    private ClientThread secondClientThread;

    @BeforeMethod
    public void setUp() throws Exception {
        first = mock(Player.class);
        second = mock(Player.class);
        serverThread = new ServerThread(first, second);
        firstClientThread = new ClientThread();
        secondClientThread = new ClientThread();
    }

    public void afterConnectionSecondPlayerShouldBeRegistered() throws IOException {
        //given
        when(second.provideShips()).thenReturn("0,1,;");
        when(first.provideShips()).thenReturn("0,0,;");
        //when
        serverThread.start();
        firstClientThread.start();
        secondClientThread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //then
        verify(first, times(1)).register(any(ServerSocket.class));
        verify(second, times(1)).register(any(ServerSocket.class));
        verify(first, times(1)).inform("Game has started. 1");
        verify(second, times(1)).inform("Game has started. 2");
        verify(first, times(1)).provideShips();
        verify(second, times(1)).provideShips();

        verify(first, times(1)).inform("0,1,;");
        verify(second, times(1)).inform("0,0,;");
    }
}
