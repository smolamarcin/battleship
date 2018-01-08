package com.academy.solid.nie.client.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 */
public final class StartScene extends Application {
    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent start = FXMLLoader.load(getClass().getResource("/startScreen.fxml"));
        Scene scene = new Scene(start);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //todo:write java doc

    /**
     * @param args input
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
