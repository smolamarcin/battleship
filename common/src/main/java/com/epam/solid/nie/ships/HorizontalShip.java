package com.epam.solid.nie.ships;

import com.epam.solid.nie.utils.Point2D;

import java.util.List;

/**
 * Created by marek on 13.12.2017.
 */
public class HorizontalShip extends AbstractShipPositions implements BattleShip {

    private BattleShipType battleShipType;

    HorizontalShip(List<Point2D> positions) {
        setPositions(positions);
    }

    HorizontalShip addType(){
        this.battleShipType = BattleShipType.valueOf(getShipsRemainingHealth(getPositions()));
        return this;
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
