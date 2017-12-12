package com.epam.solid.nie.client.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StartController {
    @FXML
    private Button btn_connect;

    @FXML
    void btn_connect_clicked(ActionEvent event) {
        System.out.println(field_ip.getText());

    }

    @FXML
    private TextField field_ip;

}