package com.academy.solid.nie.client.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class Converter {
    private static final String SHIPS_SEPARATOR = ";\\|";
    private static final String COORDINATES_SEPARATOR = ";";

    private Converter() {
    }

    static List<List<Point2D>> convert(String shipsString) {
        List<List<Point2D>> ships = new ArrayList<>();
        String[] shipsArray = shipsString.split(SHIPS_SEPARATOR);
        for (String shipStr : shipsArray) {
            List<Point2D> point2DOfShip = new ArrayList<>();
            String[] coords = shipStr.split(COORDINATES_SEPARATOR);

            Arrays.stream(coords).forEach(e -> {
                String[] sth = e.split(",");
                point2DOfShip.add(Point2D.of(Integer.parseInt(sth[0]), Integer.parseInt(sth[1])));
            });

            ships.add(point2DOfShip);
        }
        return ships;
    }
}
