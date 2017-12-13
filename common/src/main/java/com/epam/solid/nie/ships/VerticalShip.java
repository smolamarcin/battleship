package com.epam.solid.nie.ships;

import com.epam.solid.nie.utils.Point2D;

import java.util.List;

/**
 * Created by marek on 13.12.2017.
 */
public class VerticalShip extends AbstractShipPositions implements BattleShip {

    private BattleShipType battleShipType;

    VerticalShip(BattleShipType battleShipType, List<Point2D> positions) {
        this.battleShipType = battleShipType;
        setPositions(positions);
    }
    public BattleShipType getBattleShipType() {
        return battleShipType;
    }

    public List<Point2D> getShipProperties() {
        return getPositions();
    }

    public int getShipsRemainingHealth(List<Point2D> positions) {
        return positions.size();
    }
}
