package com.epam.solid.nie.client.ui.tutorial;

import com.epam.solid.nie.ships.BattleShip;
import com.epam.solid.nie.ships.HorizontalShipFactory;
import com.epam.solid.nie.ships.ShipFactory;
import com.epam.solid.nie.utils.Point2D;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class ShipCreator {

    private ShipFactory shipFactory;

    Ship createShip(List<Cell> cells) {
        List<Point2D> positions = cells.stream().map(cellToPoint2D()).collect(Collectors.toList());
        return new Ship(createBattleShip(positions));
    }

    BattleShip createBattleShip(List<Point2D> points) {
        shipFactory = new HorizontalShipFactory();
        return shipFactory.createShip(points);
    }

    private Function<Cell, Point2D> cellToPoint2D() {
        return c -> new Point2D(c.getCellX(), c.getCellY());
    }
}
