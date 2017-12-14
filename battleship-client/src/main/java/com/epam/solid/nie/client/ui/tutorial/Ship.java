package com.epam.solid.nie.client.ui.tutorial;

import com.epam.solid.nie.ships.BattleShip;
import com.epam.solid.nie.utils.Point2D;
import javafx.scene.Parent;
import lombok.Getter;

@Getter
class Ship extends Parent {
    private BattleShip battleShip;

    Ship(BattleShip battleShip) {
        this.battleShip = battleShip;
    }

    void hit(Point2D point2D) {
        battleShip.getShipProperties().remove(point2D);
    }

    boolean isAlive() {
        return !(battleShip.getShipProperties().isEmpty());
    }

    int getRemainingHealth() {
        return battleShip.getShipsRemainingHealth(battleShip.getShipProperties());
    }
}