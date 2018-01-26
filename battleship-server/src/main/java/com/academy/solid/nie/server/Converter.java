package com.academy.solid.nie.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

final class Converter {
    private static final String SHIPS_SEPARATOR = ";\\|";
    private static final String COORDINATES_SEPARATOR = ";";

    private Converter() {
    }

    static List<List<String>> convert(String shipsString) {
        List<List<String>> ships = new ArrayList<>();
        String[] shipsArray = shipsString.split(SHIPS_SEPARATOR);
        for (String shipStr : shipsArray) {
            ships.add(Arrays.stream(shipStr.split(COORDINATES_SEPARATOR))
                    .map(p -> p + COORDINATES_SEPARATOR).collect(Collectors.toList()));
        }
        return ships;
    }
}
