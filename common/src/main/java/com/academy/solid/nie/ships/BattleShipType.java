package com.academy.solid.nie.ships;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marek on 13.12.2017.
 */
@AllArgsConstructor
@Getter
enum BattleShipType {
    FIVE_MASTED(5),
    FOUR_MASTED(4),
    THREE_MASTED(3),
    TWO_MASTED(2),
    ONE_MASTED(1);

    private int size;
    private static Map<Integer, BattleShipType> map = new HashMap<>();

    static {
        for (BattleShipType pageType : BattleShipType.values()) {
            map.put(pageType.size, pageType);
        }
    }

    /**
     * Based on the size of the ship (number of masts), it returns a specific type of ship.
     * @param size
     * @return specific type of ship
     */
    public static BattleShipType valueOf(int size) {
        return map.get(size);
    }

}
