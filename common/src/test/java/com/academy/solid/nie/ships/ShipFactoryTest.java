package com.academy.solid.nie.ships;

import com.academy.solid.nie.utils.Point2D;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        verifyType(ship, HorizontalShip.class);
    }

    public void should_CreateVerticalShip() {
        //given
        ShipFactory factory = new VerticalShipFactory();

        //when
        BattleShip ship = factory.createShip(new ArrayList<>());

        //then
        verifyType(ship, VerticalShip.class);
    }

    public void should_CreateVerticalShip_WithPositions() {
        //given
        ShipFactory factory = new VerticalShipFactory();
        List<Point2D> positions = Arrays.asList(Point2D.of(0, 0), Point2D.of(1, 1), Point2D.of(2, 2));

        //when
        BattleShip ship = factory.createShip(positions);
        List<Point2D> shipProperties = ship.getShipProperties();

        //then
        verifyType(ship, VerticalShip.class);
        Assert.assertEquals(positions, shipProperties);
    }

    public void should_CreateHorizontalShip_WithPositions() {
        //given
        ShipFactory factory = new HorizontalShipFactory();
        List<Point2D> positions = Arrays.asList(Point2D.of(0, 0), Point2D.of(1, 0), Point2D.of(2, 0), Point2D.of(3, 0));

        //when
        BattleShip ship = factory.createShip(positions);
        List<Point2D> shipProperties = ship.getShipProperties();

        //then
        verifyType(ship, HorizontalShip.class);
        Assert.assertEquals(positions, shipProperties);
    }

    public void should_CreateHorizontalShip_WithPositions_andCheckHealth() {
        //given
        ShipFactory factory = new HorizontalShipFactory();
        List<Point2D> positions = Arrays.asList(Point2D.of(0, 0), Point2D.of(1, 0), Point2D.of(2, 0), Point2D.of(3, 0));

        //when
        BattleShip ship = factory.createShip(positions);
        int shipsRemainingHealth = ship.getShipsRemainingHealth(ship.getShipProperties());

        //then
        verifyType(ship, HorizontalShip.class);
        Assert.assertEquals(positions.size(), shipsRemainingHealth);

    }

    private void verifyType(BattleShip battleShip, Class<?> clazz) {
        Assert.assertTrue(clazz.isInstance(battleShip));
    }
}
