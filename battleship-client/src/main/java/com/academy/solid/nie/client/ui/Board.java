package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.utils.Point2D;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
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
     * @return true if the position of the ship is correct.
     */
    boolean isShipPositionValid(final Ship ship) {
        if (canPlaceShip(ship)) {
            allShips.add(ship);
            return placeAndMarkShip(ship);
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
     * @return true if placing the ship was completed successfully
     */
    private boolean placeAndMarkShip(final Ship ship) {
        placeShip(ship);
        markEndOfShip();
        return true;
    }

    private void placeShip(final Ship ship) {
        for (Point2D point : ship.getPositions()) {
            Cell cell = getCell(point.getX(), point.getY()).addShip(ship);
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
     */
    private boolean canPlaceShip(final Ship ship) {
        return ship.getPositions().stream().noneMatch(point2D ->
        {
            int x = point2D.getX();
            int y = point2D.getY();
            return !isInScope(x, y) || getCell(x, y).isOccupied() || isThereShipInTheNeighborhood(x, y);
        });
    }

    /**
     * Specifies whether it is possible to set the specific ship on a specific cell.
     * Specifies the ability based on specific coordinates.
     *
     * @param y
     * @param x
     * @return
     */
    private boolean isThereShipInTheNeighborhood(final int x, final int y) {
        return Arrays.stream(getNeighbors(x, y)).anyMatch(Cell::isOccupied);
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
        return allShips.stream().noneMatch(Ship::isAlive);
    }

}
