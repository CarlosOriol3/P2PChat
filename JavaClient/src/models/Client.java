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
public class Client implements Runnable{

    private String hostIp;
    private int port;
    private Message mensaje;
    
    public Client(String hostIp,int port, Message mensaje) {
        this.hostIp = hostIp;
        this.port = port;
        this.mensaje = mensaje;
    }
    
    
    @Override
    public void run() {
        DataOutputStream out;
        
        try {
            Socket sc = new Socket(hostIp, port);

            //Establecer Comunicacion
            
            //para escribir
            out = new DataOutputStream(sc.getOutputStream());

            out.writeUTF(mensaje.toString());

            
            sc.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
