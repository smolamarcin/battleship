package com.academy.solid.nie.ships;

import com.academy.solid.nie.utils.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents type of the ship.
 */
public class BattleShip {
    private List<Point2D> positions = new ArrayList<>();
    private Type type;

    public BattleShip(List<Point2D> positions, Type type) {
        this.positions = positions;
        this.type = type;
    }

    /**
     * Returns the list of fields (represents by points, which contains coordinates)
     * that are occupied by the ship.
     *
     * @return positions (as a list of points) occupied by the ship
     */
    public List<Point2D> getPositions() {
        return positions;
    }

    public Type getType() {
        return type;
    }
}
