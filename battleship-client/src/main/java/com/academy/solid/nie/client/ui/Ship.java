package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.utils.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
class Ship {
    private int health;

    public List<Point2D> getPositions() {
        return positions;
    }

    private List<Point2D> positions = new ArrayList<>();

    Ship(List<Point2D> positions) {
        this.positions = positions;
        this.health = getLength();
    }

    void hit(Point2D point2D) {
        positions.remove(point2D);
        health--;
    }

    boolean isAlive() {
        return health != 0;
    }

    int getLength() {
        return positions.size();
    }
}
