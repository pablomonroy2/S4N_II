package co.com.s4n.test;
import static org.junit.Assert.*;

import org.junit.Test;

import co.com.s4n.modelo.Coordenada;
import co.com.s4n.modelo.Dron;
import co.com.s4n.util.Constantes;


public class DronTest {
	
	private static final Dron DRON = new Dron();

	@Test
	public void testGiroIzquierda1() {
		int resultado = DRON.cambiarOrientacion(Constantes.NORTE, "I");
		int esperado = Constantes.OCCIDENTE ;
		assertEquals(esperado, resultado);
	}
	
	@Test
	public void testGiroIzquierda2() {
		int resultado = DRON.cambiarOrientacion(Constantes.SUR, "I");
		int esperado = Constantes.ORIENTE ;
		assertEquals(esperado, resultado);
	}
	
	@Test
	public void testGiroDerecha1() {
		int resultado = DRON.cambiarOrientacion(Constantes.OCCIDENTE, "D");
		int esperado = Constantes.NORTE ;
		assertEquals(esperado, resultado);
	}
	
	@Test
	public void testGiroDerecha2() {
		int resultado = DRON.cambiarOrientacion(Constantes.ORIENTE, "D");
		int esperado = Constantes.SUR ;
		assertEquals(esperado, resultado);
	}
	
	@Test
	public void testMovimintoXPositivo() {
		Coordenada posicion = new Coordenada(1,0,Constantes.ORIENTE);
		posicion = DRON.movimiento(posicion);
		assertEquals(2, posicion.getX());
	}
	
	@Test
	public void testMovimintoXNegativo() {
		Coordenada posicion = new Coordenada(0,0,Constantes.OCCIDENTE);
		posicion = DRON.movimiento(posicion);
		assertEquals(-1, posicion.getX());
	}
	
	@Test
	public void testMovimintoYPositivo() {
		Coordenada posicion = new Coordenada(0,1,Constantes.NORTE);
		posicion = DRON.movimiento(posicion);
		assertEquals(2, posicion.getY());
	}
	
	@Test
	public void testMovimintoYNegativo() {
		Coordenada posicion = new Coordenada(0,0,Constantes.SUR);
		posicion = DRON.movimiento(posicion);
		assertEquals(-1, posicion.getY());
	}
	
	@Test
	public void testDronFueraDeRango1(){
		assertFalse(DRON.validarRango(new Coordenada(Constantes.RANGO_DRON +1 ,
													 0,
													 Constantes.NORTE)));
	}
	
	@Test
	public void testDronFueraDeRango2(){
		assertFalse(DRON.validarRango(new Coordenada(0,
													 Constantes.RANGO_DRON +1,
													 Constantes.NORTE)));
	}
	
	@Test
	public void testDronEnRango(){
		assertTrue(DRON.validarRango( new Coordenada(Constantes.RANGO_DRON,
													 Constantes.RANGO_DRON,
													 Constantes.NORTE)));
	}
	
	@Test
	public void testEntrega(){	
		Coordenada resultado = new Coordenada(0,0,Constantes.NORTE);
		Dron dron = new Dron();
		dron.setPosicionActual(resultado);
		resultado = dron.entrega("AAAAIAAD");
		if ( -2 ==  resultado.getX() && 
			  4 ==  resultado.getY() && 
			 Constantes.NORTE == resultado.getOrientacion()){
			assertTrue(true);
		}else{
			assertTrue(false);
		}	
	}
	
	@Test
	public void testEntregaCasoErroneo1(){	
		Coordenada resultado = new Coordenada(-2,4,Constantes.NORTE);
		Dron dron = new Dron();
		dron.setPosicionActual(resultado);
		resultado = dron.entrega("DDAIAD");

		if ( -3 ==  resultado.getX() && 
			  3 ==  resultado.getY() && 
			 Constantes.SUR == resultado.getOrientacion()){
			assertTrue(true);
		}else{
			assertTrue(false);
		}	
	}
	
	@Test
	public void testEntregaCasoErroneo2(){	
		Coordenada resultado = new Coordenada(-3,3,Constantes.SUR);
		Dron dron = new Dron();
		dron.setPosicionActual(resultado);
		resultado = dron.entrega("AAIADAD");
		
		if ( -4 ==  resultado.getX() && 
			  2 ==  resultado.getY() && 
			 Constantes.ORIENTE == resultado.getOrientacion()){
			assertTrue(true);
		}else{
			assertTrue(false);
		}	
	}

}
