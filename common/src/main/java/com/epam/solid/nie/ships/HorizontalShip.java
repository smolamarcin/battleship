package com.epam.solid.nie.ships;

/**
 * Created by marek on 13.12.2017.
 */
public class HorizontalShip implements BattleShip {

    private BattleShipType battleShipType;

    HorizontalShip(BattleShipType battleShipType) {
        this.battleShipType = battleShipType;
    }

    public BattleShipType getBattleShipType() {
        return battleShipType;
    }

}
