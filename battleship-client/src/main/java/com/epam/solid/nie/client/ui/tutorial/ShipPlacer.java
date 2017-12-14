package com.epam.solid.nie.client.ui.tutorial;

import com.epam.solid.nie.client.ui.SocketServer;
import com.epam.solid.nie.utils.Point2D;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.*;

class ShipPlacer {

    private List<Cell> cells = new ArrayList<>();
    private ShipCreator shipCreator = new ShipCreator();
    private Random random = new Random();
    private Board enemyBoard;
    private Board playerBoard;
    private SocketServer socketServer;
    private Queue<Integer> typesOfShips = new LinkedList<>(Arrays.asList(4,3,3,2,2,2,1,1,1,1));

    ShipPlacer(Board enemyBoard, Board playerBoard, SocketServer socketServer) {
        this.enemyBoard = enemyBoard;
        this.playerBoard = playerBoard;
        this.socketServer = socketServer;
    }

    EventHandler<MouseEvent> setUpPlayerShips() {
        return event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if(typesOfShips.isEmpty()){
                    return;
                }
                int poll = typesOfShips.peek();
                for(int j = poll ;j > 0 ;j --) {
                    Cell cell = (Cell) event.getSource();
                    cells.add(cell);
                }
                Ship ship = shipCreator.createShip(cells);
                if (playerBoard.isShipPositionValid(ship, cells)) {
                    typesOfShips.poll();
                    if(typesOfShips.isEmpty()){
                        socketServer.passAllShips(playerBoard.getAllpositions());
                    }
                }
            }
            cells.clear();
        };
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
            Point2D point2D = new Point2D(x, y);
            Ship ship = new Ship(shipCreator.createBattleShip(Collections.singletonList(point2D)));
            if (enemyBoard.isShipPositionValid(ship, new Cell(point2D))) {
                numberOfShipTypes--;
            }
        }
        return true;
    }
}