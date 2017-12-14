package com.epam.solid.nie.client.ui;


import com.epam.solid.nie.client.communication.IpValidator;
import com.epam.solid.nie.client.communication.Validator;
import com.epam.solid.nie.client.communication.SocketServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StartSceneController {

    @FXML
    private Button btn_connect;
    //TODO:
    //imie przeakaze do konstruktora
    //ip
    //rozstawienie statkow (jako string)
    //pozycje po kazdym ruchu jako string
    @FXML
    void btn_connect_clicked(ActionEvent event) throws IOException {
        Validator ipValidator = new IpValidator();
        String ip=field_ip.getText();
        if (ipValidator.validate(ip)){
            SocketServer socketServer = new SocketServer();
            if (socketServer.canConnect(ip)){
                socketServer.connect(ip);
                new GameScene(socketServer).start();
            }
        }else {
            System.out.println("Wrong ip.");
        }
    }

    @FXML
    private TextField field_ip;

}