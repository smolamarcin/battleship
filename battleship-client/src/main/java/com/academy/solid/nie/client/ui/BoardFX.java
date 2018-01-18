package com.academy.solid.nie.client.ui;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * Class representing the game board.
 * It defines what behaviors are possible on the board.
 * The board is made up of individual cells.
 * The default size of the board is 10x10.
 *
 * @since 1.0.1
 */
class BoardFX extends Parent {
    private VBox rows = new VBox();
    private final int maxHeight;
    private final int maxWidth;

    BoardFX(int maxHeight, int maxWidth) {
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }

    /**
     * Creates a board at the beginning of the game.
     * Each row is filled during iteration in a loop.
     * The loop ends when all rows are filled up.
     *
     * @param handler
     */
    void initialize(final EventHandler<? super MouseEvent> handler) {
        for (int y = 0; y < maxWidth; y++) {
            fillHorizontal(handler, y);
        }
        getChildren().add(rows);
    }

    /**
     * Fills the row with cells from left to right.
     */
    private void fillHorizontal(final EventHandler<? super MouseEvent> handler, final int y) {
        HBox row = new HBox();
        for (int x = 0; x < maxHeight; x++) {
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
     * Indicates the cell as occupied by the ship.
     * The cell color changes to white with a green border.
     *
     * @param ship  - represents single instance of Ship
     * @param point - represents single Point of the board
     */
    void markFieldAsOccupiedByShip(Ship ship, Point2D point) {
        Cell cell = getCell(point);
        cell.addShip(ship);
        cell.setFill(Color.WHITE);
        cell.setStroke(Color.GREEN);
    }

    private Cell getCell(int x, int y) {
        return (Cell) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
    }

    private Cell getCell(Point2D point) {
        int x = point.getX();
        int y = point.getY();
        return getCell(x, y);
    }

    boolean isOccupied(Point2D point) {
        return getCell(point).isOccupied();
    }

    void shoot(Point2D point) {
        Cell cellOnBoard = getCell(point);
        cellOnBoard.shoot();
    }

    void add(Ship ship, Point2D point) {
        getCell(point).addShip(ship);
    }
}
