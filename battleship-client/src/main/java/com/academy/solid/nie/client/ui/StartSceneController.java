package com.academy.solid.nie.client.ui;


import com.academy.solid.nie.client.communication.IpValidator;
import com.academy.solid.nie.client.communication.Validator;
import com.academy.solid.nie.client.communication.SocketServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 *  Controller to manage components of the starting window.
 *
 */
public final class StartSceneController {
    @FXML
    private ImageView logo;
    @FXML
    private Button btnConnect;

    @FXML
    void btnConnectClicked(final ActionEvent event) throws IOException {
        Validator ipValidator = new IpValidator();
        String ip = fieldIP.getText();
        if (ipValidator.validate(ip)) {
            SocketServer socketServer = new SocketServer();
            socketServer.connect(ip);
            new GameScene(socketServer).start();
        } else {
            new WindowDisplayer("Wrong IP").withButtonWhoExitThisWindow().display();
        }
    }

    @FXML
    private TextField fieldIP;

}
