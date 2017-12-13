package com.epam.solid.nie.ships;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by marek on 13.12.2017.
 */
@Test
public class ShipFactoryTest {

    public void createHorizontalShip(){
        //given
        ShipFactory factory = new HorizontalShipFactory();

        //when
        BattleShip ship = factory.createShip(BattleShipType.FIVE_MASTED);

        //then
        verifyType(ship, BattleShipType.FIVE_MASTED, HorizontalShip.class);
    }

    public void createVerticalShip(){
        //given
        ShipFactory factory = new VerticalShipFactory();

        //when
        BattleShip ship = factory.createShip(BattleShipType.TWO_MASTED);

        //then
        verifyType(ship, BattleShipType.TWO_MASTED, VerticalShip.class);
    }


    private void verifyType(BattleShip battleShip, BattleShipType battleShipType, Class<?> clazz){
        Assert.assertTrue(clazz.isInstance(battleShip));
        Assert.assertEquals(battleShipType, battleShip.getBattleShipType());
    }
}
