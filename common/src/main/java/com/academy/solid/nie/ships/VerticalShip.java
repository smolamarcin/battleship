package com.academy.solid.nie.ships;

import com.academy.solid.nie.utils.Point2D;

import java.util.List;

/**
 * Created by marek on 13.12.2017.
 */
public class VerticalShip extends AbstractShipPositions implements BattleShip {

    VerticalShip(List<Point2D> positions) {
        setPositions(positions);
    }

    /**
     * Returns the list of fields (represents by points, which contains coordinates)
     * that are occupied by the ship.
     *
     * @return positions (as a list of points) occupied by the ship
     */
    public List<Point2D> getShipProperties() {
        return getPositions();
    }

    public int getShipsRemainingHealth(List<Point2D> positions) {
        return positions.size();
    }
}
