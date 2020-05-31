/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

/**
 *
 * @author Carlos
 */
public class Server extends Observable implements Runnable {

    private int port;
    
    ServerSocket server = null;
    Socket sc = null;
    
    public Server(int port){
        this.port = port; 
    }
    
    @Override
    public void run() {
        
        try {
            server = new ServerSocket(port);
            
            System.out.println("Server init");
            
            while(true){
                //Se queda esperando
                sc = server.accept();
                System.out.println("Client Connected");
                //Establecer Comunicacion
                //Para recibir
                DataInputStream in = new DataInputStream(sc.getInputStream());
                
                //leer lo que envia el cliente
                String message = in.readUTF();
                
                //imprimir mensaje 
                System.out.println(message);
                
                //Metodos de observable
                this.setChanged();
                this.notifyObservers(message);
                this.clearChanged();
                
                //Cerrar el cliente 
                sc.close();
                System.out.println("Client Disconnect");
            }
        } catch (IOException ex) {
            System.out.println("Exception: "+ex);
        }
    }
    
}
