/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.socketapp.controller;

import com.betancur.socketapp.model.ClienteTCP;
import com.betancur.socketapp.model.ClienteUDP;
import com.betancur.socketapp.view.JPClient;
import java.awt.Color;
import java.io.IOException;

/**
 *
 * @author Ariel
 */
public class ControllerDayTime {

    JPClient vista;

    public ControllerDayTime(JPClient vista) {
        this.vista = vista;
    }

    public void conecta(String host, int port, int timeOut, int seleccion) throws IOException, InterruptedException {

        if (seleccion == 1) {
            ClienteTCP clienteTCP = new ClienteTCP();
            vista.getJtp_consol().setEditable(true);
            vista.appendToPane(vista.getJtp_consol(), clienteTCP.iniciarConexion(host, port), Color.blue);
            vista.appendToPane(vista.getJtp_consol(), clienteTCP.crearFlujos(), Color.blue);
            vista.appendToPane(vista.getJtp_consol(), clienteTCP.enviarDatosAlServidor(), Color.blue);
            vista.appendToPane(vista.getJtp_consol(), clienteTCP.recibirDatosDelServidor(), Color.blue);

            vista.getJtp_consol().setEditable(false);
        } else {
            ClienteUDP clienteUDP = new ClienteUDP();
            vista.getJtp_consol().setEditable(true);
            vista.appendToPane(vista.getJtp_consol(), clienteUDP.iniciarConexion(host, port, timeOut), Color.blue);
            vista.appendToPane(vista.getJtp_consol(), clienteUDP.crearDatagrama(), Color.blue);
            vista.appendToPane(vista.getJtp_consol(), clienteUDP.enviarDatagrama(), Color.blue);
            vista.appendToPane(vista.getJtp_consol(), clienteUDP.ajustarTiempoDeRespuesta(), Color.blue);
            vista.appendToPane(vista.getJtp_consol(), clienteUDP.prepararRespuesta(), Color.blue);
            vista.appendToPane(vista.getJtp_consol(), clienteUDP.recibirRespuesta(), Color.blue);
            vista.appendToPane(vista.getJtp_consol(), clienteUDP.cerrarConexion(), Color.blue);

            vista.getJtp_consol().setEditable(false);

        }

    }
}
