package com.academy.solid.nie.client.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * This class displays a window containing only one button that displays the provided message
 */
class WindowDisplayer {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 100;
    private final Button button;
    private Stage secondStage = new Stage();

    /**
     * Constructor which creates Button with provided message on it
     *
     * @param message represents message
     */
    WindowDisplayer(String message) {
        button = new Button();
        button.setText(message);
    }

    /**
     * Creates a button for exiting system after being pressed
     *
     * @return This object
     */
    WindowDisplayer withButtonWhoExitSystem() {
        button.setOnAction(e -> System.exit(0));
        return this;
    }

    /**
     * Creates a button to exit window on which this Button is placed
     *
     * @return This object
     */
    WindowDisplayer withButtonWhoExitThisWindow() {
        button.setOnAction(e -> button.getScene().getWindow().hide());
        return this;
    }

    WindowDisplayer withTitle(String title) {
        secondStage.setTitle(title);
        return this;
    }

    /**
     * Displays window with only one button with message and behaviour provided earlier
     */
    void display() {
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(button);
        Scene secondScene = new Scene(secondaryLayout, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        secondStage.sizeToScene();
        secondStage.setScene(secondScene);
        secondStage.show();
    }
}
