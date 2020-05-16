/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Carlos
 */
public class User {
    private String name;
    private boolean online;
    
    public User(String name){
        this.name = name;
        this.online = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getName() {
        return name;
    }

    public boolean isOnline() {
        return online;
    }
}
