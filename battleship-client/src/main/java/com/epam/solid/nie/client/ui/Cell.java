package com.epam.solid.nie.client.ui;

import com.epam.solid.nie.utils.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 *
 */
public class Cell extends Rectangle {
    private static final int DEFAULT_WIDTH = 30;
    private static final int DEFAULT_HEIGHT = 30;
    private Point2D point2D;
    private Ship ship;
    boolean wasShot;

    public Cell(Point2D point2D) {
        super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.point2D = point2D;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    Cell addShip(Ship ship){
        this.ship = ship;
        return this;
    }

    boolean shoot() {
        wasShot = true;
        setFill(Color.BLACK);
        if (ship != null) {
            ship.hit(point2D);
            setFill(Color.RED);
            return true;
        }

        return false;
    }
    boolean isOccupied(){
        return ship!=null;
    }

    @Override
    public String toString() {
        return point2D.getX()+","+ point2D.getY()+",";
    }

    int getCellX() {
        return point2D.getX();
    }

    int getCellY() {
        return point2D.getY();
    }
}
