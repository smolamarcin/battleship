package com.epam.solid.nie.client.ui;

import com.epam.solid.nie.client.communication.SocketServer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ShipPlacerTest {

    @Test
    public void descriptionHere() {
        //given
        VBox rows = new VBox();
        EventHandler<MouseEvent> handler = Mockito.mock(EventHandler.class);
        Board enemyBoard = new Board(false, rows);
        enemyBoard.initialize(handler);
        Board playerBoard = new Board(false, rows);
        playerBoard.initialize(handler);
        List<Ship> expected = new ArrayList<>();

        ShipPlacer shipPlacer = new ShipPlacer(enemyBoard, playerBoard, new SocketServer());

        //when
        List<Ship> ships = shipPlacer.placeShipsRandomly("7,0,8,0,|");

        //then
        Assert.assertEquals(ships, expected);

    }

}