/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package board;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 *
 * @author Lenovo
 */
public class controlador implements ActionListener {
    public static boolean enrroqueReyA = true;
    public static boolean enrroqueTorreIzquierdaA = true;
    public static boolean enrroqueTorreDerechaA = true;
    public static boolean enrroqueReyB = true;
    public static boolean enrroqueTorreIzquierdaB = true;
    public static boolean enrroqueTorreDerechaB = true;

    public static String[][] tablero = new String[8][8];
    private  char turnoJugador = 'b';
    private String posicionAntigua = null;
    private String posicionNueva = null;
    private String posicionActual;
    public static String fichaElegida;
    public static ImageIcon imagenElegida;
    movimientos movimientos;
    public boolean jaqueMate=false;
    
    public controlador(){
        iniciarTablero();
        interfaz1 vista= new interfaz1(); 
        vista.setVisible(true);
      //*  turnos();
        añadirActionEvents();
        movimientos = new movimientos();
        
        
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
//Se crea un array que hara de tablero y se incializan los String que actuaran como fichas   
    private void añadirActionEvents() {
        interfaz1.c00.addActionListener(this);
        interfaz1.c01.addActionListener(this);
        interfaz1.c02.addActionListener(this);
        interfaz1.c03.addActionListener(this);
        interfaz1.c04.addActionListener(this);
        interfaz1.c05.addActionListener(this);
        interfaz1.c06.addActionListener(this);
        interfaz1.c07.addActionListener(this);

        interfaz1.c10.addActionListener(this);
        interfaz1.c11.addActionListener(this);
        interfaz1.c12.addActionListener(this);
        interfaz1.c13.addActionListener(this);
        interfaz1.c14.addActionListener(this);
        interfaz1.c15.addActionListener(this);
        interfaz1.c16.addActionListener(this);
        interfaz1.c17.addActionListener(this);

        interfaz1.c20.addActionListener(this);
        interfaz1.c21.addActionListener(this);
        interfaz1.c22.addActionListener(this);
        interfaz1.c23.addActionListener(this);
        interfaz1.c24.addActionListener(this);
        interfaz1.c25.addActionListener(this);
        interfaz1.c26.addActionListener(this);
        interfaz1.c27.addActionListener(this);

        interfaz1.c30.addActionListener(this);
        interfaz1.c31.addActionListener(this);
        interfaz1.c32.addActionListener(this);
        interfaz1.c33.addActionListener(this);
        interfaz1.c34.addActionListener(this);
        interfaz1.c35.addActionListener(this);
        interfaz1.c36.addActionListener(this);
        interfaz1.c37.addActionListener(this);

        interfaz1.c40.addActionListener(this);
        interfaz1.c41.addActionListener(this);
        interfaz1.c42.addActionListener(this);
        interfaz1.c43.addActionListener(this);
        interfaz1.c44.addActionListener(this);
        interfaz1.c45.addActionListener(this);
        interfaz1.c46.addActionListener(this);
        interfaz1.c47.addActionListener(this);

        interfaz1.c50.addActionListener(this);
        interfaz1.c51.addActionListener(this);
        interfaz1.c52.addActionListener(this);
        interfaz1.c53.addActionListener(this);
        interfaz1.c54.addActionListener(this);
        interfaz1.c55.addActionListener(this);
        interfaz1.c56.addActionListener(this);
        interfaz1.c57.addActionListener(this);

        interfaz1.c60.addActionListener(this);
        interfaz1.c61.addActionListener(this);
        interfaz1.c62.addActionListener(this);
        interfaz1.c63.addActionListener(this);
        interfaz1.c64.addActionListener(this);
        interfaz1.c65.addActionListener(this);
        interfaz1.c66.addActionListener(this);
        interfaz1.c67.addActionListener(this);

        interfaz1.c70.addActionListener(this);
        interfaz1.c71.addActionListener(this);
        interfaz1.c72.addActionListener(this);
        interfaz1.c73.addActionListener(this);
        interfaz1.c74.addActionListener(this);
        interfaz1.c75.addActionListener(this);
        interfaz1.c76.addActionListener(this);
        interfaz1.c77.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (turnoJugador == 'b') {
            posicionActual = GETbutonposicion(ae.getSource());

            if (comprobarSiLaFichaEsBlanca(posicionActual)) {
                posicionAntigua = posicionActual;
            } else if (posicionAntigua != null) {
                posicionNueva = posicionActual;
                if (movimientos.esPosibleEsteMovimientoA(tablero, posicionAntigua, posicionNueva)){
                    cambiarfichas(posicionAntigua, posicionNueva);
                    posicionNueva = null;
                    posicionAntigua = null;
                    turnoJugador = 'n';
                    comprobarJaqueMateHaciaBlancas();
                    comprobarJaqueMateHaciaNegras();
                   
                }
            }
        }
        if (turnoJugador == 'n') {
            posicionActual = GETbutonposicion(ae.getSource());

            if (comprobarSiLaFichaEsNegra(posicionActual)) {
                posicionAntigua = posicionActual;
            } else if (posicionAntigua != null) {
                posicionNueva = posicionActual;
                if (movimientos.esPosibleEsteMovimientoB(tablero, posicionAntigua, posicionNueva)){
                    cambiarfichas(posicionAntigua, posicionNueva);
                    posicionNueva = null;
                    posicionAntigua = null;
                    turnoJugador = 'b';
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
            return interfaz1.c00;
        } else if (posicion.equals("01")) {
            return interfaz1.c01;
        } else if (posicion.equals("02")) {
            return interfaz1.c02;
        } else if (posicion.equals("03")) {
            return interfaz1.c03;
        } else if (posicion.equals("04")) {
            return interfaz1.c04;
        } else if (posicion.equals("05")) {
            return interfaz1.c05;
        } else if (posicion.equals("06")) {
            return interfaz1.c06;
        } else if (posicion.equals("07")) {
            return interfaz1.c07;
        } else if (posicion.equals("10")) {
            return interfaz1.c10;
        } else if (posicion.equals("11")) {
            return interfaz1.c11;
        } else if (posicion.equals("12")) {
            return interfaz1.c12;
        } else if (posicion.equals("13")) {
            return interfaz1.c13;
        } else if (posicion.equals("14")) {
            return interfaz1.c14;
        } else if (posicion.equals("15")) {
            return interfaz1.c15;
        } else if (posicion.equals("16")) {
            return interfaz1.c16;
        } else if (posicion.equals("17")) {
            return interfaz1.c17;
        } else if (posicion.equals("20")) {
            return interfaz1.c20;
        } else if (posicion.equals("21")) {
            return interfaz1.c21;
        } else if (posicion.equals("22")) {
            return interfaz1.c22;
        } else if (posicion.equals("23")) {
            return interfaz1.c23;
        } else if (posicion.equals("24")) {
            return interfaz1.c24;
        } else if (posicion.equals("25")) {
            return interfaz1.c25;
        } else if (posicion.equals("26")) {
            return interfaz1.c26;
        } else if (posicion.equals("27")) {
            return interfaz1.c27;
        } else if (posicion.equals("30")) {
            return interfaz1.c30;
        } else if (posicion.equals("31")) {
            return interfaz1.c31;
        } else if (posicion.equals("32")) {
            return interfaz1.c32;
        } else if (posicion.equals("33")) {
            return interfaz1.c33;
        } else if (posicion.equals("34")) {
            return interfaz1.c34;
        } else if (posicion.equals("35")) {
            return interfaz1.c35;
        } else if (posicion.equals("36")) {
            return interfaz1.c36;
        } else if (posicion.equals("37")) {
            return interfaz1.c37;
        } else if (posicion.equals("40")) {
            return interfaz1.c40;
        } else if (posicion.equals("41")) {
            return interfaz1.c41;
        } else if (posicion.equals("42")) {
            return interfaz1.c42;
        } else if (posicion.equals("43")) {
            return interfaz1.c43;
        } else if (posicion.equals("44")) {
            return interfaz1.c44;
        } else if (posicion.equals("45")) {
            return interfaz1.c45;
        } else if (posicion.equals("46")) {
            return interfaz1.c46;
        } else if (posicion.equals("47")) {
            return interfaz1.c47;
        } else if (posicion.equals("50")) {
            return interfaz1.c50;
        } else if (posicion.equals("51")) {
            return interfaz1.c51;
        } else if (posicion.equals("52")) {
            return interfaz1.c52;
        } else if (posicion.equals("53")) {
            return interfaz1.c53;
        } else if (posicion.equals("54")) {
            return interfaz1.c54;
        } else if (posicion.equals("55")) {
            return interfaz1.c55;
        } else if (posicion.equals("56")) {
            return interfaz1.c56;
        } else if (posicion.equals("57")) {
            return interfaz1.c57;
        } else if (posicion.equals("60")) {
            return interfaz1.c60;
        } else if (posicion.equals("61")) {
            return interfaz1.c61;
        } else if (posicion.equals("62")) {
            return interfaz1.c62;
        } else if (posicion.equals("63")) {
            return interfaz1.c63;
        } else if (posicion.equals("64")) {
            return interfaz1.c64;
        } else if (posicion.equals("65")) {
            return interfaz1.c65;
        } else if (posicion.equals("66")) {
            return interfaz1.c66;
        } else if (posicion.equals("67")) {
            return interfaz1.c67;
        } else if (posicion.equals("70")) {
            return interfaz1.c70;
        } else if (posicion.equals("71")) {
            return interfaz1.c71;
        } else if (posicion.equals("72")) {
            return interfaz1.c72;
        } else if (posicion.equals("73")) {
            return interfaz1.c73;
        } else if (posicion.equals("74")) {
            return interfaz1.c74;
        } else if (posicion.equals("75")) {
            return interfaz1.c75;
        } else if (posicion.equals("76")) {
            return interfaz1.c76;
        } else if (posicion.equals("77")) {
            return interfaz1.c77;
        }
        return null;
    }
   
            
    private String GETbutonposicion (Object boton){
        if (boton == interfaz1.c00) {
            return "00";
        } else if (boton == interfaz1.c01) {
            return "01";
        } else if (boton == interfaz1.c02) {
            return "02";
        } else if (boton == interfaz1.c03) {
            return "03";
        } else if (boton == interfaz1.c04) {
            return "04";
        } else if (boton == interfaz1.c05) {
            return "05";
        } else if (boton == interfaz1.c06) {
            return "06";
        } else if (boton == interfaz1.c07) {
            return "07";
        } else if (boton == interfaz1.c10) {
            return "10";
        } else if (boton == interfaz1.c11) {
            return "11";
        } else if (boton == interfaz1.c12) {
            return "12";
        } else if (boton == interfaz1.c13) {
            return "13";
        } else if (boton == interfaz1.c14) {
            return "14";
        } else if (boton == interfaz1.c15) {
            return "15";
        } else if (boton == interfaz1.c16) {
            return "16";
        } else if (boton == interfaz1.c17) {
            return "17";
        } else if (boton == interfaz1.c20) {
            return "20";
        } else if (boton == interfaz1.c21) {
            return "21";
        } else if (boton == interfaz1.c22) {
            return "22";
        } else if (boton == interfaz1.c23) {
            return "23";
        } else if (boton == interfaz1.c24) {
            return "24";
        } else if (boton == interfaz1.c25) {
            return "25";
        } else if (boton == interfaz1.c26) {
            return "26";
        } else if (boton == interfaz1.c27) {
            return "27";
        } else if (boton == interfaz1.c30) {
            return "30";
        } else if (boton == interfaz1.c31) {
            return "31";
        } else if (boton == interfaz1.c32) {
            return "32";
        } else if (boton == interfaz1.c33) {
            return "33";
        } else if (boton == interfaz1.c34) {
            return "34";
        } else if (boton == interfaz1.c35) {
            return "35";
        } else if (boton == interfaz1.c36) {
            return "36";
        } else if (boton == interfaz1.c37) {
            return "37";
        } else if (boton == interfaz1.c40) {
            return "40";
        } else if (boton == interfaz1.c41) {
            return "41";
        } else if (boton == interfaz1.c42) {
            return "42";
        } else if (boton == interfaz1.c43) {
            return "43";
        } else if (boton == interfaz1.c44) {
            return "44";
        } else if (boton == interfaz1.c45) {
            return "45";
        } else if (boton == interfaz1.c46) {
            return "46";
        } else if (boton == interfaz1.c47) {
            return "47";
        } else if (boton == interfaz1.c50) {
            return "50";
        } else if (boton == interfaz1.c51) {
            return "51";
        } else if (boton == interfaz1.c52) {
            return "52";
        } else if (boton == interfaz1.c53) {
            return "53";
        } else if (boton == interfaz1.c54) {
            return "54";
        } else if (boton == interfaz1.c55) {
            return "55";
        } else if (boton == interfaz1.c56) {
            return "56";
        } else if (boton == interfaz1.c57) {
            return "57";
        } else if (boton == interfaz1.c60) {
            return "60";
        } else if (boton == interfaz1.c61) {
            return "61";
        } else if (boton == interfaz1.c62) {
            return "62";
        } else if (boton == interfaz1.c63) {
            return "63";
        } else if (boton == interfaz1.c64) {
            return "64";
        } else if (boton == interfaz1.c65) {
            return "65";
        } else if (boton == interfaz1.c66) {
            return "66";
        } else if (boton == interfaz1.c67) {
            return "67";
        } else if (boton == interfaz1.c70) {
            return "70";
        } else if (boton == interfaz1.c71) {
            return "71";
        } else if (boton == interfaz1.c72) {
            return "72";
        } else if (boton == interfaz1.c73) {
            return "73";
        } else if (boton == interfaz1.c74) {
            return "74";
        } else if (boton == interfaz1.c75) {
            return "75";
        } else if (boton == interfaz1.c76) {
            return "76";
        } else if (boton == interfaz1.c77) {
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
            JaqueMate ventana = new JaqueMate(null,true);
            ventana.setVisible(true);
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
            JaqueMate ventana = new JaqueMate(null,true);
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

   

}