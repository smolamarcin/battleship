package com.epam.solid.nie.client.ui;


import com.epam.solid.nie.client.communication.IpValidator;
import com.epam.solid.nie.client.communication.Validator;
import com.epam.solid.nie.client.communication.SocketServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.logging.Logger;

public class StartSceneController {
    private Logger logger = Logger.getLogger(StartSceneController.class.getName());

    @FXML
    private Button btn_connect;

    @FXML
    private TextField field_ip;

    @FXML
    void btn_connect_clicked(ActionEvent event) throws IOException {
        Validator ipValidator = new IpValidator();
        String ip = field_ip.getText();
        if (ipValidator.validate(ip)) {
            SocketServer socketServer = new SocketServer();
            boolean whichPlayer = socketServer.connect(ip);
            new GameScene(socketServer, whichPlayer).start();
        } else {
            logger.info("Wrong ip.");
        }
    }

}