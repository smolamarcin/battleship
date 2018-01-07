package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.ships.BattleShip;
import com.academy.solid.nie.utils.Point2D;

/**
 *
 */
class Ship {
    private int health;
    private BattleShip battleShip;

    Ship(BattleShip battleShip) {
        this.battleShip = battleShip;
        this.health = getRemainingHealth();
    }

    void hit(Point2D point2D) {
        battleShip.getPositions().remove(point2D);
        health--;
    }

    boolean isAlive() {
        return health != 0;
    }

    int getRemainingHealth() {
        return battleShip.getPositions().size();
    }

    BattleShip getBattleShip() {
        return battleShip;
    }
}