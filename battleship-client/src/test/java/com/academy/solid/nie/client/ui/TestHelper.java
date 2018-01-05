package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.ships.HorizontalShipFactory;
import com.academy.solid.nie.ships.ShipFactory;
import com.academy.solid.nie.ships.VerticalShipFactory;
import com.academy.solid.nie.utils.Point2D;

import java.util.List;

/**
 *
 */
public interface TestHelper {

    default Ship createShipHorizontally(List<Point2D> positions){
        ShipFactory factory = new HorizontalShipFactory();
        return new Ship(factory.createShip(positions));
    }

    default Ship createShipVertically(List<Point2D> positions){
        ShipFactory factory = new VerticalShipFactory();
        return new Ship(factory.createShip(positions));
    }

}
