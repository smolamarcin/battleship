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

import static java.lang.System.out;


/**
 * Class representing the game board.
 * It defines what behaviors are possible on the board.
 * The board is made up of individual cells.
 * The default size of the board is 10x10.
 *
 * @since 1.0.1
 */
class Board extends Parent {
    private static final int MAX_HEIGHT = 10;
    private static final int MAX_WIDTH = 10;
    private VBox rows = new VBox();
    private boolean enemy;
    List<Ship> allShips = new ArrayList<>();
    private static StringBuilder positions = new StringBuilder();

    Board(boolean enemy, VBox rows) {
        this.enemy = enemy;
        this.rows = rows;
    }


    Board(boolean enemy) {
        this.enemy = enemy;
    }

    /**
     * Creates a board at the beginning of the game.
     * Each row is filled during iteration in a loop.
     * The loop ends when all rows are filled up.
     *
     * @param handler
     */
    void initialize(EventHandler<? super MouseEvent> handler) {
        for (int y = 0; y < MAX_WIDTH; y++) {
            fillHorizontal(handler, y);
        }
        getChildren().add(rows);
    }

    /**
     * Fills the row with cells from left to right.
     */
    private void fillHorizontal(EventHandler<? super MouseEvent> handler, int y) {
        HBox row = new HBox();
        for (int x = 0; x < MAX_HEIGHT; x++) {
            Point2D point2D = Point2D.of(x, y);
            createCellInRow(handler, row, point2D);
        }
        rows.getChildren().add(row);
    }

    /**
     * Creates new Cell in a single row.
     */
    private void createCellInRow(EventHandler<? super MouseEvent> handler, HBox row, Point2D point2D) {
        Cell c = new Cell(point2D);
        c.setOnMouseClicked(handler);
        row.getChildren().add(c);
    }

    /**
     * Determines if you can put a ship in a given place.
     *
     * @param ship
     * @param cell
     * @return true if the position of the ship is correct.
     */
    boolean isShipPositionValid(Ship ship, Cell cell) {
        if (canPlaceShip(ship, cell)) {
            allShips.add(ship);
            return placeShip(ship, cell);
        }
        out.println(positions);
        return false;
    }

    /**
     * The method puts the ship in the given place.
     *
     * @param ship
     * @param cell
     * @return true if placing the ship was completed successfully
     */
    private boolean placeShip(Ship ship, Cell cell) {
        if (ship.getBattleShip() instanceof VerticalShip) {
            placeShipVertically(ship, cell.getCellX(), cell.getCellY());
        } else {
            placeShipHorizontally(ship, cell.getCellX(), cell.getCellY());
        }
        markEndOfShip();
        return true;
    }

    /** Places the ship on the board in a horizontal position.
     *
     * @param ship
     * @param x
     * @param y
     */
    private void placeShipHorizontally(Ship ship, int x, int y) {
        for (int i = x; i < x + ship.getRemainingHealth(); i++) {
            Cell cell = getCell(i, y).addShip(ship);
            if (!enemy) {
                markFieldAsOccupiedByShip(cell);
            }
        }
    }

    /** Places the ship on the board in a vertical position.
     *
     * @param ship
     * @param x
     * @param y
     */
    private void placeShipVertically(Ship ship, int x, int y) {
        for (int i = y; i < y + ship.getRemainingHealth(); i++) {
            Cell cell = getCell(x, i).addShip(ship);
            if (!enemy) {
                markFieldAsOccupiedByShip(cell);
            }
        }
    }

    /** Indicates the cell as occupied by the ship.
     *  The cell color changes to white with a green border.
     *
     * @param cell
     */
    private void markFieldAsOccupiedByShip(Cell cell) {
        cell.setFill(Color.WHITE);
        cell.setStroke(Color.GREEN);
        savePositionPieceOfShip(cell);
    }

    /** Adds the coordinates of a single cell to the list of positions.
     *
     * @param cell
     */
    private StringBuilder savePositionPieceOfShip(Cell cell) {
        return positions.append(cell.toString());
    }

    /** Marks the end of the ship on the list.
     *  The pipe is a convention that has been established in the team.
     */
    private StringBuilder markEndOfShip() {
        return positions.append("|");
    }

    /** Returns a cell with given coordinates.
     *
     * @param x
     * @param y
     * @return
     */
    Cell getCell(int x, int y) {
        return (Cell) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
    }

    /** Returns an array of cells that are neighbours of the specified cell.
     *
     * @param x
     * @param y
     * @return array of neighbours cells
     */
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

    /** Specifies whether it is possible to set the specific ship on a specific cell.
     *
     * @param ship
     * @param cell
     */
    boolean canPlaceShip(Ship ship, Cell cell) {
        int length = ship.getRemainingHealth();
        int x = cell.getCellX();
        int y = cell.getCellY();

        if (ship.getBattleShip() instanceof VerticalShip) {
            for (int i = y; i < y + length; i++) {
                if (!isInScope(x, i) || getCell(x, i).isOccupied()) {
                    return false;
                }
                if (canPlaceShip(i, x)) return false;
            }
        } else {
            for (int i = x; i < x + length; i++) {
                if (!isInScope(i, y) || getCell(i, y).isOccupied())
                    return false;

                if (canPlaceShip(y, i)) return false;
            }
        }
        return true;
    }

    /** Specifies whether it is possible to set the specific ship on a specific cell.
     *  Specifies the ability based on specific coordinates.
     * @param y
     * @param i
     * @return
     */
    private boolean canPlaceShip(int y, int i) {
        for (Cell neighbor : getNeighbors(i, y)) {
            if (!isInScope(i, y))
                return true;

            if (neighbor.isOccupied())
                return true;
        }
        return false;
    }

    /** Determines if the point is in the range of the board.
     *
     * @param point
     * @return
     */
    private boolean isInScope(Point2D point) {
        return isInScope(point.getX(), point.getY());
    }

    /** Determines if the coordinates are in the range of the board.
     *
     * @param x
     * @param y
     * @return
     */
    private boolean isInScope(double x, double y) {
        return x >= 0 && x < MAX_HEIGHT && y >= 0 && y < MAX_WIDTH;
    }

    /** Returns the positions of all ships on the board.
     *
     * @return
     */
    public String getAllpositions() {
        return positions.toString();
    }

    /** Returns information if all ships have been sunk.
     *
     * @return
     */
    public boolean areAllShipsSunk() {
        for (Ship ship : allShips)
            if (ship.isAlive())
                return false;
        return true;
    }

}
