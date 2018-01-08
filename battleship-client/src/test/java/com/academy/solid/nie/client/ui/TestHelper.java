package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.utils.Point2D;

import java.util.List;

/**
 *
 */
public interface TestHelper {

    default Ship createShipHorizontally(List<Point2D> positions){
        return new Ship(positions);
    }

}
