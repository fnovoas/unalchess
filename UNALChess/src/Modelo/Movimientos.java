/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Controlador;

/**
 *
 * @author Lenovo
 */
public class Movimientos {
    
    String[][] tablero;

    private void copiarTablero(String[][] arr) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                arr[i][j] = tablero[i][j];
            }
        }
    }
    
    public boolean esPosibleEsteMovimientoA(String[][] tablero, String posicionAntigua, String posicionNueva) {
        this.tablero = tablero;
        String[] posicionesPosiblesA;
        posicionesPosiblesA = movimientosAmodificados(tablero, posicionAntigua);

        if (posicionesPosiblesA != null) {
            for (int i = 0; i < posicionesPosiblesA.length; i++) {
                if (posicionesPosiblesA[i].equals(posicionNueva)) {
                    comprobarAcercaDeEnrroque(posicionAntigua);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean esPosibleEsteMovimientoB(String[][] tablero, String posicionAntigua, String posicionNueva) {
        this.tablero = tablero;
        String[] posicionesPosiblesB;
        posicionesPosiblesB = movimientosBmodificados(tablero, posicionAntigua);

        if (posicionesPosiblesB != null) {
            for (int i = 0; i < posicionesPosiblesB.length; i++) {
                if (posicionesPosiblesB[i].equals(posicionNueva)) {
                    comprobarAcercaDeEnrroque(posicionAntigua);
                    return true;
                }
            }
        }
        return false;
    }
    
    private void comprobarAcercaDeEnrroque(String posAntigua) {
        if (posAntigua.equals("74")) {
             Controlador.enrroqueReyA=false;
        } else if (posAntigua.equals("70")) {
            Controlador.enrroqueTorreIzquierdaA = false;
        } else if (posAntigua.equals("77")) {
            Controlador.enrroqueTorreDerechaA = false;
        } else if (posAntigua.equals("04")) {
            Controlador.enrroqueReyB = false;
        } else if (posAntigua.equals("00")) {
            Controlador.enrroqueTorreIzquierdaB = false;
        } else if (posAntigua.equals("07")) {
            Controlador.enrroqueTorreDerechaB = false;
        }
    }

    public String[] movimientosAmodificados(String[][] tableroM, String posicion) {
        String[] posiblesMovimientos = movimientosFichas(tableroM, posicion);
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));

        return modificacionTodosMovimientos(tableroM, posicion, posiblesMovimientos);
    }
    
    public String[] movimientosBmodificados(String[][] tableroM, String posicion) {
        String[] posiblesMovimientos = movimientosFichas(tableroM, posicion);
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));

        return modificacionTodosMovimientos(tableroM, posicion, posiblesMovimientos);
    }

    private String[] modificacionTodosMovimientos(String[][] tableroFuturo, String posicionInicial, String[] posicionesFinales) {
        String posicionesDefinitivas = "";

        int xInicial = Character.getNumericValue(posicionInicial.charAt(1));
        int yInicial = Character.getNumericValue(posicionInicial.charAt(0));

        String ficha = fichaDeLaCasilla(tableroFuturo, yInicial, xInicial);
        if (posicionesFinales != null) {
            for (int i = 0; i < posicionesFinales.length; i++) {
                try {
                    String posicionFinal = posicionesFinales[i];
                    int xFinal = Character.getNumericValue(posicionFinal.charAt(1));
                    int yFinal = Character.getNumericValue(posicionFinal.charAt(0));

                    String tableroM[][] = new String[8][8];

                    copiarTableroPrimeroAlSegundo(tableroFuturo, tableroM);

                    tableroM[yFinal][xFinal] = tableroM[yInicial][xInicial];
                    tableroM[yInicial][xInicial] = "";

                    if (ficha.charAt(0) == 'b') {
                        if (reyAenJaque(tableroM) == false) {
                            posicionesDefinitivas += "" + yFinal + "" + xFinal + "_";
                        }
                    } else if (ficha.charAt(0) == 'n') {
                        if (reyBenJaque(tableroM) == false) {
                            posicionesDefinitivas += "" + yFinal + "" + xFinal + "_";
                        }
                    }
                } catch (Exception ex) {
                }

            }

            String[] arrayPosiciones = posicionesDefinitivas.split("_");
            return arrayPosiciones;
        }
        return null;
    }

    private void copiarTableroPrimeroAlSegundo(String[][] tableroOrigen, String[][] tableroCopia) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tableroCopia[i][j] = tableroOrigen[i][j];
            }
        }
    }

    public String[] todosLosMovimientosFichasB(String[][] tableroM) {

        String posicionesTotales = "";

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String ficha = fichaDeLaCasilla(tableroM, i, j);

                if (ficha.equals("n_torre")) {
                    posicionesTotales += transformarApalabra(movimientoTorreBSoloAtaque(tableroM, i, j));
                } else if (ficha.equals("n_alfil")) {
                    posicionesTotales += transformarApalabra(movimientoAlfilBSoloAtaque(tableroM, i, j));
                } else if (ficha.equals("n_reina")) {
                    posicionesTotales += transformarApalabra(movimientoReinaBSoloAtaque(tableroM, i, j));
                } else if (ficha.equals("n_caballo")) {
                    posicionesTotales += transformarApalabra(movimientoCaballoBSoloAtaque(tableroM, i, j));
                } else if (ficha.equals("n_rey")) {
                    posicionesTotales += transformarApalabra(movimientoReyBSoloAtaque(tableroM, i, j));
                } else if (ficha.equals("n_peon")) {
                    posicionesTotales += transformarApalabra(movimientoPeonBSoloAtaque(tableroM, i, j));
                }

            }
        }

        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    public String[] movimientoAtaqueFichaB(String[][] tableroM, int i, int j) {

        String posicionesTotales = "";

        String ficha = fichaDeLaCasilla(tableroM, i, j);

        if (ficha.equals("n_torre")) {
            posicionesTotales += transformarApalabra(movimientoTorreBSoloAtaque(tableroM, i, j));
        } else if (ficha.equals("n_alfil")) {
            posicionesTotales += transformarApalabra(movimientoAlfilBSoloAtaque(tableroM, i, j));
        } else if (ficha.equals("n_reina")) {
            posicionesTotales += transformarApalabra(movimientoReinaBSoloAtaque(tableroM, i, j));
        } else if (ficha.equals("n_caballo")) {
            posicionesTotales += transformarApalabra(movimientoCaballoBSoloAtaque(tableroM, i, j));
        } else if (ficha.equals("n_rey")) {
            posicionesTotales += transformarApalabra(movimientoReyBSoloAtaque(tableroM, i, j));
        } else if (ficha.equals("n_peon")) {
            posicionesTotales += transformarApalabra(movimientoPeonBSoloAtaque(tableroM, i, j));
        }

        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    public String[] movimientoAtaqueFichaA(String[][] tableroM, int i, int j) {

        String posicionesTotales = "";

        String ficha = fichaDeLaCasilla(tableroM, i, j);

        if (ficha.equals("b_torre")) {
            posicionesTotales += transformarApalabra(movimientoTorreASoloAtaque(tableroM, i, j));
        } else if (ficha.equals("b_alfil")) {
            posicionesTotales += transformarApalabra(movimientoAlfilASoloAtaque(tableroM, i, j));
        } else if (ficha.equals("b_reina")) {
            posicionesTotales += transformarApalabra(movimientoReinaASoloAtaque(tableroM, i, j));
        } else if (ficha.equals("b_caballo")) {
            posicionesTotales += transformarApalabra(movimientoCaballoASoloAtaque(tableroM, i, j));
        } else if (ficha.equals("b_rey")) {
            posicionesTotales += transformarApalabra(movimientoReyASoloAtaque(tableroM, i, j));
        } else if (ficha.equals("b_peon")) {
            posicionesTotales += transformarApalabra(movimientoPeonASoloAtaque(tableroM, i, j));
        }

        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    public String[] todosLosMovimientosFichasA(String[][] tableroM) {

        String posicionesTotales = "";

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String ficha = fichaDeLaCasilla(tableroM, i, j);

                if (ficha.equals("b_torre")) {
                    posicionesTotales += transformarApalabra(movimientoTorreASoloAtaque(tableroM, i, j));
                } else if (ficha.equals("b_alfil")) {
                    posicionesTotales += transformarApalabra(movimientoAlfilASoloAtaque(tableroM, i, j));
                } else if (ficha.equals("b_reina")) {
                    posicionesTotales += transformarApalabra(movimientoReinaASoloAtaque(tableroM, i, j));
                } else if (ficha.equals("b_caballo")) {
                    posicionesTotales += transformarApalabra(movimientoCaballoASoloAtaque(tableroM, i, j));
                } else if (ficha.equals("b_rey")) {
                    posicionesTotales += transformarApalabra(movimientoReyASoloAtaque(tableroM, i, j));
                } else if (ficha.equals("b_peon")) {
                    posicionesTotales += transformarApalabra(movimientoPeonASoloAtaque(tableroM, i, j));
                }

            }
        }
        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    private String transformarApalabra(String[] array) {
        String palabra = "";
        for (int i = 0; i < array.length; i++) {
            palabra += array[i] + "_";
        }
        return palabra;
    }

    public String[] movimientosFichas(String[][] tableroM, String posicion) {
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));
        String ficha = fichaDeLaCasilla(tableroM, y, x);
        if (ficha.equals("b_peon")) {
            return movimientoPeonA(tableroM, y, x);
        } else if (ficha.equals("n_peon")) {
            return movimientoPeonB(tableroM, y, x);
        } else if (ficha.equals("b_torre")) {
            return movimientoTorreA(tableroM, y, x);
        } else if (ficha.equals("n_torre")) {
            return movimientoTorreB(tableroM, y, x);
        } else if (ficha.equals("b_alfil")) {
            return movimientoAlfilA(tableroM, y, x);
        } else if (ficha.equals("n_alfil")) {
            return movimientoAlfilB(tableroM, y, x);
        } else if (ficha.equals("b_caballo")) {
            return movimientoCaballoA(tableroM, y, x);
        } else if (ficha.equals("n_caballo")) {
            return movimientoCaballoB(tableroM, y, x);
        } else if (ficha.equals("b_reina")) {
            return movimientoReinaA(tableroM, y, x);
        } else if (ficha.equals("n_reina")) {
            return movimientoReinaB(tableroM, y, x);
        } else if (ficha.equals("b_rey")) {
            return movimientoReyA(tableroM, y, x);
        } else if (ficha.equals("n_rey")) {
            return movimientoReyB(tableroM, y, x);
        }

        return null;
    }

    private String[] movimientoPeonA(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        if (tableroM[y - 1][x].equals("")) {
            posicionesPosibles += "" + (y - 1) + x + "_";
        }
        try {
            if (tableroM[y - 2][x].equals("") && y == 6) {
                posicionesPosibles += "" + (y - 2) + (x) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsnegra(tableroM, y - 1, x + 1)) {
                posicionesPosibles += "" + (y - 1) + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsnegra(tableroM, y - 1, x - 1)) {
                posicionesPosibles += "" + (y - 1) + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoPeonASoloAtaque(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";
        try {
            String forcarError = tableroM[y - 1][x + 1];
            posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
        } catch (Exception ex) {
        }
        try {
            String forcarError = tableroM[y - 1][x - 1];
            posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
        } catch (Exception ex) {
        }

        String array[] = posicionesPosibles.split("_");
        return array;

    }

    private String[] movimientoPeonBSoloAtaque(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        try {
            String forcarError = tableroM[y + 1][x + 1];
            posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
        } catch (Exception ex) {
        }

        try {
            String forcarError = tableroM[y + 1][x - 1];
            posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
        } catch (Exception ex) {
        }

        String array[] = posicionesPosibles.split("_");
        return array;

    }

    private String[] movimientoPeonB(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        if (tableroM[y + 1][x].equals("")) {
            posicionesPosibles += "" + (y + 1) + x + "_";
        }
        try {
            if (tableroM[y + 2][x].equals("") && y == 1 && tableroM[y + 1][x].equals("")) {
                posicionesPosibles += "" + (y + 2) + (x) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsBlanca(tableroM, y + 1, x - 1)) {
                posicionesPosibles += "" + (y + 1) + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsBlanca(tableroM, y + 1, x + 1)) {
                posicionesPosibles += "" + (y + 1) + (x + 1) + "_";
            }

        } catch (Exception ex) {
        }

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;
    }

    private String[] movimientoTorreA(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        //Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoTorreASoloAtaque(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        //Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;
    }

    private String[] movimientoTorreBSoloAtaque(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        //Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    posicionesPosibles += "" + i + "" + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    posicionesPosibles += "" + i + "" + x + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    posicionesPosibles += "" + i + "" + x + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoTorreB(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        //Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    posicionesPosibles += "" + i + "" + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoAlfilA(String[][] tableroM, int y, int x) {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoAlfilASoloAtaque(String[][] tableroM, int y, int x) {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoAlfilBSoloAtaque(String[][] tableroM, int y, int x) {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoAlfilB(String[][] tableroM, int y, int x) {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoCaballoA(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        try {
            //Movimiento arriba-iaquierda
            if (tableroM[y - 2][x - 1].equals("") || comprobarSiLaFichaEsnegra(tableroM, y - 2, x - 1)) {
                posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento arriba-derecha
            if (tableroM[y - 2][x + 1].equals("") || comprobarSiLaFichaEsnegra(tableroM, y - 2, x + 1)) {
                posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento abajo-izquierda
            if (tableroM[y + 2][x - 1].equals("") || comprobarSiLaFichaEsnegra(tableroM, y + 2, x - 1)) {
                posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento abajo-derecha
            if (tableroM[y + 2][x + 1].equals("") || comprobarSiLaFichaEsnegra(tableroM, y + 2, x + 1)) {
                posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento izquierda-arriba
            if (tableroM[y - 1][x - 2].equals("") || comprobarSiLaFichaEsnegra(tableroM, y - 1, x - 2)) {
                posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            // Movimiento izquierda-abajo
            if (tableroM[y + 1][x - 2].equals("") || comprobarSiLaFichaEsnegra(tableroM, y + 1, x - 2)) {
                posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento derecha-arriba
            if (tableroM[y - 1][x + 2].equals("") || comprobarSiLaFichaEsnegra(tableroM, y - 1, x + 2)) {
                posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento derecha-abajo
            if (tableroM[y + 1][x + 2].equals("") || comprobarSiLaFichaEsnegra(tableroM, y + 1, x + 2)) {
                posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";
            }
        } catch (Exception ex) {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoCaballoASoloAtaque(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x - 1];
            posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x + 1];
            posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x - 1];
            posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x + 1];
            posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x - 2];
            posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x - 2];
            posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x + 2];
            posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x + 2];
            posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";

        } catch (Exception ex) {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoCaballoBSoloAtaque(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x - 1];
            posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x + 1];
            posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x - 1];
            posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x + 1];
            posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x - 2];
            posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x - 2];
            posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x + 2];
            posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x + 2];
            posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";

        } catch (Exception ex) {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoCaballoB(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        try {
            //Movimiento arriba-iaquierda
            if (tableroM[y - 2][x - 1].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y - 2, x - 1)) {
                posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento arriba-derecha
            if (tableroM[y - 2][x + 1].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y - 2, x + 1)) {
                posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento abajo-izquierda
            if (tableroM[y + 2][x - 1].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y + 2, x - 1)) {
                posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento abajo-derecha
            if (tableroM[y + 2][x + 1].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y + 2, x + 1)) {
                posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento izquierda-arriba
            if (tableroM[y - 1][x - 2].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y - 1, x - 2)) {
                posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            // Movimiento izquierda-abajo
            if (tableroM[y + 1][x - 2].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y + 1, x - 2)) {
                posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento derecha-arriba
            if (tableroM[y - 1][x + 2].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y - 1, x + 2)) {
                posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento derecha-abajo
            if (tableroM[y + 1][x + 2].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y + 1, x + 2)) {
                posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";
            }
        } catch (Exception ex) {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoReinaA(String[][] tableroM, int y, int x) {
        String[] movimientoDiagonal = movimientoAlfilA(tableroM, y, x);
        String[] movimientoRecto = movimientoTorreA(tableroM, y, x);

        int numeroPosiciones = movimientoDiagonal.length + movimientoRecto.length;

        String[] movimientosReina = new String[numeroPosiciones];

        int n = 0;

        for (int i = 0; i < movimientoDiagonal.length; i++) {
            movimientosReina[n] = movimientoDiagonal[i];
            n++;
        }

        for (int i = 0; i < movimientoRecto.length; i++) {
            movimientosReina[n] = movimientoRecto[i];
            n++;
        }

        return movimientosReina;
    }

    private String[] movimientoReinaASoloAtaque(String[][] tableroM, int y, int x) {
        String[] movimientoDiagonal = movimientoAlfilASoloAtaque(tableroM, y, x);
        String[] movimientoRecto = movimientoTorreASoloAtaque(tableroM, y, x);

        int numeroPosiciones = movimientoDiagonal.length + movimientoRecto.length;

        String[] movimientosReina = new String[numeroPosiciones];

        int n = 0;

        for (int i = 0; i < movimientoDiagonal.length; i++) {
            movimientosReina[n] = movimientoDiagonal[i];
            n++;
        }

        for (int i = 0; i < movimientoRecto.length; i++) {
            movimientosReina[n] = movimientoRecto[i];
            n++;
        }

        return movimientosReina;
    }

    private String[] movimientoReinaBSoloAtaque(String[][] tableroM, int y, int x) {
        String[] movimientoDiagonal = movimientoAlfilBSoloAtaque(tableroM, y, x);
        String[] movimientoRecto = movimientoTorreBSoloAtaque(tableroM, y, x);

        int numeroPosiciones = movimientoDiagonal.length + movimientoRecto.length;

        String[] movimientosReina = new String[numeroPosiciones];

        int n = 0;

        for (int i = 0; i < movimientoDiagonal.length; i++) {
            movimientosReina[n] = movimientoDiagonal[i];
            n++;
        }

        for (int i = 0; i < movimientoRecto.length; i++) {
            movimientosReina[n] = movimientoRecto[i];
            n++;
        }

        return movimientosReina;
    }

    private String[] movimientoReinaB(String[][] tableroM, int y, int x) {
        String[] movimientoDiagonal = movimientoAlfilB(tableroM, y, x);
        String[] movimientoRecto = movimientoTorreB(tableroM, y, x);

        int numeroPosiciones = movimientoDiagonal.length + movimientoRecto.length;

        String[] movimientosReina = new String[numeroPosiciones];

        int n = 0;

        for (int i = 0; i < movimientoDiagonal.length; i++) {
            movimientosReina[n] = movimientoDiagonal[i];
            n++;
        }

        for (int i = 0; i < movimientoRecto.length; i++) {
            movimientosReina[n] = movimientoRecto[i];
            n++;
        }

        return movimientosReina;
    }

    private String[] movimientoReyA(String[][] tableroM, int y, int x) {

        String posicionesPosibles = "";

        try {
            if (comprobarSiLaFichaEsBlanca(tableroM, y - 1, x - 1) == false) {
                posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsBlanca(tableroM, y - 1, x) == false) {
                posicionesPosibles += "" + (y - 1) + "" + (x) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsBlanca(tableroM, y - 1, x + 1) == false) {
                posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsBlanca(tableroM, y, x - 1) == false) {
                posicionesPosibles += "" + (y) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsBlanca(tableroM, y, x + 1) == false) {
                posicionesPosibles += "" + (y) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsBlanca(tableroM, y + 1, x - 1) == false) {
                posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsBlanca(tableroM, y + 1, x) == false) {
                posicionesPosibles += "" + (y + 1) + "" + (x) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsBlanca(tableroM, y + 1, x + 1) == false) {
                posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        //Enrroque 
        if (Controlador.enrroqueReyA == true) {
            if (tableroM[7][1].equals("") && tableroM[7][2].equals("") && tableroM[7][3].equals("") && Controlador.enrroqueTorreIzquierdaA == true) {
                posicionesPosibles += "" + "72" + "_";
            }
            if (tableroM[7][5].equals("") && tableroM[7][6].equals("") && Controlador.enrroqueTorreDerechaA == true) {
                posicionesPosibles += "" + "76" + "_";
            }
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoReyASoloAtaque(String[][] tableroM, int y, int x) {

        String posicionesPosibles = "";

        if (y > 0 && x > 0) {
            posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
        }

        try {
            if (y > 0 && x < 7) {
                posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {

        }

        try {
            if (y > 0) {
                posicionesPosibles += "" + (y - 1) + "" + (x) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (x > 0) {
                posicionesPosibles += "" + (y) + "" + (x - 1) + "_";
            }

        } catch (Exception ex) {

        }

        try {
            if (x < 7) {
                posicionesPosibles += "" + (y) + "" + (x + 1) + "_";
            }

        } catch (Exception ex) {

        }

        try {
            if (y < 7 && x > 0) {
                posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
            }

        } catch (Exception ex) {

        }

        try {
            if (y < 7) {
                posicionesPosibles += "" + (y + 1) + "" + (x) + "_";
            }

        } catch (Exception ex) {

        }

        try {
            if (y < 7 && x < 7) {
                posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
            }

        } catch (Exception ex) {

        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoReyBSoloAtaque(String[][] tableroM, int y, int x) {

        String posicionesPosibles = "";

        if (y > 0 && x > 0) {
            posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
        }

        try {
            if (y > 0) {
                posicionesPosibles += "" + (y) + "" + (x - 1) + "_";

            }

        } catch (Exception ex) {

        }

        try {
            if (y > 0 && x < 7) {
                posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {

        }

        try {
            if (x > 0) {
                posicionesPosibles += "" + (y) + "" + (x - 1) + "_";
            }

        } catch (Exception ex) {

        }

        try {
            if (x < 7) {
                posicionesPosibles += "" + (y) + "" + (x + 1) + "_";
            }

        } catch (Exception ex) {

        }

        try {
            if (y < 7 && x > 7) {
                posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
            }

        } catch (Exception ex) {

        }

        try {
            if (y < 7) {
                posicionesPosibles += "" + (y + 1) + "" + (x) + "_";
            }

        } catch (Exception ex) {

        }

        try {
            if (y < 7 && x < 7) {
                posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
            }

        } catch (Exception ex) {

        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoReyB(String[][] tableroM, int y, int x) {

        String posicionesPosibles = "";

        try {
            if (comprobarSiLaFichaEsnegra(tableroM, y - 1, x - 1) == false) {
                posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsnegra(tableroM, y - 1, x) == false) {
                posicionesPosibles += "" + (y - 1) + "" + (x) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsnegra(tableroM, y - 1, x + 1) == false) {
                posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsnegra(tableroM, y, x - 1) == false) {
                posicionesPosibles += "" + (y) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsnegra(tableroM, y, x + 1) == false) {
                posicionesPosibles += "" + (y) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsnegra(tableroM, y + 1, x - 1) == false) {
                posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsnegra(tableroM, y + 1, x) == false) {
                posicionesPosibles += "" + (y + 1) + "" + (x) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsnegra(tableroM, y + 1, x + 1) == false) {
                posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        //Enrroque
        
        if (Controlador.enrroqueReyB == true) {
            if (tableroM[0][1].equals("") && tableroM[0][2].equals("") && tableroM[0][3].equals("") && Controlador.enrroqueTorreIzquierdaB == true) {
                posicionesPosibles += "" + "02" + "_";
            }
            if (tableroM[0][5].equals("") && tableroM[0][6].equals("") && Controlador.enrroqueTorreDerechaB == true) {
                posicionesPosibles += "" + "06" + "_";
            }
        }
         
        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String fichaDeLaCasilla(String[][] tableroM, int y, int x) {
        return tableroM[y][x];
    }

    private boolean comprobarSiLaFichaEsnegra(String[][] tableroM, int y, int x) {
        if (!tableroM[y][x].equals("")) {
            return (tableroM[y][x].charAt(0) == 'n') ? true : false;
        }
        return false;
    }

    private boolean comprobarSiLaFichaEsBlanca(String[][] tableroM, int y, int x) {
        if (!tableroM[y][x].equals("")) {
            return (tableroM[y][x].charAt(0) == 'b') ? true : false;
        }
        return false;
    }

    private boolean reyAenJaque(String[][] tableroM) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fichaDeLaCasilla(tableroM, i, j).equals("b_rey")) {
                    String posicionRey = "" + i + "" + j;

                    String[] movimientosEnemigos = todosLosMovimientosFichasB(tableroM);

                    for (int x = 0; x < movimientosEnemigos.length; x++) {
                        if (movimientosEnemigos[x].equals(posicionRey)) {
                            return true;
                        }
                    }
                    return false;

                }
            }
        }
        return false;
    }

    private boolean reyBenJaque(String[][] tableroM) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fichaDeLaCasilla(tableroM, i, j).equals("n_rey")) {
                    String posicionRey = "" + i + "" + j;

                    String[] movimientosEnemigos = todosLosMovimientosFichasA(tableroM);

                    for (int x = 0; x < movimientosEnemigos.length; x++) {
                        if (movimientosEnemigos[x].equals(posicionRey)) {
                            return true;
                        }
                    }
                    return false;

                }
            }
        }
        return false;
    }

    /*
    private String[] modificacionMovimientosReyA(String[] posiblesMovimientos) {
        String posicionesFinales = "";
        String movimientosEnemigos[] = todosLosMovimientosFichasB(tablero);
        for (int i = 0; i < posiblesMovimientos.length; i++) {
            boolean amenazado = false;
            for (int j = 0; j < movimientosEnemigos.length; j++) {
                if (movimientosEnemigos[j].equals(posiblesMovimientos[i])) {
                    amenazado = true;
                }
            }
            if (amenazado == false) {
                posicionesFinales += posiblesMovimientos[i] + "_";
            }
        }
        String[] arrayTotal = posicionesFinales.split("_");
        return arrayTotal;
    }
     */
 /*
    private String[] modificacionMovimientosReyB(String[] posiblesMovimientos) {
        String posicionesFinales = "";
        String movimientosEnemigos[] = todosLosMovimientosFichasA(tablero);
        for (int i = 0; i < posiblesMovimientos.length; i++) {
            boolean amenazado = false;
            for (int j = 0; j < movimientosEnemigos.length; j++) {
                if (movimientosEnemigos[j].equals(posiblesMovimientos[i])) {
                    amenazado = true;
                }
            }
            if (amenazado == false) {
                posicionesFinales += posiblesMovimientos[i] + "_";
            }
        }
        String[] arrayTotal = posicionesFinales.split("_");
        return arrayTotal;
    }
     */
}

