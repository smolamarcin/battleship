package com.epam.solid.nie.client.ui;

import com.epam.solid.nie.client.communication.SocketServer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ShipPlacerTest {

    @Test
    public void descriptionHere() {
        //given
        EventHandler<MouseEvent> handler = Mockito.mock(EventHandler.class);
        Board enemyBoard = new Board(false);
        enemyBoard.initialize(handler);
        Board playerBoard = new Board(false);
        playerBoard.initialize(handler);
        List<Ship> expected = new ArrayList<>();

        ShipPlacer shipPlacer = new ShipPlacer(enemyBoard, playerBoard, new SocketServer());

        //when
        String[] arr = {"8", "0", "8", "1"};
        String val = shipPlacer.createShip(arr, false);
        //then
        Assert.assertEquals(val, "vertical");
    }

    @Test
    public void descriptionHere2() {
        //given
        EventHandler<MouseEvent> handler = Mockito.mock(EventHandler.class);
        Board enemyBoard = new Board(false);
        enemyBoard.initialize(handler);
        Board playerBoard = new Board(false);
        playerBoard.initialize(handler);
        List<Ship> expected = new ArrayList<>();

        ShipPlacer shipPlacer = new ShipPlacer(enemyBoard, playerBoard, new SocketServer());

        //when
        String[] arr = {"7", "1", "8", "1"};
        String val = shipPlacer.createShip(arr, false);
        //then
        Assert.assertEquals(val, "horizontal");
    }

}