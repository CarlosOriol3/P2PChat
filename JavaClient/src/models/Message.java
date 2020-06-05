package models;

//Import Libraries
import java.time.LocalDate;
import java.time.LocalTime;

public class Message {

    //Attributes
    private String messageText;
    private LocalTime timeStamp;
    private LocalDate date;
    private User user;

    // Constructor
    public Message() {
        this.timeStamp = LocalTime.now();
        this.date = LocalDate.now();
    }
    
    //Other Constructor
    public Message(String msg) {
        this.timeStamp = LocalTime.now();
        this.date = LocalDate.now();
        this.user = new User(msg.split("\u0004")[0]);
        this.messageText = msg.split("\u0004")[1].trim();
    }

    
    public String getMessageText() {
        return messageText;
    }

    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
        return user.toString() + ": " + messageText;
    }

    //To string method to send to a server
    public String toStringNet() {
        return user.toString() + "\u0004" + messageText;
    }
}
