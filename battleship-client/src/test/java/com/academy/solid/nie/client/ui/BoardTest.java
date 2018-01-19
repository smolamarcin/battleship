package com.academy.solid.nie.client.ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
        Board board = new Board(false);
        Point2D point = Point2D.of(0, 0);
        Ship ship = new Ship(Collections.singletonList(point));
        EventHandler<MouseEvent> handler = Mockito.mock(EventHandler.class);
        board.initialize(handler);

        new Cell(point).setShip(ship);

        //when
        boolean result = board.isShipPositionValid(ship);

        //then
        Assert.assertTrue(result);
    }

    public void shouldNotPlaceShip_WhenThereIsAlreadyShip() {
        //given
        Board board = new Board(false);
        EventHandler<MouseEvent> handler = Mockito.mock(EventHandler.class);
        board.initialize(handler);

        Point2D pointA = Point2D.of(1, 6);
        Ship ship = new Ship(Collections.singletonList(pointA));

        Point2D pointB = Point2D.of(0, 0);
        new Cell(pointB).setShip(ship);

        //when
        board.isShipPositionValid(ship);
        boolean result = board.isShipPositionValid(ship);

        //then
        Assert.assertFalse(result);
    }

    public void shouldNotPlaceShip_WhenNearbyIsShip() {
        //given
        EventHandler<MouseEvent> handler = Mockito.mock(EventHandler.class);
        Board board = new Board(false);
        board.initialize(handler);
        Point2D pointA = Point2D.of(0, 0);
        Ship ship = createShipHorizontally(Collections.singletonList(pointA));
        new Cell(pointA).setShip(ship);

        //when
        boolean result = board.isShipPositionValid(ship);
        Assert.assertTrue(result);

        Point2D pointB = Point2D.of(0, 1);
        Ship ship2 = createShipHorizontally(Collections.singletonList(pointB));
        new Cell(pointB).setShip(ship2);

        result = board.isShipPositionValid(ship2);

        //then
        Assert.assertFalse(result);
    }
}