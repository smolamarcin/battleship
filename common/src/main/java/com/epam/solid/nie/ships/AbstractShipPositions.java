package com.epam.solid.nie.ships;

import com.epam.solid.nie.utils.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marek on 13.12.2017.
 */
abstract class AbstractShipPositions {
    private List<Point2D> positions = new ArrayList<Point2D>();

    List<Point2D> getPositions() {
        return positions;
    }

    void setPositions(List<Point2D> positions) {
        this.positions = positions;
    }
}
