package com.epam.solid.nie.ships;

import com.epam.solid.nie.utils.Point2D;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by marek on 13.12.2017.
 */
@Test
public class ShipFactoryTest {

    public void should_createHorizontalShip(){
        //given
        ShipFactory factory = new HorizontalShipFactory();

        //when
        BattleShip ship = factory.createShip(BattleShipType.FIVE_MASTED, null);

        //then
        verifyType(ship, BattleShipType.FIVE_MASTED, HorizontalShip.class);
    }

    public void should_CreateVerticalShip(){
        //given
        ShipFactory factory = new VerticalShipFactory();

        //when
        BattleShip ship = factory.createShip(BattleShipType.TWO_MASTED, null);

        //then
        verifyType(ship, BattleShipType.TWO_MASTED, VerticalShip.class);
    }

    public void should_CreateVerticalShip_WithPositions(){
        //given
        ShipFactory factory = new VerticalShipFactory();
        List<Point2D> positions = Arrays.asList(new Point2D(0,0), new Point2D(1,1), new Point2D(2,2));

        //when
        BattleShip ship = factory.createShip(BattleShipType.THREE_MASTED, positions);
        List<Point2D> shipProperties = ship.getShipProperties();

        //then
        verifyType(ship, BattleShipType.THREE_MASTED, VerticalShip.class);
        Assert.assertEquals(positions, shipProperties);
    }

    public void should_CreateHorizontalShip_WithPositions(){
        //given
        ShipFactory factory = new HorizontalShipFactory();
        List<Point2D> positions = Arrays.asList(new Point2D(0,0), new Point2D(1,0), new Point2D(2,0), new Point2D(3,0));

        //when
        BattleShip ship = factory.createShip(BattleShipType.FOUR_MASTED, positions);
        List<Point2D> shipProperties = ship.getShipProperties();

        //then
        verifyType(ship, BattleShipType.FOUR_MASTED, HorizontalShip.class);
        Assert.assertEquals(positions, shipProperties);
    }

    public void should_CreateHorizontalShip_WithPositions_andCheckHealth(){
        //given
        ShipFactory factory = new HorizontalShipFactory();
        List<Point2D> positions = Arrays.asList(new Point2D(0,0), new Point2D(1,0), new Point2D(2,0), new Point2D(3,0));

        //when
        BattleShip ship = factory.createShip(BattleShipType.FOUR_MASTED, positions);
        int shipsRemainingHealth = ship.getShipsRemainingHealth(ship.getShipProperties());

        //then
        verifyType(ship, BattleShipType.FOUR_MASTED, HorizontalShip.class);
        Assert.assertEquals(positions.size(), shipsRemainingHealth);

    }
    private void verifyType(BattleShip battleShip, BattleShipType battleShipType, Class<?> clazz){
        Assert.assertTrue(clazz.isInstance(battleShip));
        Assert.assertEquals(battleShipType, battleShip.getBattleShipType());
    }
}
