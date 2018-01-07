package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.ships.BattleShip;
import com.academy.solid.nie.ships.Type;
import com.academy.solid.nie.utils.Point2D;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
class ShipCreator {
    private Type type;

    ShipCreator(Type vertical) {
        this.type = vertical;
    }

    Ship createShip(List<Cell> cells) {
        List<Point2D> positions = cells.stream().map(cellToPoint2D()).collect(Collectors.toList());
        return new Ship(createBattleShip(positions));
    }

    BattleShip createBattleShip(List<Point2D> points) {
        return new BattleShip(points, type);
    }

    private Function<Cell, Point2D> cellToPoint2D() {
        return c -> Point2D.of(c.getCellX(), c.getCellY());
    }
}
