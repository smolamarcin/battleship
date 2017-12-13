package com.epam.solid.nie.ships;

/**
 * Created by marek on 13.12.2017.
 */
public class HorizontalShipFactory implements ShipFactory {
    public BattleShip createShip(BattleShipType type) {
        return new HorizontalShip(type);
    }
}
