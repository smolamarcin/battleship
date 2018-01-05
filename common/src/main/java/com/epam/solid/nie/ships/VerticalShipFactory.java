package com.epam.solid.nie.ships;

import com.epam.solid.nie.utils.Point2D;

import java.util.List;

/**
 * Created by marek on 13.12.2017.
 */
public class VerticalShipFactory implements ShipFactory {
    /**
     * Create an instance of vertical ship based on a list of points
     * @param positions as List of points
     * @return new instance of the vertical ship
     */
    public BattleShip createShip(final List<Point2D> positions) {
        return new VerticalShip(positions).addType();
    }
}
