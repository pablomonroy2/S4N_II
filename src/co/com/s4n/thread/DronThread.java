package co.com.s4n.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import co.com.s4n.modelo.Coordenada;
import co.com.s4n.modelo.Dron;
import co.com.s4n.util.Constantes;

public class DronThread extends Thread {
	
	private Stream<String> moves;
	private int id;
	
	public DronThread() {
		
	}
	
	/**
	 * @param moves
	 * @param id
	 */
	public DronThread(Stream<String> moves, int id) {
		this.moves = moves;
		this.id = id;
	}
	
	/* 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		ArrayList<Coordenada> coordenadas = new ArrayList<Coordenada>();
		Dron dron = new Dron();
		moves.limit(Constantes.LIMITE_ENTREGAS).forEach(linea ->{
			coordenadas.add( dron.entrega(linea) );
		});
		try {
			dron.reporte(coordenadas,id); 
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
}
