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
                    //running = placeShipsRandomly();
//                     running = placeShipsRandomly(socketServer.receiveAllShips());
                    running = placeShips();
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

    List<Ship> placeShipsRandomly(String shipsString) {
        String[] ships = shipsString.split(",\\|");
        for (String shipStr : ships) {
            List<Point2D> point2DOfShip = new ArrayList<>();
            System.out.println(shipStr);
            String[] coords = shipStr.split(",");
            for (int i = 0; i < coords.length - 1; i += 2) {
                int x = Integer.valueOf(coords[i]);
                int y = Integer.valueOf(coords[i+1]);
                point2DOfShip.add(Point2D.of(x, y));
            }
            if(coords.length < 2 && Integer.valueOf(coords[0]).equals(Integer.valueOf(coords[2])))
                shipCreator = new ShipCreator(new VerticalShipFactory());
            else
                shipCreator = new ShipCreator(new HorizontalShipFactory());
            Ship ship = new Ship(shipCreator.createBattleShip(point2DOfShip));
            enemyBoard.isShipPositionValid(ship, new Cell(point2DOfShip.get(0)));
            System.out.println(Arrays.toString(coords));
        }
        return enemyBoard.allShips;
    }

    boolean placeShips() {
        List<Point2D> positions = new ArrayList<>();
        positions.add(Point2D.of(0, 1));
        positions.add(Point2D.of(0, 2));
        shipCreator = new ShipCreator(new VerticalShipFactory());
        Ship ship = new Ship(shipCreator.createBattleShip(positions));
        enemyBoard.isShipPositionValid(ship, new Cell(positions.get(0)));

        List<Point2D> positions2 = new ArrayList<>();
        shipCreator = new ShipCreator(new HorizontalShipFactory());
        positions2.add(Point2D.of(0, 7));
        positions2.add(Point2D.of(1, 7));
        ship = new Ship(shipCreator.createBattleShip(positions2));
        enemyBoard.isShipPositionValid(ship, new Cell(positions2.get(0)));
        return true;
    }
}