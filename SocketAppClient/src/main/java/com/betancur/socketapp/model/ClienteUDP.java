/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.socketapp.model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteUDP {

    private String mensaje;

    private InetAddress direccionServidor;

    private int PUERTO_SERVIDOR;

    private DatagramSocket socketUDP;

    private byte[] buffer;

    private DatagramPacket pregunta;

    private DatagramPacket peticion;

    private int timeOut;

    public String iniciarConexion(String host, int port, int timeOut) {
        try {
            this.timeOut = timeOut;

            //inicializa mensaje
            this.mensaje = "";

            //puerto del servidor
            this.PUERTO_SERVIDOR = port;
            //buffer donde se almacenara los mensajes
            this.buffer = new byte[1024];

            //Obtengo la localizacion de localhost
            this.direccionServidor = InetAddress.getByName(host);

            //Creo el socket de UDP
            this.socketUDP = new DatagramSocket();

            //Setting timeOut del socket
            //this.socketUDP.setSoTimeout(timeOut);
            this.mensaje = "¡Iniciando conexión desde el Cliente!" + "\n";
            
        } catch (UnknownHostException | SocketException ex) {
            this.mensaje = "¡ERROR de conexión desde el Cliente!" + "\n";
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.mensaje;
    }

    public String crearDatagrama() {
        //Convierto el mensaje a bytes
        this.buffer = mensaje.getBytes();
        //Creo un datagrama
        this.pregunta = new DatagramPacket(this.buffer, this.buffer.length, direccionServidor, PUERTO_SERVIDOR);
        this.mensaje = "Datagrama creado" + "\n";

        return this.mensaje;
    }

    public String enviarDatagrama() {
        try {
            //Lo envio con send            
            socketUDP.send(this.pregunta);
            this.mensaje = "Enviando Datagrama" + "\n";

        } catch (SocketException ex) {
            this.mensaje = "ERROR en Envio de Datagrama" + "\n";
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            this.mensaje = "ERROR el host no existe" + "\n";
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.mensaje;
    }

    public String ajustarTiempoDeRespuesta() {

        try {

            this.socketUDP.setSoTimeout(this.timeOut);
            this.mensaje = "tiempo ajustado de TimeOut a " + this.timeOut / 1000 + " segundos" + "\n";
            
        } catch (SocketException ex) {
            this.mensaje = "ERROR al ajustar timeOut" + "\n";
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.mensaje;
    }

    public String prepararRespuesta() {

        //Preparo la respuesta
        this.peticion = new DatagramPacket(this.buffer, this.buffer.length);
        this.mensaje = "Preparando respuesta" + "\n";

        return this.mensaje;
    }

    public String recibirRespuesta() throws InterruptedException {
        try {     
           
            //Recibo la respuesta
            socketUDP.receive(this.peticion);

            //Recibe los datos y los muestra
            this.mensaje = new String(this.peticion.getData());

        } catch (SocketException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketTimeoutException | UnknownHostException ex) {
            this.mensaje = "timeOut de "+ +timeOut/1000+ " excedido" +"\n";
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.mensaje;
    }

    public String cerrarConexion() {
        //cierro el socket
        socketUDP.close();
        this.mensaje = "Conexión del Cliente cerrada"+"\n";
        
        return this.mensaje;
    }

}
