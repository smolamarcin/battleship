package com.epam.solid.nie.client.ui;

import com.epam.solid.nie.client.communication.SocketServer;
import com.epam.solid.nie.ships.HorizontalShipFactory;
import com.epam.solid.nie.ships.VerticalShipFactory;
import com.epam.solid.nie.utils.Point2D;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.*;

import static com.epam.solid.nie.client.ui.GameScene.running;


/**
 *
 */
class ShipPlacer {
    public static final int FOUR_MAST = 4;
    public static final int THREE_MAST = 3;
    public static final int TWO_MAST = 2;
    public static final int ONE_MAST = 1;
    private ShipCreator shipCreator;
    private Board enemyBoard;
    private Board playerBoard;
    private SocketServer socketServer;
    private Queue<Integer> typesOfShips = new LinkedList<>(Arrays.asList(FOUR_MAST, THREE_MAST, THREE_MAST,
            TWO_MAST, TWO_MAST, TWO_MAST, ONE_MAST, ONE_MAST, ONE_MAST, ONE_MAST));

    ShipPlacer(final Board enemyBoard,
               final Board playerBoard,
               final SocketServer socketServer) {
        this.enemyBoard = enemyBoard;
        this.playerBoard = playerBoard;
        this.socketServer = socketServer;
    }

    EventHandler<MouseEvent> setUpPlayerShips() {
        return event -> {
            if (running) {
                return;
            }
            Cell cell = (Cell) event.getSource();
            shipOrientation(event);
            if (playerBoard.isShipPositionValid(shipCreator.createShip(produceCells(cell)), cell)) {
                typesOfShips.poll();
                if (typesOfShips.isEmpty()) {
                    socketServer.send(playerBoard.getAllPositions());
                    running = placeShipsOfEnemy(socketServer.receiveAllShips());
                }
            }
        };
    }

    private void shipOrientation(final MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            shipCreator = new ShipCreator(new VerticalShipFactory());
        } else {
            shipCreator = new ShipCreator(new HorizontalShipFactory());
        }
    }

    private List<Cell> produceCells(final Cell cell) {
        List<Cell> cells = new ArrayList<>();
        Integer poll = typesOfShips.peek();
        for (int four = poll; four > 0; four--) {
            cells.add(cell);
        }
        return cells;
    }

    boolean placeShipsOfEnemy(final String shipsString) {
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

    ShipCreator createShip(final String[] coords) {
        if (coords.length > 2 && Integer.valueOf(coords[0]).equals(Integer.valueOf(coords[2]))) {
            return new ShipCreator(new VerticalShipFactory());
        } else {
            return new ShipCreator(new HorizontalShipFactory());
        }
    }
}
