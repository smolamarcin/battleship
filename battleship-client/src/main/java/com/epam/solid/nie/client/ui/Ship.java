package com.epam.solid.nie.client.ui;

import javafx.scene.Parent;

public class Ship extends Parent {
    private int length;
    public boolean vertical;

    private int health;

    Ship(int length, boolean vertical) {
        this.length = length;
        this.vertical = vertical;
        health = length;
    }

    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getLength() {
        return length;
    }
}
