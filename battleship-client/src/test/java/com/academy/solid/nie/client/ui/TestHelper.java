package com.academy.solid.nie.client.ui;

import java.util.List;

/**
 *
 */
public interface TestHelper {

    default Ship createShipHorizontally(List<Point2D> positions){
        return new Ship(positions);
    }

}
