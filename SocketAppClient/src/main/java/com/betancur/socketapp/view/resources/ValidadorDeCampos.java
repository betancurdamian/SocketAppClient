package com.betancur.socketapp.view.resources;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTextField;

/**
 *
 * @author Ariel
 */
public class ValidadorDeCampos {
    
    public void validarSoloLetras(JTextField campo){
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    
    }
    
    public void validarSoloNumero(JTextField campo){
        campo.addKeyListener(new KeyAdapter() {            
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    
    }
    
    public void LimitarCaracteres(JTextField campo, int cantidad){
        campo.addKeyListener(new KeyAdapter() {            
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                int tam = campo.getText().length();
                if (tam>=cantidad) {
                    e.consume();
                }
            }
        });
    
    }
    
    public void validarNumeroDecimales(JTextField campo){
        campo.addKeyListener(new KeyAdapter() {            
            public void keyTyped(KeyEvent e){
                int k = (int)e.getKeyChar();
                if(k>=46 && k<=57){
                    if(k==46){
                        String dato = campo.getText();
                        int tamA= dato.length();
                        for(int i=0; i<=tamA; i++){
                            if(dato.contains(".")){
                                e.setKeyChar((char)KeyEvent.VK_CLEAR);
                            }
                        }
                    }
                    if(k==47){
                        e.setKeyChar((char)KeyEvent.VK_CLEAR);
                    }
                }else{
                    e.setKeyChar((char)KeyEvent.VK_CLEAR);
                    e.consume();
                }
                
                
            }
        });
    }
    public int verificarTamanioCampo(JTextField campo){
        int posicion=0;
        char caracter = (char) 0;
        for (int i = 0; i <= campo.getText().length()-1; i++) {
            caracter = campo.getText().charAt(i);
            if (caracter =='.') {
                posicion = i;
            }
        }
        return posicion;
    }
    
    /**
     * calcularEdad
     * Calcula la edad en funcion a la fechaDenacimiento ingresada
     * @param fechaDeNacimiento
     * @return 
     */
    public int calcularEdad(Date fechaDeNacimiento) {
        Calendar calendario = Calendar.getInstance(); // fecha actual
        int anioDeHoy = calendario.get(Calendar.YEAR);
        int mesDeHoy = calendario.get(Calendar.MONTH);
        int diaDeHoy = calendario.get(Calendar.DAY_OF_MONTH);
        
        calendario.setTime(fechaDeNacimiento); // fecha de nacimiento
        int anioDeNacimiento = anioDeHoy - calendario.get(Calendar.YEAR)-1;
        int mesDeNacimiento = calendario.get(Calendar.MONTH);
        
        
        if (mesDeNacimiento == mesDeHoy) { // Mismo mes
            return calendario.get(Calendar.DAY_OF_MONTH) <= diaDeHoy ? anioDeNacimiento
                    : anioDeNacimiento - 1;
        } else {
            return mesDeNacimiento < mesDeHoy ? anioDeNacimiento - 1 : anioDeNacimiento;
        }
    }
}
