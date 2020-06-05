/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Carlos
 */

//Implements the Runnable Interface for thread
public class Client implements Runnable{

    //Attributes
    private String hostIp;
    private int port;
    private String mensaje;
    
    //Constructor
    public Client(String hostIp,int port, String mensaje) {
        this.hostIp = hostIp;
        this.port = port;
        this.mensaje = mensaje;
    }
    
    
    //Run Method
    @Override
    public void run() { 
        try {
            Socket sc = new Socket(hostIp, port);

            //Create Connection
  
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            out.writeUTF(mensaje);

            //Close Connection
            sc.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
