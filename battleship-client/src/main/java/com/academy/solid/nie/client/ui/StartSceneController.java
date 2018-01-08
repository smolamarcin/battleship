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

//todo: write javadoc

/**
 *
 */
public final class StartSceneController {
    private static final int DEFAULT_WIDTH = 200;
    private static final int DEFAULT_HEIGHT = 100;
    @FXML
    private ImageView logo;
    @FXML
    private Button btnConnect;

    @FXML
    void btnConnectClicked(final ActionEvent event) throws IOException {
        Validator ipValidator = new IpValidator();
        String ip = fieldIp.getText();
        if (ipValidator.validate(ip)) {
            SocketServer socketServer = new SocketServer();
            socketServer.connect(ip);
            new GameScene(socketServer).start();
        } else {
            Label label = new Label("Wrong IP");
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(label);
            Scene secondScene = new Scene(secondaryLayout, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            Stage secondStage = new Stage();
            secondStage.setScene(secondScene);
            secondStage.show();
        }
    }

    @FXML
    private TextField fieldIp;

}
