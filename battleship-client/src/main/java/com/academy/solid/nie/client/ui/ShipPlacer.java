package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.client.communication.SocketServer;
import com.academy.solid.nie.ships.HorizontalShipFactory;
import com.academy.solid.nie.ships.VerticalShipFactory;
import com.academy.solid.nie.utils.Point2D;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.*;


/**
 *
 */
class ShipPlacer {
    private ShipCreator shipCreator;
    private Board enemyBoard;
    private Board playerBoard;
    private SocketServer socketServer;
    private Queue<Integer> typesOfShips = new LinkedList<>(Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1));

    ShipPlacer(Board enemyBoard, Board playerBoard, SocketServer socketServer) {
        this.enemyBoard = enemyBoard;
        this.playerBoard = playerBoard;
        this.socketServer = socketServer;
    }

    EventHandler<MouseEvent> setUpPlayerShips() {
        return event -> {
            if (GameScene.running)
                return;
            Cell cell = (Cell) event.getSource();
            shipOrientation(event);
            if (playerBoard.isShipPositionValid(shipCreator.createShip(produceCells(cell)), cell)) {
                typesOfShips.poll();
                if (typesOfShips.isEmpty()) {
                    socketServer.send(playerBoard.getAllPositions());
                    GameScene.running = placeShipsOfEnemy(socketServer.receiveAllShips());
                }
            }
        };
    }

    private void shipOrientation(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            shipCreator = new ShipCreator(new VerticalShipFactory());
        } else {
            shipCreator = new ShipCreator(new HorizontalShipFactory());
        }
    }

    private List<Cell> produceCells(Cell cell) {
        List<Cell> cells = new ArrayList<>();
        Integer poll = typesOfShips.peek();
        for (int four = poll; four > 0; four--) {
            cells.add(cell);
        }
        return cells;
    }

    boolean placeShipsOfEnemy(String shipsString) {
        String[] ships = shipsString.split(",\\|");
        for (String shipStr : ships) {
            List<Point2D> point2DOfShip = new ArrayList<>();
            String[] coords = shipStr.split(",");
            for (int i = 0; i < coords.length - 1; i += 2) {
                int x = Integer.parseInt(coords[i]);
                int y = Integer.parseInt(coords[i + 1]);
                point2DOfShip.add(Point2D.of(x, y));
            }
            shipCreator = createShip(coords);
            Ship ship = new Ship(shipCreator.createBattleShip(point2DOfShip));
            enemyBoard.isShipPositionValid(ship, new Cell(point2DOfShip.get(0)));
        }
        return true;
    }

    ShipCreator createShip(String[] coords) {
        if (coords.length > 2 && Integer.valueOf(coords[0]).equals(Integer.valueOf(coords[2])))
            return new ShipCreator(new VerticalShipFactory());
        else
            return new ShipCreator(new HorizontalShipFactory());
    }
}