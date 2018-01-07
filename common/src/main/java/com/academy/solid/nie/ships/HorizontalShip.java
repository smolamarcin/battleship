package com.academy.solid.nie.ships;

import com.academy.solid.nie.utils.Point2D;

import java.util.List;

/**
 * Represents a ship positioned horizontally on the board.
 */
public class HorizontalShip extends AbstractShipPositions implements BattleShip {

    HorizontalShip(List<Point2D> positions) {
        setPositions(positions);
    }

    /**
     * Returns the list of fields (represents by points, which contains coordinates)
     * that are occupied by the ship.
     * @return list of points occupied by the ship
     */
    public List<Point2D> getShipProperties() {
        return getPositions();
    }
}
