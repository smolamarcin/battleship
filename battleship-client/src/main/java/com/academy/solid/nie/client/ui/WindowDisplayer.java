package com.academy.solid.nie.client.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Class which purpose is to display window with only one button with provided message on that button
 */
public class WindowDisplayer {
    private static final int DEFAULT_WIDTH = 200;
    private static final int DEFAULT_HEIGHT = 100;
    private final Button button;

    /**
     * Constructor which create Button with provided message on it
     *
     * @param message represents message
     */
    public WindowDisplayer(String message) {
        button = new Button();
        button.setText(message);
    }

    /**
     * Make Button to exit system after being pressed
     *
     * @return This object
     */
    public WindowDisplayer withButtonWhoExitSystem() {
        button.setOnAction(e -> System.exit(0));
        return this;
    }

    /**
     * Make Button to exit window on which this Button is placed
     *
     * @return This object
     */
    public WindowDisplayer withButtonWhoExitThisWindow() {
        button.setOnAction(e -> button.getScene().getWindow().hide());
        return this;
    }

    /**
     * Displays window with only one button with message and behaviour provided earlier
     */
    public void display() {
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(button);
        Scene secondScene = new Scene(secondaryLayout, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.show();
    }
}
