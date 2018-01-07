package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.client.communication.SocketServer;
import com.academy.solid.nie.ships.Type;
import com.academy.solid.nie.utils.Point2D;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 *
 */
class ShipPlacer {
    private Board enemyBoard;
    private Board playerBoard;
    private SocketServer socketServer;
    private boolean areAllShipsPlaced = false;
    private Queue<Integer> typesOfShips = new LinkedList<>(Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1));

    ShipPlacer(Board enemyBoard, Board playerBoard, SocketServer socketServer) {
        this.enemyBoard = enemyBoard;
        this.playerBoard = playerBoard;
        this.socketServer = socketServer;
    }

    EventHandler<MouseEvent> setUpPlayerShips() {
        return event -> {
            if (areAllShipsPlaced)
                return;
            Cell cell = (Cell) event.getSource();
            Type type = shipOrientation(event);
            if (playerBoard.isShipPositionValid(new Ship(producePoints(cell), type), cell)) {
                typesOfShips.poll();
                if (typesOfShips.isEmpty()) {
                    socketServer.send(playerBoard.getAllPositions());
                    placeShipsOfEnemy(socketServer.receiveAllShips());
                    areAllShipsPlaced = true;
                }
            }
        };
    }

    private List<Point2D> producePoints(Cell cell) {
        return produceCells(cell).stream().map(cellToPoint2D()).collect(Collectors.toList());
    }

    private Function<Cell, Point2D> cellToPoint2D() {
        return c -> Point2D.of(c.getCellX(), c.getCellY());
    }

    boolean areAllShipsPlaced() {
        return areAllShipsPlaced;
    }

    private Type shipOrientation(MouseEvent event) {
        return event.getButton() == MouseButton.PRIMARY ? Type.VERTICAL : Type.HORIZONTAL;
    }

    private List<Cell> produceCells(Cell cell) {
        List<Cell> cells = new ArrayList<>();
        Integer poll = typesOfShips.peek();
        for (int four = poll; four > 0; four--) {
            cells.add(cell);
        }
        return cells;
    }

    void placeShipsOfEnemy(String shipsString) {
        String[] ships = shipsString.split(",\\|");
        for (String shipStr : ships) {
            List<Point2D> point2DOfShip = new ArrayList<>();
            String[] coords = shipStr.split(",");
            for (int i = 0; i < coords.length - 1; i += 2) {
                int x = Integer.parseInt(coords[i]);
                int y = Integer.parseInt(coords[i + 1]);
                point2DOfShip.add(Point2D.of(x, y));
            }
            Ship ship = new Ship(point2DOfShip, createShip(coords));
            enemyBoard.isShipPositionValid(ship, new Cell(point2DOfShip.get(0)));
        }
    }

    Type createShip(String[] coords) {
        return coords.length > 2 && Integer.valueOf(coords[0]).equals(Integer.valueOf(coords[2]))
                ? Type.VERTICAL : Type.HORIZONTAL;
    }
}