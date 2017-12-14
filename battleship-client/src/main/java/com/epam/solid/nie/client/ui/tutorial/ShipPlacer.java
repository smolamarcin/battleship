package com.epam.solid.nie.client.ui.tutorial;

import com.epam.solid.nie.client.ui.SocketServer;
import com.epam.solid.nie.utils.Point2D;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class ShipPlacer {

    private List<Cell> cells = new ArrayList<>();
    private int shipsToPlace = 4;
    private ShipCreator shipCreator = new ShipCreator();
    private Random random = new Random();
    private Board enemyBoard;
    private Board playerBoard;
    private SocketServer socketServer;

    ShipPlacer(Board enemyBoard, Board playerBoard, SocketServer socketServer) {
        this.enemyBoard = enemyBoard;
        this.playerBoard = playerBoard;
        this.socketServer = socketServer;
    }

    EventHandler<MouseEvent> setUpPlayerShips() {
        return placeFourMastShip();
    }

    private EventHandler<MouseEvent> placeFourMastShip(){
        return event -> {
            if (running) return;
            for(int four = 4; four > 0 ; four--) {
                Cell cell = (Cell) event.getSource();
                cells.add(cell);
            }
            if (event.getButton() == MouseButton.PRIMARY) {
                Ship ship = shipCreator.createShip(cells);
                if (playerBoard.isShipPositionValid(ship, cells) && shipsToPlace-- > 0) {
                    socketServer.passAllShips(playerBoard.getAllpositions());
                    running = placeShipsRandomly();
                }
            }
            cells.clear();
        };
    }

    /**
     * There are 5 types of ships.
     * Method picks random cell to place ship.
     * Ship type is changed in every iteration
     */
    boolean placeShipsRandomly() {
        int numberOfShipTypes = 5;
        while (numberOfShipTypes > 0) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            Point2D point2D = new Point2D(x, y);
            Ship ship = new Ship(shipCreator.createBattleShip(Collections.singletonList(point2D)));
            if (enemyBoard.isShipPositionValid(ship, new Cell(point2D))) {
                numberOfShipTypes--;
            }
        }
        return true;
    }
}