package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.client.communication.SocketServer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

class ShipPlacer {
    private static final String SHIPS_SEPARATOR = ",\\|";
    private static final String COORDINATES_SEPARATOR = ",";
    private static final int FOUR_MAST = 4;
    private static final int THREE_MAST = 3;
    private static final int DOUBLE_MAST = 2;
    private static final int SINGLE_MAST = 1;
    private static final int BOUND = 10;
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
                finishSetup();
            }
        };
    }

    private void finishSetup() {
        if (typesOfShips.isEmpty()) {
            socketServer.send(playerBoard.getAllPositions());
            placeShipsOfEnemy(socketServer.receiveAllShips());
            areAllShipsPlaced = true;
        }
    }

    private Ship makeShip(Cell cell, Type type) {
        return new Ship(producePoints(cell, type));
    }

    private Type shipOrientation(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            return Type.HORIZONTAL;
        } else {
            return Type.VERTICAL;
        }
    }

    private List<Point2D> producePoints(Cell cell, Type type) {
        int x = cell.getCellX();
        int y = cell.getCellY();
        return producePoints(x, y, type);
    }

    private void placeShipsOfEnemy(String shipsString) {
        String[] ships = shipsString.split(SHIPS_SEPARATOR);
        for (String shipStr : ships) {
            List<Point2D> point2DOfShip = new ArrayList<>();
            String[] coords = shipStr.split(COORDINATES_SEPARATOR);

            IntStream.range(0, coords.length).filter(i -> i % 2 == 0).forEach(index -> {
                int x = Integer.parseInt(coords[index]);
                int y = Integer.parseInt(coords[index + 1]);
                point2DOfShip.add(Point2D.of(x, y));
            });

            Ship ship = new Ship(point2DOfShip);
            enemyBoard.isShipPositionValid(ship);
        }
    }

    void placeShipsRandomly() {
        if (areAllShipsPlaced) {
            return;
        }
        while (!typesOfShips.isEmpty()) {
            List<Point2D> points = producePoints(randomNumber(), randomNumber(), randomType());
            if (playerBoard.isShipPositionValid(new Ship(points))) {
                typesOfShips.remove();
            }
        }
        finishSetup();
    }

    private int randomNumber() {
        return new Random().nextInt(BOUND);
    }

    private Type randomType() {
        return Type.values()[new Random().nextInt(Type.values().length)];
    }

    private List<Point2D> producePoints(int x, int y, Type type) {
        List<Point2D> points = new ArrayList<>();
        Integer lengthOfShip = typesOfShips.peek();
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

    boolean areAllShipsPlaced() {
        return areAllShipsPlaced;
    }
}
