package com.academy.solid.nie.client.ui;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * represent window for display transcript of game
 */
public class WindowTranscript {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private TextArea textArea = new TextArea();

    /**
     * displays window for user
     */
    public void display(){
        StackPane secondaryLayout = new StackPane();
        textArea.setDisable(Boolean.TRUE);
        secondaryLayout.getChildren().add(textArea);
        Scene secondScene = new Scene(secondaryLayout, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Stage secondStage = new Stage();
        secondStage.sizeToScene();
        secondStage.setScene(secondScene);
        secondStage.show();
    }

    /**
     * appends string to window
     * @param msg message added to textArea in window
     */
    public void append(String msg) {
        textArea.appendText(msg);
    }
}
