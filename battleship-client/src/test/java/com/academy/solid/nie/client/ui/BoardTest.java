package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.utils.Point2D;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;


/**
 *
 */
@Test(groups = {"unit"})
public class BoardTest implements TestHelper {

    public void shouldPlaceShip_whenThereAreNoShips() {
        //given
        VBox rows = new VBox();
        Board board = new Board(false, rows);
        Ship ship = new Ship(Collections.singletonList(Point2D.of(0, 0)));
        EventHandler<MouseEvent> handler = Mockito.mock(EventHandler.class);
        board.initialize(handler);

        Cell cell = new Cell(Point2D.of(0, 0)).addShip(ship);

        //when
        boolean result = board.isShipPositionValid(ship);

        //then
        Assert.assertTrue(result);
    }

    public void shouldNotPlaceShip_WhenThereIsAlreadyShip() {
        //given
        VBox rows = new VBox();
        Board board = new Board(false, rows);
        EventHandler<MouseEvent> handler = Mockito.mock(EventHandler.class);
        board.initialize(handler);

        Ship ship = new Ship(Collections.singletonList(Point2D.of(1, 6)));

        Cell cell = new Cell(Point2D.of(0, 0)).addShip(ship);

        //when
        board.isShipPositionValid(ship);
        boolean result = board.isShipPositionValid(ship);

        //then
        Assert.assertFalse(result);
    }

    public void shouldNotPlaceShip_WhenNearbyIsShip() {
        //given
        VBox rows = new VBox();
        EventHandler<MouseEvent> handler = Mockito.mock(EventHandler.class);
        Board board = new Board(false, rows);
        board.initialize(handler);
        Ship ship = createShipHorizontally(Collections.singletonList(Point2D.of(0, 0)));
        Cell cell = new Cell(Point2D.of(0, 0)).addShip(ship);

        //when
        boolean result = board.isShipPositionValid(ship);
        Assert.assertTrue(result);

        Ship ship2 = createShipHorizontally(Collections.singletonList(Point2D.of(0, 1)));
        cell = new Cell(Point2D.of(0, 1)).addShip(ship2);

        result = board.isShipPositionValid(ship2);

        //then
        Assert.assertFalse(result);
    }
}