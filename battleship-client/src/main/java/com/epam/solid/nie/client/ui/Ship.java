package com.epam.solid.nie.client.ui;

import com.epam.solid.nie.ships.BattleShip;
import com.epam.solid.nie.utils.Point2D;
import javafx.scene.Parent;
import lombok.Getter;

/**
 *
 */
@Getter
class Ship extends Parent {
    private int health;
    private BattleShip battleShip;

    Ship(final BattleShip battleShip) {
        this.battleShip = battleShip;
        this.health = getRemainingHealth();
    }

    void hit(final Point2D point2D) {
        battleShip.getShipProperties().remove(point2D);
        health--;
    }

    boolean isAlive() {
        return health != 0;
    }

    int getRemainingHealth() {
        return battleShip.getShipsRemainingHealth(battleShip.getShipProperties());
    }
}
