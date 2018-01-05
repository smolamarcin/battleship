package com.epam.solid.nie.client.ui;

import com.epam.solid.nie.utils.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Graphic representation of the cell on the board.
 * The class inherits from the rectange class which is the part of Java FX.
 *
 * @since 1.0.1
 */
public class Cell extends Rectangle {
    private static final int DEFAULT_WIDTH = 30;
    private static final int DEFAULT_HEIGHT = 30;
    private Point2D point2D;
    private Ship ship;
    boolean wasShot;

    /**
     * Creates a cell with default parameters.
     *
     * @param point2D represents point on the Board
     */
    public Cell(final Point2D point2D) {
        super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.point2D = point2D;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    /** Adds the ship to the cell.
     * It changes the cell properties.
     *
     * @param ship
     * @return
     */
    final Cell addShip(final Ship ship) {
        this.ship = ship;
        return this;
    }

    /** Changes the cell color and marks it as a hit.
     *  If there was a piece of a ship in the cell, it would return true.
     * @return
     */
    final boolean shoot() {
        wasShot = true;
        setFill(Color.BLACK);
        if (ship != null) {
            ship.hit(point2D);
            setFill(Color.RED);
            return true;
        }

        return false;
    }

    final boolean isOccupied() {
        return ship != null;
    }

    /**
     *
     * @return String representation of the object
     */
    @Override
    public String toString() {
        return point2D.getX() + "," + point2D.getY() + ",";
    }

    final int getCellX() {
        return point2D.getX();
    }

    final int getCellY() {
        return point2D.getY();
    }
}
