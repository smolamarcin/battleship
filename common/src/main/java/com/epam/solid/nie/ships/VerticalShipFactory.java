package com.epam.solid.nie.ships;

/**
 * Created by marek on 13.12.2017.
 */
public class VerticalShipFactory implements ShipFactory {
    public BattleShip createShip(BattleShipType type) {
        return new VerticalShip(type);
    }
}
