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
    private Button btnConnect;

    @FXML
    private TextField fieldIP;

    @FXML
    void btnConnectClicked(ActionEvent event) throws IOException {
        Validator ipValidator = new IpValidator();
        String ip = fieldIP.getText();
        if (ipValidator.validate(ip)) {
            SocketServer socketServer = new SocketServer();
            boolean whichPlayer = socketServer.connect(ip);
            new GameScene(socketServer, whichPlayer).start();
        } else {
            logger.info("Wrong ip.");
        }
    }

}