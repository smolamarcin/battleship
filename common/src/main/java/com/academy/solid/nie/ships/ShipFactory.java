package com.academy.solid.nie.ships;

import com.academy.solid.nie.utils.Point2D;

import java.util.List;

/**
 * Created by marek on 13.12.2017.
 */
public interface ShipFactory {
    BattleShip createShip(List<Point2D> positions);
}
