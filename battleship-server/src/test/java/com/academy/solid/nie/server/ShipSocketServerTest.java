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
    private String initialMessageForFirstPlayer = "Game has started. 1";
    private String initialMessageForSecondPlayer = "Game has started. 2";
    private int millis = 100;

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
        String shipsOfFirstPlayer = "0,1,;";
        when(second.provideShips()).thenReturn(shipsOfFirstPlayer);
        String shipsOfSecondPlayer = "0,0,;";
        when(first.provideShips()).thenReturn(shipsOfSecondPlayer);
        //when
        serverThread.start();
        firstClientThread.start();
        secondClientThread.start();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //then
        verify(first, times(1)).register(any(ServerSocket.class));
        verify(second, times(1)).register(any(ServerSocket.class));
        verify(first, times(1)).inform(initialMessageForFirstPlayer);
        verify(second, times(1)).inform(initialMessageForSecondPlayer);
        verify(first, times(1)).provideShips();
        verify(second, times(1)).provideShips();

        verify(first, times(1)).inform(shipsOfFirstPlayer);
        verify(second, times(1)).inform(shipsOfSecondPlayer);
    }
}
