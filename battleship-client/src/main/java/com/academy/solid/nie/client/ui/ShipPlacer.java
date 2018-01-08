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

    private List<Point2D> producePoints(Cell cell, Type type) {
        return produceCells(cell, type);
    }

    boolean areAllShipsPlaced() {
        return areAllShipsPlaced;
    }

    private Type shipOrientation(MouseEvent event) {
        return event.getButton() == MouseButton.PRIMARY ? Type.VERTICAL : Type.HORIZONTAL;
    }

    private List<Point2D> produceCells(Cell cell, Type type) {
        List<Point2D> points = new ArrayList<>();
        Integer lengthOfShip = typesOfShips.peek();
        int x = cell.getCellX();
        int y = cell.getCellY();
        points.add(Point2D.of(x, y));
        int lengthOfShipWithoutBeginningPoint = lengthOfShip - 1;
        for (int length = lengthOfShipWithoutBeginningPoint; length > 0; length--) {
            if (type == Type.VERTICAL) {
                x++;
            } else {
                y++;
            }
            points.add(Point2D.of(x, y));
        }
        return points;
    }

    void placeShipsOfEnemy(String shipsString) {
        String[] ships = shipsString.split(",\\|");
        for (String shipStr : ships) {
            List<Point2D> point2DOfShip = new ArrayList<>();
            String[] coords = shipStr.split(",");

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