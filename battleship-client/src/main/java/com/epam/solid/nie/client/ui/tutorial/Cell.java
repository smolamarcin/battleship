package com.epam.solid.nie.client.ui.tutorial;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    private static final int DEFAULT_WIDTH = 30;
    private static final int DEFAULT_HEIGHT = 30;
    int x;
    int y;
    Ship ship = null;
    boolean wasShot = false;

    private Board board;

    public Cell(int x, int y, Board board) {
        super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    public boolean shoot() {
        wasShot = true;
        setFill(Color.BLACK);

        if (ship != null) {
            ship.hit();
            setFill(Color.RED);
            if (!ship.isAlive()) {
                board.ships--;
            }
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return " ("+x+" "+ y+") ";
    }
}
