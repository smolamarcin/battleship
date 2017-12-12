package com.epam.solid.nie.client.ui;


import com.epam.solid.nie.client.IpValidator;
import com.epam.solid.nie.client.Validator;
import com.epam.solid.nie.client.ui.tutorial.BattleshipMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StartController {
    @FXML
    private Button btn_connect;

    @FXML
    void btn_connect_clicked(ActionEvent event) {
        Validator ipValidator = new IpValidator();
        String ip=field_ip.getText();
        if (ipValidator.validate(ip)){
            new BattleshipMain().start();
        }else {
            System.out.println("False");
        }

    }

    @FXML
    private TextField field_ip;

}