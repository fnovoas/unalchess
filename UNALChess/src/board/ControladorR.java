/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package board;
import static board.Tablero_1.JPanel_Turno;
import static board.Tablero_1.Tiempo_negras;
import static board.Tablero_1.Tiempo_blancas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Lenovo
 */
public class ControladorR implements ActionListener {

    
    public static boolean enrroqueReyA = true;
    public static boolean enrroqueTorreIzquierdaA = true;
    public static boolean enrroqueTorreDerechaA = true;
    public static boolean enrroqueReyB = true;
    public static boolean enrroqueTorreIzquierdaB = true;
    public static boolean enrroqueTorreDerechaB = true;

    public static String[][] tablero = new String[8][8];
    /*public char turnoJugador = 'b'; */ 
    Turno turnoJugador = new Turno(); //determina el turno
    
    int peonN=0;
    int caballoN=0;
    int torreN=0;
    int alfilN=0;
    int reinaN=0;
    int peonB=0;
    int caballoB=0;
    int torreB=0;
    int alfilB=0;
    int reinaB=0;
    int peonNA=0;
    int caballoNA=0;
    int torreNA=0;
    int alfilNA=0;
    int reinaNA=0;
    int peonBA=0;
    int caballoBA=0;
    int torreBA=0;
    int alfilBA=0;
    int reinaBA=0;
    
    private String posicionAntigua = null;
    private String posicionNueva = null;
    private String posicionActual;
    public static String fichaElegida;
    public static ImageIcon imagenElegida;
    Movimientos movimientos;
    public boolean jaqueMate=false;
    
    public ControladorR(){
        iniciarTablero();

        
        Tablero_1 vista= new Tablero_1(); 
        vista.setVisible(true);
      //*  turnos();
        colorTablero();
        añadirActionEvents();
        movimientos = new Movimientos();
        tiempo_b = new Timer(1, acciones_b);
        tiempo_n = new Timer(1, acciones_n);        
    }
     
    public void iniciarTablero(){
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                tablero [i][j]="";
            }
        }
        for (int i=0; i<8; i++){
            tablero [1][i]="n_peon";
            tablero [6][i]="b_peon";
        }
        tablero [0][0]="n_torre";
        tablero [0][1]="n_caballo";
        tablero [0][2]="n_alfil";
        tablero [0][3]="n_reina";
        tablero [0][4]="n_rey";
        tablero [0][5]="n_alfil";
        tablero [0][6]="n_caballo";
        tablero [0][7]="n_torre";
        
        tablero [7][0]="b_torre";
        tablero [7][1]="b_caballo";
        tablero [7][2]="b_alfil";
        tablero [7][3]="b_reina";
        tablero [7][4]="b_rey";
        tablero [7][5]="b_alfil";
        tablero [7][6]="b_caballo";
        tablero [7][7]="b_torre";
        
    }
    
    private void colorTablero() {  //cambiar color del fondo en este modo de juego
        //pinta de verde todas las casillas negras
         Color verde = new Color(125, 173, 126);
         
        /* for(int i = 0; i<77; i++){
         vista*/
         
         Tablero_1.c01.setBackground(verde);
         Tablero_1.c03.setBackground(verde);
         Tablero_1.c05.setBackground(verde);
         Tablero_1.c07.setBackground(verde);
         
         Tablero_1.c10.setBackground(verde);       
         Tablero_1.c12.setBackground(verde);
         Tablero_1.c14.setBackground(verde);
         Tablero_1.c16.setBackground(verde);

         Tablero_1.c21.setBackground(verde);
         Tablero_1.c23.setBackground(verde);
         Tablero_1.c25.setBackground(verde);
         Tablero_1.c27.setBackground(verde);
         
         Tablero_1.c30.setBackground(verde);
         Tablero_1.c32.setBackground(verde);
         Tablero_1.c34.setBackground(verde);
         Tablero_1.c36.setBackground(verde); 
       
         Tablero_1.c41.setBackground(verde); 
         Tablero_1.c43.setBackground(verde);
         Tablero_1.c45.setBackground(verde);
         Tablero_1.c47.setBackground(verde);

         Tablero_1.c50.setBackground(verde); 
         Tablero_1.c52.setBackground(verde);
         Tablero_1.c54.setBackground(verde);
         Tablero_1.c56.setBackground(verde);
         
         Tablero_1.c61.setBackground(verde); 
         Tablero_1.c63.setBackground(verde);
         Tablero_1.c65.setBackground(verde);
         Tablero_1.c67.setBackground(verde);
         
         Tablero_1.c70.setBackground(verde); 
         Tablero_1.c72.setBackground(verde);
         Tablero_1.c74.setBackground(verde);
         Tablero_1.c76.setBackground(verde);            
    }
