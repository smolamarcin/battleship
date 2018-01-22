package com.academy.solid.nie.client.ui;


import com.academy.solid.nie.client.communication.IpValidator;
import com.academy.solid.nie.client.communication.SocketServer;
import com.academy.solid.nie.client.communication.Validator;
import com.academy.solid.nie.client.language.Message;
import com.academy.solid.nie.client.language.MessageProviderImpl;
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
    private MessageProviderImpl communicateProvider;

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
                    MessageProviderImpl.getCommunicate(Message.WRONG_IP))
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
        communicateProvider = new MessageProviderImpl();
        Language language = Language.ENGLISH;
        communicateProvider.populate(language);
        languageChoice.setItems(availableLanguages);
        languageChoice.setValue(Language.ENGLISH);
    }
    private void updateFields() {
        insertIpLabel.setText(MessageProviderImpl.getCommunicate(Message.INSERT_IP));
        btnConnect.setText(MessageProviderImpl.getCommunicate(Message.CONNECT));
    }
}
