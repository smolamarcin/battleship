package com.epam.solid.nie.client.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent start = FXMLLoader.load(getClass().getResource("/startScreen.fxml"));
        primaryStage.setScene(new Scene(start));
        primaryStage.show();
        Stage anotherStage = new Stage();
        Parent game = FXMLLoader.load(getClass().getResource("/gameScreen.fxml"));
        anotherStage.setScene(new Scene(game));
        anotherStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }



}
