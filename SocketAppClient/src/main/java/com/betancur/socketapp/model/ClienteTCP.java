/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.socketapp.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteTCP {

    private String HOST;
    private int PUERTO;
    private DataInputStream in;
    private DataOutputStream out;

    private Socket sc;

    private String mensaje;

    public String iniciarConexion(String host, int port) {
        try {
            //Host del servidor
            this.HOST = host;
            //Puerto del servidor
            this.PUERTO = port;

            //Creo el socket para conectarme con el cliente
            this.sc = new Socket(HOST, PUERTO);
            this.mensaje = "¡Iniciando conexión desde el Cliente!" + "\n";
        } catch (IOException ex) {
            this.mensaje = "¡ERROR de conexión desde el Cliente!" + "\n";
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mensaje;
    }

    public String crearFlujos() {
        try {
            this.in = new DataInputStream(sc.getInputStream());
            this.out = new DataOutputStream(sc.getOutputStream());
            this.mensaje = "Flujos de entrada y salida creados" + "\n";
        } catch (IOException ex) {
            this.mensaje = "¡ERROR al crear flujos de entrada y salida!" + "\n";
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }
    
    public String enviarDatosAlServidor() {
        try {
            //Envio un mensaje al cliente
            this.out.writeUTF("¡Hola mundo desde el cliente!");
            
            this.mensaje = "Enviando datos al servidor" + "\n";
        } catch (IOException ex) {
            this.mensaje = "¡ERROR al enviar datos al servidor!" + "\n";
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }
    
    public String recibirDatosDelServidor() {
        try {
            //Recibo el mensaje del servidor
            this.mensaje = in.readUTF();
            
        } catch (IOException ex) {
            this.mensaje = "¡ERROR al recibir datos del servidor!" + "\n";
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }
    
//    public ClienteTCP(String host, int port) {
//        //Host del servidor
//        this.HOST = host;
//        //Puerto del servidor
//        this.PUERTO = port;
//
//        try {
//            //Creo el socket para conectarme con el cliente
//            this.sc = new Socket(HOST, PUERTO);
//
//            this.in = new DataInputStream(sc.getInputStream());
//            this.out = new DataOutputStream(sc.getOutputStream());
//
//            //Envio un mensaje al cliente
//            this.out.writeUTF("¡Hola mundo desde el cliente!");
//
//            //Recibo el mensaje del servidor
//            String mensaje = in.readUTF();
//
//            System.out.println(mensaje);
//
//            this.sc.close();
//
//        } catch (IOException ex) {
//            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
