package com.epam.solid.nie.ships;

import com.epam.solid.nie.utils.Point2D;

import java.util.List;

/**
 * Represents a ship positioned horizontally on the board.
 */
public class HorizontalShip extends AbstractShipPositions implements BattleShip {

    private BattleShipType battleShipType;

    HorizontalShip(List<Point2D> positions) {
        setPositions(positions);
    }

    HorizontalShip addType() {
        this.battleShipType = BattleShipType.valueOf(getShipsRemainingHealth(getPositions()));
        return this;
    }

    /**
     * Returns what type of ship is there (number of masts)
     * @return
     */
    public BattleShipType getBattleShipType() {
        return battleShipType;
    }

    /**
     * Returns the list of fields (represents by points, which contains coordinates)
     * that are occupied by the ship.
     * @return
     */
    public List<Point2D> getShipProperties() {
        return getPositions();
    }

    /**
     * Returns the number of masts that have not yet been shot based on a fields occupied by the ship.
     * @param positions - list of fields occupied by the ship
     * @return
     */
    public int getShipsRemainingHealth(List<Point2D> positions) {
        return positions.size();
    }
}
