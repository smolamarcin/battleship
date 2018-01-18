package com.academy.solid.nie.client.ui;

import java.util.List;

/**
 *
 */
class Ship {
    private int health;

    List<Point2D> getPositions() {
        return positions;
    }

    private List<Point2D> positions;

    Ship(List<Point2D> positions) {
        this.positions = positions;
        this.health = getLength();
    }

    void hit() {
        health--;
    }

    boolean isAlive() {
        return health > 0;
    }

    private int getLength() {
        return positions.size();
    }
}
