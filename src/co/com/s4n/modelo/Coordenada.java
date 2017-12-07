package co.com.s4n.modelo;

import co.com.s4n.util.Constantes;

/**
 *
 * @author PABLO
 */

public class Coordenada {
    
    private int x;
    private int y;
    private int orientacion;

    public Coordenada(){
    }
    
    /**
     *
     * @param x
     * @param y
     * @param orientacion
     */
    public Coordenada(int x, int y, int orientacion) {
        this.x = x;
        this.y = y;
        this.orientacion = orientacion;
    }

    /**
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return
     */
    public int getOrientacion() {
        return orientacion;
    }

    /**
     * @param orientacion
     */
    public void setOrientacion(int orientacion) {
        this.orientacion = orientacion;
    }
       
    /**
     * @return
     */
    public String getOrientacionName(){
        switch (this.orientacion) {
            case 1 :
                return Constantes.NORTE_NAME;
            case 2 :
                return Constantes.ORIENTE_NAME;
            case 3 : 
                return Constantes.SUR_NAME;
            case 4 : 
                return Constantes.OCCIDENTE_NAME;
            default :
                return null;
        }
    }
}
