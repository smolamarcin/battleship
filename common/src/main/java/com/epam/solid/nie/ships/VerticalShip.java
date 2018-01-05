package com.epam.solid.nie.ships;

import com.epam.solid.nie.utils.Point2D;

import java.util.List;

/**
 * Created by marek on 13.12.2017.
 */
public final class VerticalShip extends AbstractShipPositions implements BattleShip {

    private BattleShipType battleShipType;

    VerticalShip(final List<Point2D> positions) {
        setPositions(positions);
    }

    VerticalShip addType() {
        this.battleShipType = BattleShipType.valueOf(getShipsRemainingHealth(getPositions()));
        return this;
    }

    /**
     * Returns what type of ship is there (number of masts)
     *
     * @return battle ship type
     */
    public BattleShipType getBattleShipType() {
        return battleShipType;
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

    public int getShipsRemainingHealth(final List<Point2D> positions) {
        return positions.size();
    }
}
