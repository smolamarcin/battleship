package com.epam.solid.nie.client.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScene extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent start = FXMLLoader.load(getClass().getResource("/startScreen.fxml"));
        Scene scene = new Scene(start);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}