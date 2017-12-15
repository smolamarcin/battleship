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


class ShipPlacer {
    private ShipCreator shipCreator;
    private Random random = new Random();
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
            if (running)
                return;
            Cell cell = (Cell) event.getSource();
            shipOrientation(event);
            if (playerBoard.isShipPositionValid(shipCreator.createShip(produceCells(cell)), cell)) {
                typesOfShips.poll();
                if (typesOfShips.isEmpty()) {
                    socketServer.passAllShips(playerBoard.getAllpositions());
                    //todo: method to place ships is called here!!!!!!
                    running = placeShipsRandomly();
                }
            }
        };
    }

    private void shipOrientation(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY) {
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


    /**
     * There are 5 types of typesOfShips.
     * Method picks random cell to place ship.
     * Ship type is changed in every iteration
     */
    boolean placeShipsRandomly() {
        int numberOfShipTypes = 5;
        while (numberOfShipTypes > 0) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            Point2D point2D = Point2D.of(x, y);
            Ship ship = new Ship(shipCreator.createBattleShip(Collections.singletonList(point2D)));
            if (enemyBoard.isShipPositionValid(ship, new Cell(point2D))) {
                numberOfShipTypes--;
            }
        }
        return true;
    }
    //todo: dominik
}