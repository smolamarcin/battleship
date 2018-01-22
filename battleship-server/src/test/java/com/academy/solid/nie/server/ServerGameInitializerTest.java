package com.academy.solid.nie.server;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@Test
public class ServerGameInitializerTest {
    static final String IP = "127.0.0.1";
    static final int PORT = 9970;
    private int millis = 100;
    /**
     * 0 stands for first available port
     */
    private static final int AVAILABLE_PORT = 0;
    private Player first;
    private Player second;
    private ServerThread serverThread;
    private ClientThread firstClientThread;
    private ClientThread secondClientThread;
    private String initialMessageForFirstPlayer = "Game has started. 1";
    private String initialMessageForSecondPlayer = "Game has started. 2";

    @BeforeMethod(groups = {"unit", "integration"})
    public void setUp() throws Exception {
        first = mock(Player.class);
        second = mock(Player.class);
        serverThread = new ServerThread(first, second, AVAILABLE_PORT);
        firstClientThread = new ClientThread();
        secondClientThread = new ClientThread();
    }

    @Test(groups = {"integration"})
    public void afterConnectionSecondPlayerShouldBeRegistered() throws IOException {
        //given
        String shipsOfSecondPlayer = "0,1,;";
        when(second.provideShips()).thenReturn(shipsOfSecondPlayer);
        String shipsOfFirstPlayer = "0,0,;";
        when(first.provideShips()).thenReturn(shipsOfFirstPlayer);
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
        verify(first).register(any(ServerSocket.class));
        verify(second).register(any(ServerSocket.class));
        verify(first).inform(initialMessageForFirstPlayer);
        verify(second).inform(initialMessageForSecondPlayer);
        verify(first).provideShips();
        verify(second).provideShips();

        verify(first).inform(shipsOfSecondPlayer);
        verify(second).inform(shipsOfFirstPlayer);
    }

    @Test(groups = {"unit"})
    public void methodInitialized() throws IOException {
        //given
        String shipsOfSecondPlayer = "0,1,;";
        when(second.provideShips()).thenReturn(shipsOfSecondPlayer);
        String shipsOfFirstPlayer = "0,0,;";
        when(first.provideShips()).thenReturn(shipsOfFirstPlayer);
        //when
        ServerGameInitializer shipSocketServer = new ServerGameInitializer(first, second, AVAILABLE_PORT);
        shipSocketServer.initializeGame();
        //then
        verify(first).register(any(ServerSocket.class));
        verify(second).register(any(ServerSocket.class));
        verify(first).inform(initialMessageForFirstPlayer);
        verify(second).inform(initialMessageForSecondPlayer);
        verify(first).provideShips();
        verify(second).provideShips();

        verify(first).inform(shipsOfSecondPlayer);
        verify(second).inform(shipsOfFirstPlayer);
    }
}
