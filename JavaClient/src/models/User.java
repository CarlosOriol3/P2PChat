package models;

public class User {
    
    //Attributes
    private String name;
    private boolean online;

    //Constructor
    public User(String name) {
        this.name = name;
        this.online = true;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    //Getters
    public String getName() {
        return name;
    }

    //To check if a user is online. Not yet implemented.
    public boolean isOnline() {
        return online;
    }

    @Override
    public String toString() {
        return getName();
    }
}
