package ui.mainscreen;

import ui.ScreenController;
import models.Message;
import models.User;
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
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class MainScreenController extends ScreenController implements Initializable {

    // Setting Observable list for list view
    ObservableList<Message> listMessages = FXCollections.observableArrayList();

    ObservableList<User> listOnlineUsers = FXCollections.observableArrayList();

    private String ip;

    @FXML
    private Label lblIpAddress;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnDisconnect;

    @FXML
    private ListView<Message> listViewMessages;

    @FXML
    private TextArea txtMessage;

    @FXML
    private ListView<User> listViewOnlineUsers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtMessage.setWrapText(true);
        listViewMessages.setItems(listMessages);
        listViewOnlineUsers.setItems(listOnlineUsers);
        setWraptextListView();
        Platform.runLater(() -> lblIpAddress.setText(ip));
        lblIpAddress.setVisible(true);
        listViewMessages.setFocusTraversable(false);
        listViewOnlineUsers.setFocusTraversable(false);
        btnDisconnect.setFocusTraversable(false);
        handleEnterKeyPress();

    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void addUser(User user) {
        listOnlineUsers.add(user);
    }

    @FXML
    private void handleSendAction(ActionEvent event) {
        String validator = txtMessage.getText().trim();
        if (validator.equals("")) {
            System.out.println("Mensaje vacio");
            return;
        }
        Message message = new Message();
        message.setUser(listOnlineUsers.get(0));
        message.setMessageText(txtMessage.getText());
        System.out.println(message);
        listMessages.add(message);
        txtMessage.clear();

    }

    @FXML
    private void handleDisconnectAction(ActionEvent event) {
        Stage MainScreen = (Stage) btnDisconnect.getScene().getWindow();
        MainScreen.close();
    }

    private void setWraptextListView() {
        listViewMessages.setCellFactory(param -> new ListCell<Message>() {
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

    private void handleEnterKeyPress() {
        txtMessage.setOnKeyPressed(event -> {
            if (event.isShiftDown() && event.getCode() == KeyCode.ENTER) {
                txtMessage.appendText("\n");
            } else if (event.getCode() == KeyCode.ENTER) {
                // type here what you want
                String validator = txtMessage.getText().trim();
                if (validator.equals("")) {
                    // System.out.println("Mensaje vacio");
                    event.consume();
                    return;
                }
                Message message = new Message();
                message.setUser(listOnlineUsers.get(0));
                message.setMessageText(txtMessage.getText());
                // String message = txtMessage.getText();

                System.out.println(message);
                listMessages.add(message);
                txtMessage.clear();
                event.consume();
            }
        });
    }
}
