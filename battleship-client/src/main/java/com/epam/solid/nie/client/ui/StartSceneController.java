package com.epam.solid.nie.client.ui;


import com.epam.solid.nie.client.communication.IpValidator;
import com.epam.solid.nie.client.communication.Validator;
import com.epam.solid.nie.client.communication.SocketServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartSceneController {

    @FXML
    private Button btn_connect;

    @FXML
    void btn_connect_clicked(ActionEvent event) throws IOException {
        Validator ipValidator = new IpValidator();
        String ip = field_ip.getText();
        if (ipValidator.validate(ip)) {
            SocketServer socketServer = new SocketServer();
            boolean whichPlayer = socketServer.connect(ip);
            new GameScene(socketServer, whichPlayer).start();
        } else {
            Label label=new Label("Wrong ip");
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(label);
            Scene secondScene = new Scene(secondaryLayout,200,100);
            Stage secondStage=new Stage();
            secondStage.setScene(secondScene);
            secondStage.show();
        }
    }

    @FXML
    private TextField field_ip;

}