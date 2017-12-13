package com.epam.solid.nie.ships;

/**
 * Created by marek on 13.12.2017.
 */
public class VerticalShip implements BattleShip {

    private BattleShipType battleShipType;

    VerticalShip(BattleShipType battleShipType) {
        this.battleShipType = battleShipType;
    }

    public BattleShipType getBattleShipType() {
        return battleShipType;
    }
}
