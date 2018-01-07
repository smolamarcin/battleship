package com.academy.solid.nie.ships;

import com.academy.solid.nie.utils.Point2D;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by marek on 13.12.2017.
 */
@Test(groups = {"unit"})
public class ShipFactoryTest {


    public void should_createHorizontalShip() {
        //given
        ShipFactory factory = new HorizontalShipFactory();

        //when
        BattleShip ship = factory.createShip(new ArrayList<>());

        //then
        assertEquals(ship.getType(), Type.HORIZONTAL);
    }

    public void should_CreateVerticalShip() {
        //given
        ShipFactory factory = new VerticalShipFactory();

        //when
        BattleShip ship = factory.createShip(new ArrayList<>());

        //then
        assertEquals(ship.getType(), Type.VERTICAL);
    }

    public void should_CreateVerticalShip_WithPositions() {
        //given
        ShipFactory factory = new VerticalShipFactory();
        List<Point2D> positions = Arrays.asList(Point2D.of(0, 0), Point2D.of(1, 1), Point2D.of(2, 2));

        //when
        BattleShip ship = factory.createShip(positions);
        List<Point2D> shipProperties = ship.getPositions();

        //then
        assertEquals(ship.getType(), Type.VERTICAL);
        assertEquals(positions, shipProperties);
    }

    public void should_CreateHorizontalShip_WithPositions() {
        //given
        ShipFactory factory = new HorizontalShipFactory();
        List<Point2D> positions = Arrays.asList(Point2D.of(0, 0), Point2D.of(1, 0), Point2D.of(2, 0), Point2D.of(3, 0));

        //when
        BattleShip ship = factory.createShip(positions);
        List<Point2D> shipProperties = ship.getPositions();

        //then
        assertEquals(ship.getType(), Type.HORIZONTAL);
        assertEquals(positions, shipProperties);
    }

    public void should_CreateHorizontalShip_WithPositions_andCheckHealth() {
        //given
        ShipFactory factory = new HorizontalShipFactory();
        List<Point2D> positions = Arrays.asList(Point2D.of(0, 0), Point2D.of(1, 0), Point2D.of(2, 0), Point2D.of(3, 0));

        //when
        BattleShip ship = factory.createShip(positions);
        int shipsRemainingHealth = ship.getPositions().size();

        //then
        assertEquals(ship.getType(), Type.HORIZONTAL);
        assertEquals(positions.size(), shipsRemainingHealth);

    }

    private void verifyType(BattleShip battleShip, Class<?> clazz) {
        assertTrue(clazz.isInstance(battleShip));
    }
}
