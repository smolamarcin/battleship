package com.epam.solid.nie.client.ui;

import com.epam.solid.nie.ships.BattleShip;
import com.epam.solid.nie.ships.ShipFactory;
import com.epam.solid.nie.utils.Point2D;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
@AllArgsConstructor
class ShipCreator {

    private final ShipFactory shipFactory;

    Ship createShip(final List<Cell> cells) {
        List<Point2D> positions = cells.stream().map(cellToPoint2D()).collect(Collectors.toList());
        return new Ship(createBattleShip(positions));
    }

    BattleShip createBattleShip(final List<Point2D> points) {
        return shipFactory.createShip(points);
    }

    private Function<Cell, Point2D> cellToPoint2D() {
        return c -> Point2D.of(c.getCellX(), c.getCellY());
    }
}
