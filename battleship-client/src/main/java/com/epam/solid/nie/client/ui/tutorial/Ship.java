package com.epam.solid.nie.client.ui.tutorial;

import javafx.scene.Parent;

public class Ship extends Parent {
    private int length;
    public boolean vertical;

    private int health;

    Ship(int length, boolean vertical) {
        this.length = length;
        this.vertical = vertical;
        health = length;

        /*VBox vbox = new VBox();
        for (int i = 0; i < type; i++) {
            Rectangle square = new Rectangle(30, 30);
            square.setFill(null);
            square.setStroke(Color.BLACK);
            vbox.getChildren().add(square);
        }
        getChildren().add(vbox);*/
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
