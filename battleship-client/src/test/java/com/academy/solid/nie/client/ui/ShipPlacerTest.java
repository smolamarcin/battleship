package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.client.communication.SocketServer;
import com.academy.solid.nie.client.output.Output;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.Semaphore;

import static org.mockito.Matchers.any;
import static org.testng.Assert.assertTrue;

@Test(groups = "unit")
public class ShipPlacerTest {
    private Board board;
    private Output output;
    private Semaphore semaphore;
    private SocketServer socketServer;

    @BeforeMethod
    public void setUp() {
        board = Mockito.mock(Board.class);
        socketServer = Mockito.mock(SocketServer.class);
        semaphore = Mockito.mock(Semaphore.class);
        output = Mockito.mock(Output.class);
        Mockito.when(board.isShipPositionValid(any(Ship.class))).thenReturn(true);
        Mockito.when(socketServer.receiveAllShips()).thenReturn("1,3;2,3;3,3;4,3;|4,5;5,5;6,5;|2,6;2,7;2,8;|4,7;5,7;|8,5;9,5;|8,7;8,8;|0,7;|3,1;|9,0;|0,5;|");
    }

    public void randomPlacement() {
        //given
        ShipPlacer shipPlacer = new ShipPlacer(board, board, socketServer, semaphore, true, semaphore, output);
        //when
        shipPlacer.placeShipsRandomly();
        //then
        Mockito.verify(board, Mockito.times(20)).isShipPositionValid(any(Ship.class));
    }

    public void areAllShipsPlaced() {
        //given
        ShipPlacer shipPlacer = new ShipPlacer(board, board, socketServer, semaphore, false, semaphore, output);
        //when
        shipPlacer.placeShipsRandomly();
        //then
        assertTrue(shipPlacer.areAllShipsPlaced());
    }
}