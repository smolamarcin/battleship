package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.client.communication.SocketServer;
import com.academy.solid.nie.utils.Point2D;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.*;
import java.util.stream.IntStream;


/**
 *
 */
class ShipPlacer {
    private static final int FOUR_MAST = 4;
    private static final int THREE_MAST = 3;
    private static final int DOUBLE_MAST = 2;
    private static final int SINGLE_MAST = 1;
    private Board enemyBoard;
    private Board playerBoard;
    private SocketServer socketServer;
    private boolean areAllShipsPlaced = false;
    private Queue<Integer> typesOfShips = new LinkedList<>(Arrays.asList(FOUR_MAST, THREE_MAST, THREE_MAST,
            DOUBLE_MAST, DOUBLE_MAST, DOUBLE_MAST, SINGLE_MAST, SINGLE_MAST, SINGLE_MAST, SINGLE_MAST));

    ShipPlacer(Board enemyBoard, Board playerBoard, SocketServer socketServer) {
        this.enemyBoard = enemyBoard;
        this.playerBoard = playerBoard;
        this.socketServer = socketServer;
    }

    EventHandler<MouseEvent> setUpPlayerShips() {
        return event -> {
            if (areAllShipsPlaced) {
                return;
            }
            Cell cell = (Cell) event.getSource();
            Type type = shipOrientation(event);
            if (playerBoard.isShipPositionValid(makeShip(cell, type))) {
                typesOfShips.poll();
                if (typesOfShips.isEmpty()) {
                    socketServer.send(playerBoard.getAllPositions());
                    placeShipsOfEnemy(socketServer.receiveAllShips());
                    areAllShipsPlaced = true;
                }
            }
        };
    }

    private Ship makeShip(Cell cell, Type type) {
        return new Ship(producePoints(cell, type));
    }

    boolean areAllShipsPlaced() {
        return areAllShipsPlaced;
    }

    private Type shipOrientation(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            return Type.HORIZONTAL;
        } else {
            return Type.VERTICAL;
        }
    }

    private List<Point2D> producePoints(Cell cell, Type type) {
        List<Point2D> points = new ArrayList<>();
        Integer lengthOfShip = typesOfShips.peek();
        int x = cell.getCellX();
        int y = cell.getCellY();
        points.add(Point2D.of(x, y));
        int lengthOfShipWithoutBeginningPoint = lengthOfShip - 1;
        for (int length = lengthOfShipWithoutBeginningPoint; length > 0; length--) {
            if (type == Type.HORIZONTAL) {
                x++;
            } else {
                y++;
            }
            points.add(Point2D.of(x, y));
        }
        return points;
    }

    private void placeShipsOfEnemy(String shipsString) {
        String shipsSeparator = ",\\|";
        String cooridatesSeparator = ",";
        String[] ships = shipsString.split(shipsSeparator);
        for (String shipStr : ships) {
            List<Point2D> point2DOfShip = new ArrayList<>();
            String[] coords = shipStr.split(cooridatesSeparator);

            IntStream.range(0, coords.length).filter(i -> i % 2 == 0).forEach(index -> {
                int x = Integer.parseInt(coords[index]);
                int y = Integer.parseInt(coords[index + 1]);
                point2DOfShip.add(Point2D.of(x, y));
            });

            Ship ship = new Ship(point2DOfShip);
            enemyBoard.isShipPositionValid(ship);
        }
    }
}
