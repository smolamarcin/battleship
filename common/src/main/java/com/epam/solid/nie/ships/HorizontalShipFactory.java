package com.epam.solid.nie.ships;

import com.epam.solid.nie.utils.Point2D;

import java.util.List;

/**
 * Created by marek on 13.12.2017.
 */
public class HorizontalShipFactory implements ShipFactory {
    public BattleShip createShip(List<Point2D> positions) {
        return new HorizontalShip(positions).addType();
    }
}