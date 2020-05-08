/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import models.Message;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class MainScreenController implements Initializable {

    // Setting Observable list for list view
    ObservableList<Message> messages = FXCollections.observableArrayList();

    private String ip;

    private String codename;

    @FXML
    private Label lblIpAddress;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnDisconnect;

    @FXML
    private ListView listMessage;

    @FXML
    private TextArea txtMessage;

    @FXML
    private ListView listOnlineUsers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtMessage.setWrapText(true);
        listMessage.setItems(messages);
        setWraptextListView();
        Platform.runLater(() -> lblIpAddress.setText(ip));
        lblIpAddress.setVisible(true);
        listMessage.setFocusTraversable(false);
        listOnlineUsers.setFocusTraversable(false);
        btnDisconnect.setFocusTraversable(false);
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    @FXML
    private void handleSendAction(ActionEvent event) {
        String validator = txtMessage.getText().trim();
        if (validator.equals("")) {
            // System.out.println("Mensaje vacio");
            return;
        }
        Message message = new Message();
        message.setMessageText(txtMessage.getText());
        // String message = txtMessage.getText();

        System.out.println(message);
        messages.add(message);
        txtMessage.clear();

    }

    @FXML
    private void handleDisconnectAction(ActionEvent event) {
        Stage MainScreen = (Stage) btnDisconnect.getScene().getWindow();
        MainScreen.close();
    }

    private void setWraptextListView() {
        listMessage.setCellFactory(param -> new ListCell<Message>() {
            @Override
            protected void updateItem(Message item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setMinWidth(10);
                    setMaxWidth(10);
                    setPrefWidth(10);
                    setWrapText(true);
                    setText(item.toString());
                }
            }
        });
    }
}
