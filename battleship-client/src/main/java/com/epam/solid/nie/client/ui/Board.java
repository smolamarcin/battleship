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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class representing the game board.
 * It defines what behaviors are possible on the board.
 * The board is made up of individual cells.
 * The default size of the board is 10x10.
 *
 * @since 1.0.1
 */
class Board extends Parent {
    private static final Logger LOGGER = Logger.getLogger(Board.class.getName());
    private static final int MAX_HEIGHT = 10;
    private static final int MAX_WIDTH = 10;
    private VBox rows = new VBox();
    private boolean enemy;
    private List<Ship> allShips = new ArrayList<>();
    private static StringBuilder positions = new StringBuilder();

    Board(final boolean enemy, final VBox rows) {
        this.enemy = enemy;
        this.rows = rows;
    }


    Board(final boolean enemy) {
        this.enemy = enemy;
    }

    /**
     * Creates a board at the beginning of the game.
     * Each row is filled during iteration in a loop.
     * The loop ends when all rows are filled up.
     *
     * @param handler
     */
    void initialize(final EventHandler<? super MouseEvent> handler) {
        for (int y = 0; y < MAX_WIDTH; y++) {
            fillHorizontal(handler, y);
        }
        getChildren().add(rows);
    }

    /**
     * Fills the row with cells from left to right.
     */
    private void fillHorizontal(final EventHandler<? super MouseEvent> handler, final int y) {
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
    private void createCellInRow(final EventHandler<? super MouseEvent> handler,
                                 final HBox row,
                                 final Point2D point2D) {
        Cell c = new Cell(point2D);
        c.setOnMouseClicked(handler);
        row.getChildren().add(c);
    }

    /**
     * Determines if you can put a ship in a given place.
     *
     * @param ship - represents single instance of Ship
     * @param cell - represents single Cell of the board
     * @return true if the position of the ship is correct.
     */
    boolean isShipPositionValid(final Ship ship, final Cell cell) {
        if (canPlaceShip(ship, cell)) {
            allShips.add(ship);
            return placeShip(ship, cell);
        }
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(positions.toString());
        }
        return false;
    }

    /**
     * The method puts the ship in the given place.
     *
     * @param ship - represents single instance of Ship
     * @param cell - represents single Cell of the board
     * @return true if placing the ship was completed successfully
     */
    private boolean placeShip(final Ship ship, final Cell cell) {
        if (ship.getBattleShip() instanceof VerticalShip) {
            placeShipVertically(ship, cell.getCellX(), cell.getCellY());
        } else {
            placeShipHorizontally(ship, cell.getCellX(), cell.getCellY());
        }
        markEndOfShip();
        return true;
    }

    /**
     * Places the ship on the board in a horizontal position.
     *
     * @param ship - represents single instance of Ship
     * @param x    - the horizontal coordinate of the field on which the ship will be placed
     * @param y    - the vertical coordinate of the field on which the ship will be placed
     */
    private void placeShipHorizontally(final Ship ship, final int x, final int y) {
        for (int i = x; i < x + ship.getRemainingHealth(); i++) {
            Cell cell = getCell(i, y).addShip(ship);
            if (!enemy) {
                markFieldAsOccupiedByShip(cell);
            }
        }
    }

    /**
     * Places the ship on the board in a vertical position.
     *
     * @param ship - represents single instance of Ship
     * @param x    - the horizontal coordinate of the field on which the ship will be placed
     * @param y    - the vertical coordinate of the field on which the ship will be placed
     */
    private void placeShipVertically(final Ship ship, final int x, final int y) {
        for (int i = y; i < y + ship.getRemainingHealth(); i++) {
            Cell cell = getCell(x, i).addShip(ship);
            if (!enemy) {
                markFieldAsOccupiedByShip(cell);
            }
        }
    }

    /**
     * Indicates the cell as occupied by the ship.
     * The cell color changes to white with a green border.
     *
     * @param cell - represents single Cell of the board
     */
    private void markFieldAsOccupiedByShip(final Cell cell) {
        cell.setFill(Color.WHITE);
        cell.setStroke(Color.GREEN);
        savePositionPieceOfShip(cell);
    }

    /**
     * Adds the coordinates of a single cell to the list of positions.
     *
     * @param cell - represents single Cell of the board
     */
    private void savePositionPieceOfShip(final Cell cell) {
        positions.append(cell.toString());
    }

    /**
     * Marks the end of the ship on the list.
     * The pipe is a convention that has been established in the team.
     */
    private void markEndOfShip() {
        positions.append("|");
    }

    /**
     * Returns a cell with given coordinates.
     *
     * @param x - the horizontal coordinate of the cell
     * @param y - the vertical coordinate of the cell
     * @return cell with the desired coordinates
     */
    Cell getCell(final int x, final int y) {
        return (Cell) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
    }

    /**
     * Returns an array of cells that are neighbours of the specified cell.
     *
     * @param x - the horizontal coordinate of the cell whose neighbors we want to find
     * @param y - the vertical coordinate of the cell whose neighbors we want to find
     * @return array of neighbours cells
     */
    private Cell[] getNeighbors(final int x, final int y) {
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

    /**
     * Determines whether you can place the ship on a specific cell.
     *
     * @param ship - represents single instance of Ship
     * @param cell - represents single Cell of the board, the cell on which we want to place the ship
     */
    private boolean canPlaceShip(final Ship ship, final Cell cell) {
        int length = ship.getRemainingHealth();
        int x = cell.getCellX();
        int y = cell.getCellY();

        if (ship.getBattleShip() instanceof VerticalShip) {
            for (int i = y; i < y + length; i++) {
                if (!isInScope(x, i) || getCell(x, i).isOccupied() || canPlaceShip(i, x)) {
                    return false;
                }
            }
        } else {
            for (int i = x; i < x + length; i++) {
                if (!isInScope(i, y) || getCell(i, y).isOccupied() || canPlaceShip(y, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Specifies whether it is possible to set the specific ship on a specific cell.
     * Specifies the ability based on specific coordinates.
     *
     * @param y
     * @param i
     * @return
     */
    private boolean canPlaceShip(final int y, final int i) {
        for (Cell neighbor : getNeighbors(i, y)) {
            if (!isInScope(i, y)) {
                return true;
            }
            if (neighbor.isOccupied()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the point is in the range of the board.
     *
     * @param point - contains coordinates (x - horizontal, y - vertical)
     * @return true - if point is in the board scope
     */
    private boolean isInScope(final Point2D point) {
        return isInScope(point.getX(), point.getY());
    }

    /**
     * Determines if the coordinates are in the range of the board.
     *
     * @param x - the horizontal coordinate of the cell
     * @param y - the vertical coordinate of the cell
     * @return
     */
    private boolean isInScope(final double x, final double y) {
        return x >= 0 && x < MAX_HEIGHT && y >= 0 && y < MAX_WIDTH;
    }

    /**
     * Returns the positions of all ships on the board.
     *
     * @return position of all ships as a String
     */
    String getAllPositions() {
        return positions.toString();
    }

    /**
     * Returns information whether all ships have been sunk.
     *
     * @return true - if all ships have been sunk
     */
    boolean areAllShipsSunk() {
        for (Ship ship : allShips) {
            if (ship.isAlive()) {
                return false;
            }
        }
        return true;
    }

}
