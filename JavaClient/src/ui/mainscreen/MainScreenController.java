package ui.mainscreen;

import ui.ScreenController;
import models.Message;
import models.User;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
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
import models.Client;
import models.Server;

public class MainScreenController extends ScreenController implements Initializable, Observer {

    // Setting Observable list for list view
    ObservableList<Message> listMessages = FXCollections.observableArrayList();

    ObservableList<User> listOnlineUsers = FXCollections.observableArrayList();

    private String ip;

    private final int PORT = 22;

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

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtMessage.setWrapText(true);
        listViewMessages.setItems(listMessages);
        
        setWraptextListView();
        Platform.runLater(() -> lblIpAddress.setText(ip));
        lblIpAddress.setVisible(true);
        listViewMessages.setFocusTraversable(false);
        
        btnDisconnect.setFocusTraversable(false);
        handleEnterKeyPress();

        //Create Server
        Server s = new Server(PORT);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();

    }
    //Methos to set Ip Address
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    //Method to add user to the screen
    public void addUser(User user) {
        listOnlineUsers.add(user);
    }

    //Disconnect Button
    @FXML
    private void handleDisconnectAction(ActionEvent event) {
        Stage MainScreen = (Stage) btnDisconnect.getScene().getWindow();
        MainScreen.close();
    }

    //Method to wrap text in a list view
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

    //Send Button
    @FXML
    private void handleSendAction(ActionEvent event) {
        String validator = txtMessage.getText().trim();
        if (validator.equals("")) {
            System.out.println("Mensaje vacio");
            return;
        }
        //Create Message Object
        Message message = new Message();
        message.setUser(listOnlineUsers.get(0));
        message.setMessageText(txtMessage.getText());

        //Create Client, run thread and send to the server
        Client c = new Client(ip, PORT, message.toStringNet());
        c.run();
        System.out.println(ip);
        
        //Print message on console
        System.out.println(message);
        
        //Add message to the listbox
        listMessages.add(message);
        
        //Clear Field
        txtMessage.clear();
    }

    //Method to send message pressing the Enter key
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

                //Create Message Object
                Message message = new Message();
                message.setUser(listOnlineUsers.get(0));
                message.setMessageText(txtMessage.getText());

               
                //Create Client, run thread and send to the server
                Client c = new Client(ip, PORT, message.toStringNet());
                c.run();
                System.out.println(ip);
                
                //Print message on console
                System.out.println(message);
                
                //Add message to the listbox
                listMessages.add(message);
                
                //Add message to the listbox
                txtMessage.clear();

                //Consume enter press
                event.consume();
            }
        });
    }

    //Observable method to update listbox on the other screen
    @Override
    public void update(Observable o, Object o1) {
        listMessages.add(new Message(o1.toString()));
    }
}
