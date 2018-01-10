package com.academy.solid.nie.server;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Test
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

    @BeforeMethod(groups = {"unit", "integration"})
    public void setUp() throws Exception {
        first = mock(Player.class);
        second = mock(Player.class);
        serverThread = new ServerThread(first, second);
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
        ShipSocketServer shipSocketServer = new ShipSocketServer(first, second, "127.0.0.2");
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

    @Test(groups = {"unit"})
    public void afterOneInvokeOfMethodPlaySecondPlayerShouldReceiveFirstPlayersMove() throws IOException {
        //given
        String moveOfFirstPlayer = "0,0";
        when(first.makeMove()).thenReturn(moveOfFirstPlayer);
        //when
        ShipSocketServer shipSocketServer = new ShipSocketServer(first, second, "127.0.0.3");
        shipSocketServer.initializeGame();
        shipSocketServer.play();
        //then
        verify(second).inform(moveOfFirstPlayer);
    }

    @Test(groups = {"unit"})
    public void afterSecondInvokeOfMethodPlayFirstPlayerShouldReceiveSecondPlayersMove() throws IOException {
        //given
        String moveOfSecondPlayer = "0,1";
        when(second.makeMove()).thenReturn(moveOfSecondPlayer);
        String moveOfFirstPlayer = "0,0";
        when(first.makeMove()).thenReturn(moveOfFirstPlayer);
        //when
        ShipSocketServer shipSocketServer = new ShipSocketServer(first, second, "127.0.0.4");
        shipSocketServer.initializeGame();
        shipSocketServer.play();
        shipSocketServer.play();
        //then
        verify(second).inform(moveOfFirstPlayer);
        verify(first).inform(moveOfSecondPlayer);
    }
    @Test(groups = {"unit"})
    public void afterThirdInvokeOfMethodPlaySecondPlayerShouldReceiveFirstsPlayersMoveAgain() throws IOException {
        //given
        String moveOfSecondPlayer = "0,1";
        when(second.makeMove()).thenReturn(moveOfSecondPlayer);
        String moveOfFirstPlayer = "0,0";
        when(first.makeMove()).thenReturn(moveOfFirstPlayer);
        //when
        ShipSocketServer shipSocketServer = new ShipSocketServer(first, second, "127.0.0.5");
        shipSocketServer.initializeGame();
        shipSocketServer.play();
        shipSocketServer.play();
        shipSocketServer.play();
        //then
        verify(second, times(2)).inform(moveOfFirstPlayer);
        verify(first).inform(moveOfSecondPlayer);
    }
    @Test(groups = {"unit"})
    public void gameShouldBeOverAfterSendingMoveWithEndOfGameInformation() throws IOException {
        //given
        String moveOfFirstPlayer = "Q";
        when(first.makeMove()).thenReturn(moveOfFirstPlayer);
        //when
        ShipSocketServer shipSocketServer = new ShipSocketServer(first, second, "127.0.0.6");
        shipSocketServer.initializeGame();
        shipSocketServer.play();
        //then
        Assert.assertTrue(shipSocketServer.isGameOver());
    }
}
