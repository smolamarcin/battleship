package com.epam.solid.nie.client.ui.tutorial;

import javafx.scene.Parent;
import lombok.Getter;

@Getter
class Ship extends Parent {
    private int length;
    boolean vertical;
    private int health;

    Ship(int length, boolean vertical) {
        this.length = length;
        this.vertical = vertical;
        health = length;
    }

    void hit() {
        health--;
    }

    boolean isAlive() {
        return health > 0;
    }

    int getLength() {
        return length;
    }
}
