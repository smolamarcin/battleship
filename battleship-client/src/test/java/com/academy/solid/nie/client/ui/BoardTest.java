package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.client.output.Output;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


/**
 *
 */
@Test(groups = {"unit"})
public class BoardTest implements TestHelper {

    Output output = Mockito.mock(Output.class);

    public void shouldPlaceShip_whenThereAreNoShips() {
        //given
        Board board = new Board(false);
        Point2D point = Point2D.of(0, 0);
        Ship ship = new Ship(Collections.singletonList(point));
        EventHandler<MouseEvent> handler = Mockito.mock(EventHandler.class);
        board.initialize(handler);

        //when - then
        assertTrue(board.isShipPositionValid(ship));
    }

    public void shouldNotPlaceShip_WhenThereIsAlreadyShip() {
        //given
        Board board = new Board(false);
        EventHandler<MouseEvent> handler = Mockito.mock(EventHandler.class);
        board.initialize(handler);

        Point2D pointA = Point2D.of(1, 6);
        Ship ship = new Ship(Collections.singletonList(pointA));

        board.isShipPositionValid(ship);

        //when - then
        assertFalse(board.isShipPositionValid(ship));
    }

    public void shouldNotPlaceShip_WhenNearbyIsShip() {
        //given
        EventHandler<MouseEvent> handler = Mockito.mock(EventHandler.class);
        Board board = new Board(false);
        board.initialize(handler);
        Point2D pointA = Point2D.of(0, 0);
        Ship ship = createShipHorizontally(Collections.singletonList(pointA));

        board.isShipPositionValid(ship);

        Point2D pointB = Point2D.of(0, 1);
        Ship ship2 = createShipHorizontally(Collections.singletonList(pointB));

        //when - then
        assertFalse(board.isShipPositionValid(ship2));
    }
}