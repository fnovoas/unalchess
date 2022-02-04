/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author ASUS
 */
public class Turno {
    public static Boolean estado = true; //empieza en blancas
    //true: blancas
    //false: negras
    
    public Turno(){}
    
    public boolean getEstado(){
        return estado;
    }
    
    public void setEstado(boolean estado){
    this.estado = estado;
    }
}
