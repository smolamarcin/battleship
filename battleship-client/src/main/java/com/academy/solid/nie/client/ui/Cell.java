package com.academy.solid.nie.client.ui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Graphic representation of the cell on the board.
 * The class inherits from the rectangle class which is the part of Java FX.
 *
 * @since 1.0.1
 */
class Cell extends Rectangle {
    private static final int DEFAULT_WIDTH = 30;
    private static final int DEFAULT_HEIGHT = 30;
    private Point2D point2D;
    private Ship ship;

    boolean wasShot() {
        return wasShot;
    }

    private boolean wasShot;

    /**
     * Creates a cell with default parameters.
     *
     * @param point2D represents point on the Board
     */
    Cell(final Point2D point2D) {
        super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.point2D = point2D;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    /**
     * Adds the ship to the cell.
     * It changes the cell properties.
     *
     * @param newShip
     */
    void addShip(Ship newShip) {
        this.ship = newShip;
    }

    /**
     * Changes the cell color and marks it as a hit.
     * If there was a piece of a ship in the cell, it would return true.
     *
     * @return Information whether there was a part of a ship in the cell
     */
    boolean shoot() {
        wasShot = true;
        setFill(Color.BLACK);
        if (ship != null) {
            ship.hit();
            setFill(Color.RED);
            return true;
        }
        return false;
    }

    boolean isOccupied() {
        return ship != null;
    }

    /**
     * @return String representation of the object
     */
    @Override
    public String toString() {
        return point2D.getX() + "," + point2D.getY() + ",";
    }

    int getCellX() {
        return point2D.getX();
    }

    int getCellY() {
        return point2D.getY();
    }

    Ship getShip() {
        return ship;
    }
}
