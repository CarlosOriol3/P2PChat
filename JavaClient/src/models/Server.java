/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

//Import Libraries
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

/**
 *
 * @author Carlos
 */

//Implements the Runnable interface for thread
public class Server extends Observable implements Runnable {
    
    //Attribute
    private int port;
    
    //Inicialize Sockets
    ServerSocket server = null;
    Socket sc = null;
    
    //Constructor
    public Server(int port){
        this.port = port; 
    }
    
    //Run method form Interface
    @Override
    public void run() {
        
        try {
            server = new ServerSocket(port);
            
            //Notify to the console that the server is running
            System.out.println("Server init");
            
            while(true){
                //Waits until there is a Connection or throws exception
                sc = server.accept();
                
                //Notify in the console when there is a Connection
                System.out.println("Client Connected");
                
                //Recieve the information from the client
                DataInputStream in = new DataInputStream(sc.getInputStream());
                
                //Read the message
                String message = in.readUTF();
                
                //Print the message on the console
                System.out.println(message);
                
                //Observable Methods
                this.setChanged();
                this.notifyObservers(message);
                this.clearChanged();
                
                //Close Client
                sc.close();
                //Notify the console when client disconnects
                System.out.println("Client Disconnect");
            }
        } catch (IOException ex) {
            System.out.println("Exception: "+ex);
        }
    }
    
}