//Se crea un array que hara de tablero y se incializan los String que actuarán como fichas   
    private void añadirActionEvents() {
        Tablero_1.c00.addActionListener(this);
        Tablero_1.c01.addActionListener(this);
        Tablero_1.c02.addActionListener(this);
        Tablero_1.c03.addActionListener(this);
        Tablero_1.c04.addActionListener(this);
        Tablero_1.c05.addActionListener(this);
        Tablero_1.c06.addActionListener(this);
        Tablero_1.c07.addActionListener(this);

        Tablero_1.c10.addActionListener(this);
        Tablero_1.c11.addActionListener(this);
        Tablero_1.c12.addActionListener(this);
        Tablero_1.c13.addActionListener(this);
        Tablero_1.c14.addActionListener(this);
        Tablero_1.c15.addActionListener(this);
        Tablero_1.c16.addActionListener(this);
        Tablero_1.c17.addActionListener(this);

        Tablero_1.c20.addActionListener(this);
        Tablero_1.c21.addActionListener(this);
        Tablero_1.c22.addActionListener(this);
        Tablero_1.c23.addActionListener(this);
        Tablero_1.c24.addActionListener(this);
        Tablero_1.c25.addActionListener(this);
        Tablero_1.c26.addActionListener(this);
        Tablero_1.c27.addActionListener(this);

        Tablero_1.c30.addActionListener(this);
        Tablero_1.c31.addActionListener(this);
        Tablero_1.c32.addActionListener(this);
        Tablero_1.c33.addActionListener(this);
        Tablero_1.c34.addActionListener(this);
        Tablero_1.c35.addActionListener(this);
        Tablero_1.c36.addActionListener(this);
        Tablero_1.c37.addActionListener(this);

        Tablero_1.c40.addActionListener(this);
        Tablero_1.c41.addActionListener(this);
        Tablero_1.c42.addActionListener(this);
        Tablero_1.c43.addActionListener(this);
        Tablero_1.c44.addActionListener(this);
        Tablero_1.c45.addActionListener(this);
        Tablero_1.c46.addActionListener(this);
        Tablero_1.c47.addActionListener(this);

        Tablero_1.c50.addActionListener(this);
        Tablero_1.c51.addActionListener(this);
        Tablero_1.c52.addActionListener(this);
        Tablero_1.c53.addActionListener(this);
        Tablero_1.c54.addActionListener(this);
        Tablero_1.c55.addActionListener(this);
        Tablero_1.c56.addActionListener(this);
        Tablero_1.c57.addActionListener(this);

        Tablero_1.c60.addActionListener(this);
        Tablero_1.c61.addActionListener(this);
        Tablero_1.c62.addActionListener(this);
        Tablero_1.c63.addActionListener(this);
        Tablero_1.c64.addActionListener(this);
        Tablero_1.c65.addActionListener(this);
        Tablero_1.c66.addActionListener(this);
        Tablero_1.c67.addActionListener(this);

        Tablero_1.c70.addActionListener(this);
        Tablero_1.c71.addActionListener(this);
        Tablero_1.c72.addActionListener(this);
        Tablero_1.c73.addActionListener(this);
        Tablero_1.c74.addActionListener(this);
        Tablero_1.c75.addActionListener(this);
        Tablero_1.c76.addActionListener(this);
        Tablero_1.c77.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (turnoJugador.getTurnoJugador() == 'b') { //da el turno a las blancas
            posicionActual = GETbutonposicion(ae.getSource());
            if (comprobarSiLaFichaEsBlanca(posicionActual)) {
                posicionAntigua = posicionActual;
            } else if (posicionAntigua != null) {
                posicionNueva = posicionActual;
                if (movimientos.esPosibleEsteMovimientoA(tablero, posicionAntigua, posicionNueva)){
                    lecturaT();
                    cambiarfichas(posicionAntigua, posicionNueva);
                    posicionNueva = null;
                    posicionAntigua = null;
                    if(comioFicha()){
                      turnoJugador.setTurnoJugador('b');
                    JPanel_Turno.setBackground(Color.white);//cede el turno a las negras                  
                      
                      peonNA=peonN;
                      caballoNA=caballoN;
                      torreNA=torreN;
                      alfilNA=alfilN;
                      reinaNA=reinaN;
                      
                  }else{
                      turnoJugador.setTurnoJugador('n');
                      JPanel_Turno.setBackground(Color.black);//cede el turno a las negras
                  }
                  
                    tiempo_b.stop();
                    tiempo_n.start();
                    comprobarJaqueMateHaciaBlancas();
                    comprobarJaqueMateHaciaNegras();

                }
            }
        }
        if (turnoJugador.getTurnoJugador() == 'n') { //da el turno a las negras
            posicionActual = GETbutonposicion(ae.getSource());
            if (comprobarSiLaFichaEsNegra(posicionActual)) {
                posicionAntigua = posicionActual;
            } else if (posicionAntigua != null) {
                posicionNueva = posicionActual;
                if (movimientos.esPosibleEsteMovimientoB(tablero, posicionAntigua, posicionNueva)){
                    lecturaTT();
                    cambiarfichas(posicionAntigua, posicionNueva);
                    posicionNueva = null;
                    posicionAntigua = null;
                    if(modovariadoS()){
                      turnoJugador.setTurnoJugador('n');
                      JPanel_Turno.setBackground(Color.black);//cede el turno a las negras                      
                      
                      peonBA=peonB;
                      caballoBA=caballoB;
                      torreBA=torreB;
                      alfilBA=alfilB;
                      reinaBA=reinaB;
                      
                  }else{
                      turnoJugador.setTurnoJugador('b');
                      JPanel_Turno.setBackground(Color.white);//cede el turno a las blancas
                  }

                    tiempo_n.stop();
                    tiempo_b.start();
                    comprobarJaqueMateHaciaNegras();
                    comprobarJaqueMateHaciaBlancas();
                }
            }
        }
    }
    
    public void cambiarfichas(String posAntigua, String posNueva){
        cambiarenString(posAntigua, posNueva);
        cambiarEnPantalla(posAntigua,posNueva);
        comprobarPeonEnUltimaFila();
        comprobarEnrroque(posAntigua, posNueva);
        
    }
    
    public void cambiarenString(String posAntigua, String posNueva){
       int xA = Character.getNumericValue(posAntigua.charAt(1));
        int yA = Character.getNumericValue(posAntigua.charAt(0));

        int xN = Character.getNumericValue(posNueva.charAt(1));
        int yN = Character.getNumericValue(posNueva.charAt(0));
               
        tablero[yN][xN] = tablero[yA][xA];
        tablero[yA][xA] = "";
        
    }
    
    private void cambiarEnPantalla(String posAntigua, String posNueva) {
        boton(posNueva).setIcon(boton(posAntigua).getIcon());
        boton(posAntigua).setIcon(null);
    }
    
    private JButton boton (String posicion){
         if (posicion.equals("00")) {
            return Tablero_1.c00;
        } else if (posicion.equals("01")) {
            return Tablero_1.c01;
        } else if (posicion.equals("02")) {
            return Tablero_1.c02;
        } else if (posicion.equals("03")) {
            return Tablero_1.c03;
        } else if (posicion.equals("04")) {
            return Tablero_1.c04;
        } else if (posicion.equals("05")) {
            return Tablero_1.c05;
        } else if (posicion.equals("06")) {
            return Tablero_1.c06;
        } else if (posicion.equals("07")) {
            return Tablero_1.c07;
        } else if (posicion.equals("10")) {
            return Tablero_1.c10;
        } else if (posicion.equals("11")) {
            return Tablero_1.c11;
        } else if (posicion.equals("12")) {
            return Tablero_1.c12;
        } else if (posicion.equals("13")) {
            return Tablero_1.c13;
        } else if (posicion.equals("14")) {
            return Tablero_1.c14;
        } else if (posicion.equals("15")) {
            return Tablero_1.c15;
        } else if (posicion.equals("16")) {
            return Tablero_1.c16;
        } else if (posicion.equals("17")) {
            return Tablero_1.c17;
        } else if (posicion.equals("20")) {
            return Tablero_1.c20;
        } else if (posicion.equals("21")) {
            return Tablero_1.c21;
        } else if (posicion.equals("22")) {
            return Tablero_1.c22;
        } else if (posicion.equals("23")) {
            return Tablero_1.c23;
        } else if (posicion.equals("24")) {
            return Tablero_1.c24;
        } else if (posicion.equals("25")) {
            return Tablero_1.c25;
        } else if (posicion.equals("26")) {
            return Tablero_1.c26;
        } else if (posicion.equals("27")) {
            return Tablero_1.c27;
        } else if (posicion.equals("30")) {
            return Tablero_1.c30;
        } else if (posicion.equals("31")) {
            return Tablero_1.c31;
        } else if (posicion.equals("32")) {
            return Tablero_1.c32;
        } else if (posicion.equals("33")) {
            return Tablero_1.c33;
        } else if (posicion.equals("34")) {
            return Tablero_1.c34;
        } else if (posicion.equals("35")) {
            return Tablero_1.c35;
        } else if (posicion.equals("36")) {
            return Tablero_1.c36;
        } else if (posicion.equals("37")) {
            return Tablero_1.c37;
        } else if (posicion.equals("40")) {
            return Tablero_1.c40;
        } else if (posicion.equals("41")) {
            return Tablero_1.c41;
        } else if (posicion.equals("42")) {
            return Tablero_1.c42;
        } else if (posicion.equals("43")) {
            return Tablero_1.c43;
        } else if (posicion.equals("44")) {
            return Tablero_1.c44;
        } else if (posicion.equals("45")) {
            return Tablero_1.c45;
        } else if (posicion.equals("46")) {
            return Tablero_1.c46;
        } else if (posicion.equals("47")) {
            return Tablero_1.c47;
        } else if (posicion.equals("50")) {
            return Tablero_1.c50;
        } else if (posicion.equals("51")) {
            return Tablero_1.c51;
        } else if (posicion.equals("52")) {
            return Tablero_1.c52;
        } else if (posicion.equals("53")) {
            return Tablero_1.c53;
        } else if (posicion.equals("54")) {
            return Tablero_1.c54;
        } else if (posicion.equals("55")) {
            return Tablero_1.c55;
        } else if (posicion.equals("56")) {
            return Tablero_1.c56;
        } else if (posicion.equals("57")) {
            return Tablero_1.c57;
        } else if (posicion.equals("60")) {
            return Tablero_1.c60;
        } else if (posicion.equals("61")) {
            return Tablero_1.c61;
        } else if (posicion.equals("62")) {
            return Tablero_1.c62;
        } else if (posicion.equals("63")) {
            return Tablero_1.c63;
        } else if (posicion.equals("64")) {
            return Tablero_1.c64;
        } else if (posicion.equals("65")) {
            return Tablero_1.c65;
        } else if (posicion.equals("66")) {
            return Tablero_1.c66;
        } else if (posicion.equals("67")) {
            return Tablero_1.c67;
        } else if (posicion.equals("70")) {
            return Tablero_1.c70;
        } else if (posicion.equals("71")) {
            return Tablero_1.c71;
        } else if (posicion.equals("72")) {
            return Tablero_1.c72;
        } else if (posicion.equals("73")) {
            return Tablero_1.c73;
        } else if (posicion.equals("74")) {
            return Tablero_1.c74;
        } else if (posicion.equals("75")) {
            return Tablero_1.c75;
        } else if (posicion.equals("76")) {
            return Tablero_1.c76;
        } else if (posicion.equals("77")) {
            return Tablero_1.c77;
        }
        return null;
    }
   
            
    private String GETbutonposicion (Object boton){
        if (boton == Tablero_1.c00) {
            return "00";
        } else if (boton == Tablero_1.c01) {
            return "01";
        } else if (boton == Tablero_1.c02) {
            return "02";
        } else if (boton == Tablero_1.c03) {
            return "03";
        } else if (boton == Tablero_1.c04) {
            return "04";
        } else if (boton == Tablero_1.c05) {
            return "05";
        } else if (boton == Tablero_1.c06) {
            return "06";
        } else if (boton == Tablero_1.c07) {
            return "07";
        } else if (boton == Tablero_1.c10) {
            return "10";
        } else if (boton == Tablero_1.c11) {
            return "11";
        } else if (boton == Tablero_1.c12) {
            return "12";
        } else if (boton == Tablero_1.c13) {
            return "13";
        } else if (boton == Tablero_1.c14) {
            return "14";
        } else if (boton == Tablero_1.c15) {
            return "15";
        } else if (boton == Tablero_1.c16) {
            return "16";
        } else if (boton == Tablero_1.c17) {
            return "17";
        } else if (boton == Tablero_1.c20) {
            return "20";
        } else if (boton == Tablero_1.c21) {
            return "21";
        } else if (boton == Tablero_1.c22) {
            return "22";
        } else if (boton == Tablero_1.c23) {
            return "23";
        } else if (boton == Tablero_1.c24) {
            return "24";
        } else if (boton == Tablero_1.c25) {
            return "25";
        } else if (boton == Tablero_1.c26) {
            return "26";
        } else if (boton == Tablero_1.c27) {
            return "27";
        } else if (boton == Tablero_1.c30) {
            return "30";
        } else if (boton == Tablero_1.c31) {
            return "31";
        } else if (boton == Tablero_1.c32) {
            return "32";
        } else if (boton == Tablero_1.c33) {
            return "33";
        } else if (boton == Tablero_1.c34) {
            return "34";
        } else if (boton == Tablero_1.c35) {
            return "35";
        } else if (boton == Tablero_1.c36) {
            return "36";
        } else if (boton == Tablero_1.c37) {
            return "37";
        } else if (boton == Tablero_1.c40) {
            return "40";
        } else if (boton == Tablero_1.c41) {
            return "41";
        } else if (boton == Tablero_1.c42) {
            return "42";
        } else if (boton == Tablero_1.c43) {
            return "43";
        } else if (boton == Tablero_1.c44) {
            return "44";
        } else if (boton == Tablero_1.c45) {
            return "45";
        } else if (boton == Tablero_1.c46) {
            return "46";
        } else if (boton == Tablero_1.c47) {
            return "47";
        } else if (boton == Tablero_1.c50) {
            return "50";
        } else if (boton == Tablero_1.c51) {
            return "51";
        } else if (boton == Tablero_1.c52) {
            return "52";
        } else if (boton == Tablero_1.c53) {
            return "53";
        } else if (boton == Tablero_1.c54) {
            return "54";
        } else if (boton == Tablero_1.c55) {
            return "55";
        } else if (boton == Tablero_1.c56) {
            return "56";
        } else if (boton == Tablero_1.c57) {
            return "57";
        } else if (boton == Tablero_1.c60) {
            return "60";
        } else if (boton == Tablero_1.c61) {
            return "61";
        } else if (boton == Tablero_1.c62) {
            return "62";
        } else if (boton == Tablero_1.c63) {
            return "63";
        } else if (boton == Tablero_1.c64) {
            return "64";
        } else if (boton == Tablero_1.c65) {
            return "65";
        } else if (boton == Tablero_1.c66) {
            return "66";
        } else if (boton == Tablero_1.c67) {
            return "67";
        } else if (boton == Tablero_1.c70) {
            return "70";
        } else if (boton == Tablero_1.c71) {
            return "71";
        } else if (boton == Tablero_1.c72) {
            return "72";
        } else if (boton == Tablero_1.c73) {
            return "73";
        } else if (boton == Tablero_1.c74) {
            return "74";
        } else if (boton == Tablero_1.c75) {
            return "75";
        } else if (boton == Tablero_1.c76) {
            return "76";
        } else if (boton == Tablero_1.c77) {
            return "77";
        }
        return null;
    
    }
    //se les asignan valores a los botones para saber su posicion
    
    private boolean comprobarSiLaFichaEsBlanca(String posicion) {
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));
        if (!tablero[y][x].equals("")) {
            return (tablero[y][x].charAt(0) == 'b') ? true : false;
        }
        return false;
    }
    private boolean comprobarSiLaFichaEsNegra(String posicion) {
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));
        if (!tablero[y][x].equals("")) {
            return (tablero[y][x].charAt(0) == 'n') ? true : false;
        }
        return false;
    }
    
   /* private void turnos(String posAntigua, String posNueva){
        cambiarenString(posAntigua, posNueva);
        int xA = Character.getNumericValue(posAntigua.charAt(1));
        int yA = Character.getNumericValue(posAntigua.charAt(0));

        int xN = Character.getNumericValue(posNueva.charAt(1));
        int yN = Character.getNumericValue(posNueva.charAt(0));
        
        if(turnoJugador=='b'){
            if(tablero[yA][xA] == ""){
                turnoJugador='n';
            }
        }
         if(turnoJugador=='n'){
            if(posicionAntigua==posicionActual || posicionNueva==posicionActual){
                turnoJugador='b';
            }
        }
        
        }*/
        
    private void lecturaT(){
        // se lee el arreglo y se cuentan las fichas
            
             for (int i=0; i<8; i++){
             for (int j=0; j<8; j++){
                 if(tablero[i][j].equals("n_peon")){
                     peonN+=1;
                 }
                 if(tablero[i][j].equals("n_caballo")){
                     caballoN+=1;
                 }
                 if(tablero[i][j].equals("n_torre")){
                     torreN+=1;
                 }
                 if(tablero[i][j].equals("n_alfil")){
                     alfilN+=1;
                 }
                 if(tablero[i][j].equals("n_reina")){
                     reinaN+=1;
                 }
            }
        }
             
        
    }
    
    private void lecturaTT(){
            // se lee el arreglo y se cuentan las fichas blancas
            
             for (int i=0; i<8; i++){
             for (int j=0; j<8; j++){
                 if(tablero[i][j].equals("b_peon")){
                     peonB+=1;
                 }
                 if(tablero[i][j].equals("b_caballo")){
                     caballoB+=1;
                 }
                 if(tablero[i][j].equals("b_torre")){
                     torreB+=1;
                 }
                 if(tablero[i][j].equals("b_alfil")){
                     alfilB+=1;
                 }
                 if(tablero[i][j].equals("b_reina")){
                     reinaB+=1;
                 }
            }
        }
             
        
    }
    private boolean comioFicha(){
        // vuelve a leer el tablero despues de mover una ficha, para ver si se han comido
        //una ficha, si ese es el caso retorna true, sino false
            
            
             for (int i=0; i<8; i++){
             for (int j=0; j<8; j++){
                 if(tablero[i][j].equals("n_peon")){
                     peonNA+=1;
                 }
                 if(tablero[i][j].equals("n_caballo")){
                     caballoNA+=1;
                 }
                 if(tablero[i][j].equals("n_torre")){
                     torreNA+=1;
                 }
                 if(tablero[i][j].equals("n_alfil")){
                     alfilNA+=1;
                 }
                 if(tablero[i][j].equals("n_reina")){
                     reinaNA+=1;
                 }
            }
        }
            
          if(peonNA!=peonN || caballoNA!=caballoN || alfilNA!=alfilN || reinaNA!=reinaN|| torreNA!=torreN ){
              return true;
          } else {
              return false;
          }  
         
    }
    
    private void comprobarPeonEnUltimaFila(){
        for (int i = 0; i < 8; i++) {
            if (tablero[0][i].equals("b_peon")) {
                //Se mostrara la tabla de eleccion de ficha
                eleccionDePeon();
                tablero[0][i] = fichaElegida;
                String posicion = "0" + i;
                boton(posicion).setIcon(imagenElegida);
            }

            if (tablero[7][i].equals("n_peon")) {
                eleccionDePeonN();
                tablero[7][i] = fichaElegida;
                String posicion = "7" + i;
                boton(posicion).setIcon(imagenElegida);
            }
        }
    }
    private void comprobarJaqueMateHaciaBlancas(){
      boolean jaqueMate = true;
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                String posicion = ""+i+""+j;
                if(comprobarSiLaFichaEsBlanca(posicion)){
                    String[] movimientosb = movimientos.movimientosAmodificados(tablero, posicion);
                    if(!movimientosb[0].equals("")){
                        jaqueMate = false;
                        break;
                    }
                }
            }
        }
        
        if(jaqueMate == true){
            tiempo_b.stop();
            tiempo_n.stop();
            JaqueMateBlancas ventana = new JaqueMateBlancas(null,true);
            ventana.setVisible(true);
        }
        
    }
    
    private boolean modovariadoS(){
        
            //es igual
            
             for (int i=0; i<8; i++){
             for (int j=0; j<8; j++){
                 if(tablero[i][j].equals("b_peon")){
                     peonBA+=1;
                 }
                 if(tablero[i][j].equals("b_caballo")){
                     caballoBA+=1;
                 }
                 if(tablero[i][j].equals("b_torre")){
                     torreBA+=1;
                 }
                 if(tablero[i][j].equals("b_alfil")){
                     alfilBA+=1;
                 }
                 if(tablero[i][j].equals("b_reina")){
                     reinaBA+=1;
                 }
            }
        }
             
          if(peonBA!=peonB || caballoBA!=caballoB || alfilBA!=alfilB || reinaBA!=reinaB|| torreBA!=torreB ){
              return true;
          } else {
              return false;
          }  
         
    }
    private void comprobarJaqueMateHaciaNegras(){
        boolean jaqueMate = true;
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                String posicion = ""+i+""+j;
                if(comprobarSiLaFichaEsNegra(posicion)){
                    String[] movimientosn = movimientos.movimientosBmodificados(tablero, posicion);
                    if(!movimientosn[0].equals("")){
                        jaqueMate = false;
                        break;
                    }
                }
            }
        }
        
        if(jaqueMate == true){
            tiempo_b.stop();
            tiempo_n.stop();
            JaqueMateNegras ventana = new JaqueMateNegras(null,true);
            ventana.setVisible(true);
        }
        
    }
    
    private void eleccionDePeon() {
        
        VentanaEleccionFicha ventanaElec = new VentanaEleccionFicha(null, true);
        ventanaElec.setVisible(true);
    
    }
    
    private void eleccionDePeonN() {
        
        VentanaEleccionFichaN ventanaElecN = new VentanaEleccionFichaN(null, true);
        ventanaElecN.setVisible(true);
    
    }
    
    private void comprobarEnrroque(String posAntigua, String posNueva) {
        int xN = Character.getNumericValue(posNueva.charAt(1));
        int yN = Character.getNumericValue(posNueva.charAt(0));

        int xA = Character.getNumericValue(posAntigua.charAt(1));
        int yA = Character.getNumericValue(posAntigua.charAt(0));

        if (tablero[yN][xN].equals("b_rey") || tablero[yN][xN].equals("n_rey")) {
            if (xA + 2 == xN) {
                //Derecha
                tablero[yN][xN - 1] = tablero[yN][7];
                tablero[yN][xN + 1] = "";

                String posicionTorreAntigua = "" + yN + 7;
                String posicionTorreNueva = "" + yN + (xN - 1);

                boton(posicionTorreNueva).setIcon(boton(posicionTorreAntigua).getIcon());
                boton(posicionTorreAntigua).setIcon(null);
            } else if (xA - 2 == xN) {
                //Izquierda
                tablero[yN][xA - 1] = tablero[yN][0];
                tablero[yN][0] = "";

                String posicionTorreAntigua = "" + yN + 0;
                String posicionTorreNueva = "" + yN + (xA - 1);

                boton(posicionTorreNueva).setIcon(boton(posicionTorreAntigua).getIcon());
                boton(posicionTorreAntigua).setIcon(null);
            }
        }
    }

    //Temporizador de las piezas blancas
    private Timer tiempo_b;   
    private int centesimas_b = 0;
    private int segundos_b = 0;
    private int minutos_b = 0;
    private ActionListener acciones_b = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            centesimas_b++;
            if(centesimas_b > 99){
                segundos_b++;
                centesimas_b = 0;           
            }
            if(segundos_b > 59){
                minutos_b++;
                segundos_b = 0;
            }
            if(minutos_b > 59){
                minutos_b = 0;
            }
            actualizarEtiquetaBlancas();
        }
    };
    private void actualizarEtiquetaBlancas(){
        String texto_b = (minutos_b<=9?"0":"")+minutos_b+":"+(segundos_b<=9?"0":"")+segundos_b+":"+(centesimas_b<=9?"0":"")+centesimas_b;
        Tiempo_blancas.setText(texto_b);
    }  
    
    //Temporizador de las piezas negras
    private Timer tiempo_n;  
    private int centesimas_n = 0;
    private int segundos_n = 0;
    private int minutos_n = 0;
    private ActionListener acciones_n = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            centesimas_n++;
            if(centesimas_n > 99){
                segundos_n++;
                centesimas_n = 0;           
            }
            if(segundos_n > 59){
                minutos_n++;
                segundos_n = 0;
            }
            if(minutos_n > 59){
                minutos_n = 0;
            }
            actualizarEtiquetaNegras();
        }
    };
    private void actualizarEtiquetaNegras(){
        String texto_n = (minutos_n<=9?"0":"")+minutos_n+":"+(segundos_n<=9?"0":"")+segundos_n+":"+(centesimas_n<=9?"0":"")+centesimas_n;
        Tiempo_negras.setText(texto_n);
    } 
            
    
}