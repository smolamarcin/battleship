package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.ships.Type;
import com.academy.solid.nie.utils.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
class Ship {
    private int health;
    private List<Point2D> positions = new ArrayList<>();
    private Type type;

    Ship(List<Point2D> positions, Type type) {
        this.positions = positions;
        this.type = type;
        this.health = getLength();
    }

    Type getType() {
        return type;
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