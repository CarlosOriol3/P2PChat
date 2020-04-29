/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchatjava;

/**
 *
 * @author Carlos
 */
import java.time.LocalDate;
import java.time.LocalTime;
public class Message {
    private String messageText;
    private LocalTime timeStamp;
    private LocalDate date;
    private String user;

    public Message() {
        this.timeStamp = LocalTime.now();
        this.date = LocalDate.now();
    }
    

    
    public String getMessageText() {
        return messageText;
    }

    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setTimeStamp(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "User: " + messageText;
    }
    
        
}


