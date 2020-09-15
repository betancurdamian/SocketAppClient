/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.socketapp.controller;


import com.betancur.socketapp.view.JFClient;
import java.io.IOException;

/**
 *
 * @author Ariel
 */
public class Main {

    public static void main(String[] args) throws IOException {

        new JFClient().setVisible(true);

    }
}
