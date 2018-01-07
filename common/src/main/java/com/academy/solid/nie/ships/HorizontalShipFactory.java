package com.academy.solid.nie.ships;

import com.academy.solid.nie.utils.Point2D;

import java.util.List;

/**
 * Created by marek on 13.12.2017.
 */
public class HorizontalShipFactory implements ShipFactory {
    /**
     * Create an instance of horizontal ship based on a list of points
     * @param positions
     * @return new instance of the Horizontal ship
     */
    public BattleShip createShip(List<Point2D> positions) {
        return new HorizontalShip(positions);
    }
}
