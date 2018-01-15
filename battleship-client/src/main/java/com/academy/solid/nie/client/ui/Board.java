package com.academy.solid.nie.client.ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

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
class Board {
    private static final Logger LOGGER = Logger.getLogger(Board.class.getName());
    private static final int MAX_HEIGHT = 10;
    private static final int MAX_WIDTH = 10;
    private BoardFX boardFX;
    private boolean enemy;
    private List<Ship> allShips = new ArrayList<>();
    private static StringBuilder positions = new StringBuilder();

    BoardFX getBoardFX() {
        return boardFX;
    }

    Board(final boolean enemy, final VBox rows) {
        this.enemy = enemy;
        this.boardFX = new BoardFX(rows, MAX_HEIGHT, MAX_WIDTH);
    }


    Board(final boolean enemy) {
        this.enemy = enemy;
        this.boardFX = new BoardFX(MAX_HEIGHT, MAX_WIDTH);
    }

    /**
     * Creates a board at the beginning of the game.
     * Each row is filled during iteration in a loop.
     * The loop ends when all rows are filled up.
     *
     * @param handler
     */
    void initialize(EventHandler<? super MouseEvent> handler) {
        boardFX.initialize(handler);
    }

    /**
     * Determines if you can put a ship in a given place.
     *
     * @param ship - represents single instance of Ship
     * @return true if the position of the ship is correct.
     */
    boolean isShipPositionValid(Ship ship) {
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
    private boolean placeAndMarkShip(Ship ship) {
        placeShip(ship);
        markEndOfShip();
        return true;
    }

    private void placeShip(Ship ship) {
        for (Point2D point : ship.getPositions()) {
            boardFX.add(ship, point);
            if (!enemy) {
                markFieldAsOccupiedByShip(ship, point);
            }
        }
    }

    /**
     * Indicates the cell as occupied by the ship.
     * The cell color changes to white with a green border.
     *
     * @param ship - represents single instance of Ship
     * @param point - represents single Point of the board
     */
    private void markFieldAsOccupiedByShip(Ship ship, Point2D point) {
        boardFX.markFieldAsOccupiedByShip(ship, point);
        savePositionPieceOfShip(point);
    }

    /**
     * Adds the coordinates of a single cell to the list of positions.
     *
     * @param point - represents single Cell of the board
     */
    private void savePositionPieceOfShip(final Point2D point) {
        positions.append(point.toString());
    }

    /**
     * Marks the end of the ship on the list.
     * The pipe is a convention that has been established in the team.
     */
    private void markEndOfShip() {
        positions.append("|");
    }

    /**
     * Returns an array of cells that are neighbours of the specified cell.
     *
     * @param point - represents single Point of the board
     * @return array of neighbours cells
     */
    private ArrayList<Point2D> getAllNeighborsOf(Point2D point) {
        int x = point.getX();
        int y = point.getY();
        Point2D[] array = new Point2D[]{
                Point2D.of(x - 1, y),
                Point2D.of(x + 1, y),
                Point2D.of(x, y - 1),
                Point2D.of(x, y + 1),
                Point2D.of(x - 1, y - 1),
                Point2D.of(x + 1, y - 1),
                Point2D.of(x - 1, y + 1),
                Point2D.of(x + 1, y + 1)
        };
        return new ArrayList<>(Arrays.asList(array));
    }

    /**
     * Determines whether you can place the ship on a specific cell.
     *
     * @param ship - represents single instance of Ship
     */
    private boolean canPlaceShip(final Ship ship) {
        return ship.getPositions().stream().noneMatch(point2D -> !isInScope(point2D) || boardFX.isOccupied(point2D) || isThereShipInTheNeighborhood(point2D));
    }

    /**
     * Specifies whether it is possible to set the specific ship on a specific cell.
     * Specifies the ability based on specific coordinates.
     *
     * @param point - represents single Point of the board
     * @return
     */
    private boolean isThereShipInTheNeighborhood(Point2D point) {
        return getAllNeighborsOf(point).stream().filter(this::isInScope).anyMatch(p -> boardFX.isOccupied(p));
    }

    /**
     * Determines if the point is in the range of the board.
     *
     * @param point - contains coordinates (x - horizontal, y - vertical)
     * @return true - if point is in the board scope
     */
    private boolean isInScope(Point2D point) {
        double x = point.getX();
        double y = point.getY();
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

    void makeMoves(List<Point2D> points) {
        for (Point2D point : points) {
            boardFX.shoot(point);
        }
    }
}
