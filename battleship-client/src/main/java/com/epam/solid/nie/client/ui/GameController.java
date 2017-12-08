package com.epam.solid.nie.client.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameController {
    @FXML
    private Button btn_connect;

    @FXML
    void btn_connect_clicked(ActionEvent event) {
        System.out.println("game engine");
    }

}
