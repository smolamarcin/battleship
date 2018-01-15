package com.academy.solid.nie.client.ui;


import com.academy.solid.nie.client.communication.IpValidator;
import com.academy.solid.nie.client.communication.Validator;
import com.academy.solid.nie.client.communication.SocketServer;
import com.academy.solid.nie.client.language.Communicate;
import com.academy.solid.nie.client.language.CommunicateProviderImpl;
import com.academy.solid.nie.client.language.Language;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;


/**
 * Controller to manage components of the starting window.
 */
public final class StartSceneController {
    @FXML
    private ImageView logo;
    @FXML
    private Button btnConnect;
    @FXML
    private Label insertIpLabel;
    @FXML
    private TextField fieldIP;
    @FXML
    private ChoiceBox<Language> languageChoice;
    private ObservableList<Language> availableLanguages = FXCollections.
            observableArrayList(Language.POLISH, Language.ENGLISH);
    private CommunicateProviderImpl communicateProvider;
    private Language language;

    @FXML
    void initialize() {
        initializeLanguageMenu();
    }

    @FXML
    void btnConnectClicked(final ActionEvent event) throws IOException {
        Validator ipValidator = new IpValidator();
        String ip = fieldIP.getText();
        if (ipValidator.validate(ip)) {
            SocketServer socketServer = new SocketServer();
            socketServer.connect(ip);
            new GameScene(socketServer).start();
        } else {
            WindowDisplayer wrongIpWindow = new WindowDisplayer(
                    communicateProvider.getCommunicate(Communicate.WRONG_IP))
                    .withButtonWhoExitThisWindow();
            wrongIpWindow.display();
        }
    }

    @FXML
    void languageChosen(ActionEvent actionEvent) {
        communicateProvider.populate(languageChoice.getValue());
        updateFields();
    }
    private void initializeLanguageMenu() {
        communicateProvider = new CommunicateProviderImpl();
        language = Language.ENGLISH;
        communicateProvider.populate(language);
        languageChoice.setItems(availableLanguages);
        languageChoice.setValue(Language.ENGLISH);
    }
    private void updateFields() {
        insertIpLabel.setText(communicateProvider.getCommunicate(Communicate.INSERT_IP));
        btnConnect.setText(communicateProvider.getCommunicate(Communicate.CONNECT));
    }
}
