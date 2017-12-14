package com.epam.solid.nie.client.ui;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Board extends Parent {
    private static final int MAX_HEIGHT = 10;
    private static final int MAX_WIDTH = 10;
    private VBox rows = new VBox();
    private boolean enemy;
    public int ships = 5;
    private static StringBuilder positions = new StringBuilder();

    Board(boolean enemy) {
        this.enemy = enemy;
    }


    void initialize(EventHandler<? super MouseEvent> handler) {
        for (int y = 0; y < MAX_WIDTH; y++) {
            fillHorizontal(handler, y);
        }
        getChildren().add(rows);
    }

    private void fillHorizontal(EventHandler<? super MouseEvent> handler, int y) {
        HBox row = new HBox();
        for (int x = 0; x < MAX_HEIGHT; x++) {
            createCellInRow(handler, y, row, x);
        }
        rows.getChildren().add(row);
    }

    private void createCellInRow(EventHandler<? super MouseEvent> handler, int y, HBox row, int x) {
        Cell c = new Cell(x, y, this);
        c.setOnMouseClicked(handler);
        row.getChildren().add(c);
    }

    public boolean isShipPositionValid(Ship ship, Cell cell) {
        if (canPlaceShip(ship, cell)) {
            return placeShip(ship, cell);
        }
        System.out.println(positions);
        return false;
    }

    private boolean placeShip(Ship ship, Cell cell) {
        if (ship.vertical) {
            placeShipVertically(ship, cell.getCellX(), cell.getCellY());
        } else {
            placeShipHorizontally(ship, cell.getCellX(), cell.getCellY());
        }
        markEndOfShip();
        return true;
    }

    private void placeShipHorizontally(Ship ship, int x, int y) {
        for (int i = x; i < x + ship.getLength(); i++) {
            Cell cell = getCell(i, y);
            cell.ship = ship;
            if (!enemy) {
                markFieldAsOccupiedByShip(cell);
            }
        }
    }

    private void placeShipVertically(Ship ship, int x, int y) {
        for (int i = y; i < y + ship.getLength(); i++) {
            Cell cell = getCell(x, i);
            cell.ship = ship;
            if (!enemy) {
                markFieldAsOccupiedByShip(cell);
            }
        }
    }

    private void markFieldAsOccupiedByShip(Cell cell) {
        cell.setFill(Color.WHITE);
        cell.setStroke(Color.GREEN);
        savePositionPieceOfShip(cell);
    }

    private StringBuilder savePositionPieceOfShip(Cell cell) {
        return positions.append(cell.toString());
    }

    private StringBuilder markEndOfShip() {
        return positions.append("|");
    }


    public Cell getCell(int x, int y) {
        return (Cell) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
    }

    private Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[]{
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1),
                new Point2D(x - 1, y - 1),
                new Point2D(x + 1, y - 1),
                new Point2D(x - 1, y + 1),
                new Point2D(x + 1, y + 1)
        };

        List<Cell> neighbors = new ArrayList<>();

        for (Point2D p : points) {
            if (isInScope(p)) {
                neighbors.add(getCell((int) p.getX(), (int) p.getY()));
            }
        }

        return neighbors.toArray(new Cell[0]);
    }

    public boolean canPlaceShip(Ship ship, Cell cell) {
        int length = ship.getLength();
        int x = cell.getCellX();
        int y = cell.getCellY();

        if (ship.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!isInScope(x, i) || getCell(x, i).isOccupied()) {
                    return false;
                }

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isInScope(x, i))
                        return false;

                    if (neighbor.isOccupied())
                        return false;
                }
            }
        } else {
            for (int i = x; i < x + length; i++) {
                if (!isInScope(i, y) || getCell(i, y).isOccupied())
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isInScope(i, y))
                        return false;

                    if (neighbor.isOccupied())
                        return false;
                }
            }
        }
        return true;
    }

    private boolean isInScope(Point2D point) {
        return isInScope(point.getX(), point.getY());
    }

    private boolean isInScope(double x, double y) {
        return x >= 0 && x < MAX_HEIGHT && y >= 0 && y < MAX_WIDTH;
    }

    public String getAllpositions() {
        return positions.toString();
    }


}