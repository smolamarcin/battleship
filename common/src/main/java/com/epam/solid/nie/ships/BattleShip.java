package com.epam.solid.nie.ships;

/**
 * Represents type of the ship.
 */
public interface BattleShip extends BattleShipProperties, ShipHealth {
    /**
     * @return type of the ship
     */
    BattleShipType getBattleShipType();
}
