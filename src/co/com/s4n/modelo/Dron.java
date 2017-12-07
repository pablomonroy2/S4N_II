/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.s4n.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import co.com.s4n.interfaz.IDron;
import co.com.s4n.util.Constantes;

/**
 *
 * @author PABLO
 */


public class Dron extends Thread implements IDron {

	private Coordenada posicionActual;
	
	public Dron() {
		this.posicionActual = new Coordenada(0,0,Constantes.NORTE);
	}
	
	/**
	 * @return
	 */
	public Coordenada getPosicionActual() {
		return posicionActual;
	}

	/**
	 * @param posicionActual
	 */
	public void setPosicionActual(Coordenada posicionActual) {
		this.posicionActual = posicionActual;
	}

	/* 
	 * @see co.com.s4n.interfaz.IDron#entrega(java.lang.String)
	 */
	public Coordenada entrega(String movimientos){
        
		Coordenada posicion = new Coordenada(this.posicionActual.getX(),
											 this.posicionActual.getY(), 
											 this.posicionActual.getOrientacion()); 
		
        Stream<Character> moves = movimientos.chars().mapToObj(i -> (char)i);       
        moves.forEach(move -> {
            if( "I".equals(Character.toString(move)) || "D".equals(Character.toString(move))){
            	posicion.setOrientacion(cambiarOrientacion(posicion.getOrientacion(), Character.toString(move)));
            }else
            if( "A".equals(Character.toString(move)) ){               
            	Coordenada aux = movimiento(posicion);
            	posicion.setX(aux.getX());
            	posicion.setY(aux.getY());
            }	
        });     
        this.posicionActual = posicion;
        return posicion;
    }
	
	
	/**
	 * @param posicion
	 * @return
	 */
	public Coordenada movimiento(Coordenada posicion){
		switch (posicion.getOrientacion()){
	        case Constantes.NORTE :
	        	posicion.setY(posicion.getY()+1);
	        	break;
	        case Constantes.SUR :
	        	posicion.setY(posicion.getY()-1);
	        	break;
	        case Constantes.ORIENTE :
	        	posicion.setX(posicion.getX()+1);
	        	break;
	        case Constantes.OCCIDENTE :
	        	posicion.setX(posicion.getX()-1);
	        	break;
	    }		
		return posicion;
	}
	
	/* 
	 * @see co.com.s4n.interfaz.IDron#reporte(java.util.ArrayList, int)
	 */
	public void reporte(ArrayList<Coordenada> entregas,int id)throws IOException{     
       
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Files/outFiles/out"+(id<10?"0"+id:id)+".txt")));
        bw.write("== Reporte de entregas ==");
        entregas.stream().forEach(c -> {
        	try {
				if ( validarRango(c) ){
					bw.write("\n(" + c.getX() + ", " + c.getY() + ") dirección " + c.getOrientacionName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        }) ;         
        bw.close();  
    }
	
	/**
	 * @param oldOrientation
	 * @param move
	 * @return
	 */
	public int cambiarOrientacion(int oldOrientation, String move){       
        int newOrientation = "I".equals(move) ?  oldOrientation - 1 : oldOrientation + 1;   
        return newOrientation > 4 ? 1 : newOrientation < 1 ? 4 : newOrientation;
    }
	
	/**
	 * @param coordenada
	 * @return
	 */
	public boolean validarRango(Coordenada coordenada){	
		if(coordenada.getX() > Constantes.RANGO_DRON || coordenada.getX() < (Constantes.RANGO_DRON*-1)  ||
		   coordenada.getY() > Constantes.RANGO_DRON || coordenada.getY() < (Constantes.RANGO_DRON*-1)	){
			return false;
		}else{
			return true;
		}	
	}
}
