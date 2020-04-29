/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchatjava;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

/**
 *
 * @author Carlos
 */
public class FXMLDocumentController implements Initializable {
    
    ObservableList<Message> messages = FXCollections.observableArrayList();
    
    @FXML
    private Label label;
    
    @FXML
    private void handleSendAction(ActionEvent event) {
        String validator = txtMessage.getText().trim();
        if(validator.equals("")){
            //System.out.println("Mensaje vacio");
            return;
        }
        Message message = new Message();
        message.setMessageText(txtMessage.getText());
        //String message = txtMessage.getText();
        
        System.out.println(message);
        messages.add(message);
        txtMessage.clear();
        
    }
    
    @FXML
    private Button btnSend;
    
    @FXML
    private ListView listMessage;
    
    @FXML
    private TextArea txtMessage;
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listMessage.setItems(messages);
    }    
    
}
