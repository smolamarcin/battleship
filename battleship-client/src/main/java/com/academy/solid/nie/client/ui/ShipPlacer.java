package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.client.communication.SocketServer;
import com.academy.solid.nie.client.output.Output;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.*;
import java.util.concurrent.Semaphore;

class ShipPlacer {
    private static final int FOUR_MAST = 4;
    private static final int THREE_MAST = 3;
    private static final int DOUBLE_MAST = 2;
    private static final int SINGLE_MAST = 1;
    private static final int BOUND = 10;
    private Board enemyBoard;
    private Board playerBoard;
    private SocketServer socketServer;
    private Semaphore myTurn;
    private boolean firstPlayer;
    private Semaphore waitForSending;
    private Output output;
    private boolean areAllShipsPlaced = false;
    private Queue<Integer> typesOfShips = new LinkedList<>(Arrays.asList(FOUR_MAST, THREE_MAST, THREE_MAST,
            DOUBLE_MAST, DOUBLE_MAST, DOUBLE_MAST, SINGLE_MAST, SINGLE_MAST, SINGLE_MAST, SINGLE_MAST));

    ShipPlacer(Board enemyBoard, Board playerBoard, SocketServer socketServer, Semaphore myTurn, boolean firstPlayer, Semaphore waitForSending, Output output) {
        this.enemyBoard = enemyBoard;
        this.playerBoard = playerBoard;
        this.socketServer = socketServer;
        this.myTurn = myTurn;
        this.firstPlayer = firstPlayer;
        this.waitForSending = waitForSending;
        this.output = output;
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
            placeEnemyShips(Converter.convert(socketServer.receiveAllShips()));
            areAllShipsPlaced = true;
            if (firstPlayer) {
                myTurn.release();
                output.send("Your turn.");
            } else {
                waitForSending.release();
                output.send("Your opponent turn.");
            }
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

    private void placeEnemyShips(List<List<Point2D>> shipsString) {
        for (List<Point2D> shipAsPoints : shipsString) {
            Ship ship = new Ship(shipAsPoints);
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
