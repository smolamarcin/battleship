package com.epam.solid.nie.client.ui;


import com.epam.solid.nie.client.IpValidator;
import com.epam.solid.nie.client.Validator;
import com.epam.solid.nie.client.ui.tutorial.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StartSceneController {

    @FXML
    private Button btn_connect;

    @FXML
    void btn_connect_clicked(ActionEvent event) throws IOException {
        Validator ipValidator = new IpValidator();
        String ip=field_ip.getText();
        if (ipValidator.validate(ip)){
            new GameScene().start();
        }else {
            System.out.println("Wrong ip.");
        }
    }

    @FXML
    private TextField field_ip;

}