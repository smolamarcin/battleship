package com.epam.solid.nie.client.ui.tutorial;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;

public class Board extends Parent {
    private VBox rows = new VBox();
    private boolean enemy;
    public int ships;

    public Board(boolean enemy, EventHandler<? super MouseEvent> handler) {
        this.enemy=enemy;
        for (int y = 0; y < 10; y++) {
            HBox row= new HBox();
            for (int x = 0; x < 10; x++) {
                Cell c=new Cell(x,y,this);
                c.setOnMouseClicked(handler);
                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);
    }

    public class Cell extends Rectangle {
        public int x, y;
        public Ship ship;
        public boolean wasShot = false;
        private Board board;

        public Cell(int x, int y, Board board) {
            super(30, 30);
            this.x = x;
            this.y = y;
            this.board = board;
            setFill(Color.lightGray);
            setStroke(Color.BLACK);
        }

        public boolean shoot(){
            wasShot = true;
            setFill(Color.black);
            if (ship!=null){
                ship.hit();
                setFill(Color.RED);
                if (!ship.isAlive()){
                    board.ships--;
                }
                return true;
            }
            return false;
        }


    }
}
