package com.epam.solid.nie.client.ui;

import com.epam.solid.nie.ships.VerticalShip;
import com.epam.solid.nie.utils.Point2D;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 *
 */
class Board extends Parent {
    private Logger logger = Logger.getLogger(Board.class.getName());
    private static final int MAX_HEIGHT = 10;
    private static final int MAX_WIDTH = 10;
    private VBox rows = new VBox();
    private boolean enemy;
    private List<Ship> allShips = new ArrayList<>();
    private static StringBuilder positions = new StringBuilder();

    Board(boolean enemy, VBox rows) {
        this.enemy = enemy;
        this.rows = rows;
    }


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
            Point2D point2D = Point2D.of(x,y);
            createCellInRow(handler, row, point2D);
        }
        rows.getChildren().add(row);
    }

    private void createCellInRow(EventHandler<? super MouseEvent> handler, HBox row, Point2D point2D) {
        Cell c = new Cell(point2D);
        c.setOnMouseClicked(handler);
        row.getChildren().add(c);
    }

    boolean isShipPositionValid(Ship ship, Cell cell) {
        if (canPlaceShip(ship, cell)) {
            allShips.add(ship);
            return placeShip(ship, cell);
        }
        logger.info(positions.toString());
        return false;
    }

    private boolean placeShip(Ship ship, Cell cell) {
        if (ship.getBattleShip() instanceof VerticalShip) {
            placeShipVertically(ship, cell.getCellX(), cell.getCellY());
        } else {
            placeShipHorizontally(ship, cell.getCellX(), cell.getCellY());
        }
        markEndOfShip();
        return true;
    }

    private void placeShipHorizontally(Ship ship, int x, int y) {
        for (int i = x; i < x + ship.getRemainingHealth(); i++) {
            Cell cell = getCell(i, y).addShip(ship);
            if (!enemy) {
                markFieldAsOccupiedByShip(cell);
            }
        }
    }

    private void placeShipVertically(Ship ship, int x, int y) {
        for (int i = y; i < y + ship.getRemainingHealth(); i++) {
            Cell cell = getCell(x, i).addShip(ship);
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


    Cell getCell(int x, int y) {
        return (Cell) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
    }

    private Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[]{
                Point2D.of(x - 1, y),
                Point2D.of(x + 1, y),
                Point2D.of(x, y - 1),
                Point2D.of(x, y + 1),
                Point2D.of(x - 1, y - 1),
                Point2D.of(x + 1, y - 1),
                Point2D.of(x - 1, y + 1),
                Point2D.of(x + 1, y + 1)
        };

        List<Cell> neighbors = new ArrayList<>();

        for (Point2D p : points) {
            if (isInScope(p)) {
                neighbors.add(getCell(p.getX(), p.getY()));
            }
        }

        return neighbors.toArray(new Cell[0]);
    }

    boolean canPlaceShip(Ship ship, Cell cell) {
        int length = ship.getRemainingHealth();
        int x = cell.getCellX();
        int y = cell.getCellY();

        if (ship.getBattleShip() instanceof VerticalShip) {
            if (handleVerticalShip(length, x, y)) return false;
        } else {
            if (handleHorizontalShip(length, x, y)) return false;
        }
        return true;
    }

    private boolean handleHorizontalShip(int length, int x, int y) {
        for (int i = x; i < x + length; i++) {
            if (!isInScope(i, y) || getCell(i, y).isOccupied())
                return true;

            if (canPlaceShip(y, i)) return true;
        }
        return false;
    }

    private boolean handleVerticalShip(int length, int x, int y) {
        for (int i = y; i < y + length; i++) {
            if (!isInScope(x, i) || getCell(x, i).isOccupied()) {
                return true;
            }
            if (canPlaceShip(i, x)) return true;
        }
        return false;
    }

    private boolean canPlaceShip(int y, int i) {
        for (Cell neighbor : getNeighbors(i, y)) {
            if (!isInScope(i, y))
                return true;

            if (neighbor.isOccupied())
                return true;
        }
        return false;
    }

    private boolean isInScope(Point2D point) {
        return isInScope(point.getX(), point.getY());
    }

    private boolean isInScope(double x, double y) {
        return x >= 0 && x < MAX_HEIGHT && y >= 0 && y < MAX_WIDTH;
    }

    String getAllpositions() {
        return positions.toString();
    }

    boolean areAllShipsSunk() {
        for (Ship ship : allShips)
            if(ship.isAlive())
                return false;
        return true;
    }

}