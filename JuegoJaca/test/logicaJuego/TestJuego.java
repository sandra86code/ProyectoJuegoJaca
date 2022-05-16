package logicaJuego;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import elementos.Coordenada;
import elementos.Jugador;
import elementos.PlayerType;

class TestJuego {


	public Juego listaJugadoresTerminadoUnJugador() {
		PlayerType[] jugadores = {PlayerType.ELFO, PlayerType.GUERRERO, PlayerType.MAGO, PlayerType.OGRO};
		return new Juego(jugadores);
	}
	
	@Test
	public void testImprimeNombreJugadores() {
		Juego juego = listaJugadoresTerminadoUnJugador();
		assertEquals("El jugador 1 es un ELFO\nEl jugador 2 es un GUERRERO\nEl jugador 3 es un MAGO\n"
				+ "El jugador 4 es un OGRO\n", juego.imprimeNombreJugadores());
	}
	
	@Test
	public void testImprimeValoresJugadores() {
		Juego juego = listaJugadoresTerminadoUnJugador();
		assertEquals("Jugador: ELFO Dinero: 0 Gemas: 0 Pociones: 0\n"
				+ "Jugador: GUERRERO Dinero: 0 Gemas: 0 Pociones: 0\n"
				+ "Jugador: MAGO Dinero: 0 Gemas: 0 Pociones: 0\n"
				+ "Jugador: OGRO Dinero: 0 Gemas: 0 Pociones: 0\n", juego.imprimeValoresJugadores());
	}
	
	
//	@Test
//	public void testMovePlayerArriba() throws JuegoException, JugadorException {
//
//	}
	
//	@Test
//	public void testMovePlayerAbajo() {
//	
//	}
//	
//	@Test
//	public void testMovePlayerDerecha() {
//	
//	}
//	
//	@Test
//	public void testMovePlayerIzquierda() {
//	
//	}
	
	
	@Test
	public void testNombreJugadorQueJuegaUltimaPosicion() {
		Juego juego = listaJugadoresTerminadoUnJugador();
		juego.proximoJugador();
		juego.proximoJugador();
		juego.proximoJugador();
		assertEquals("OGRO", juego.getNombreJuegadorQueJuega());
	}
	
	@Test
	public void testNombreJugadorQueJuegaPrimeraPosicion() {
		Juego juego = listaJugadoresTerminadoUnJugador();
		juego.proximoJugador();
		juego.proximoJugador();
		juego.proximoJugador();
		juego.proximoJugador();
		assertEquals("ELFO", juego.getNombreJuegadorQueJuega());
	}
	
	
	@Test
	public void testGetGanadorIncorrecto() {
		Juego juego = listaJugadoresTerminadoUnJugador();
		assertEquals(null, juego.getGanador());
		
	}
	
//	@Test
//	public void testGetGanadorQuedaUno() {
//		Juego juego = listaJugadoresTerminadoUnJugador();
//		
//	}
	
	
	@Test
	public void testGetGanadorTodoElDinero() {
		Juego juego = listaJugadoresTerminadoUnJugador();
		Coordenada coordenada = juego.obtenerCoordenadaJugadorJuega();
		Jugador jugador = ((Jugador)juego.obtenerElementoTablero​(coordenada));
		for(int i=0; i<Constantes.NUM_DINERO; i++) {
			jugador.encuentraDinero();
		}
		assertEquals("Jugador: ELFO Dinero: 2 Gemas: 0 Pociones: 0", juego.getGanador());
	}
	
	@Test
	public void testValorDadoCorrecto() {
		Juego juego = listaJugadoresTerminadoUnJugador();
		juego.setDado();
		for(int i=0; i<200; i++) {
			assertTrue(juego.getValorDado()>=1 && juego.getValorDado()<=Constantes.ELFO_VELOCIDAD);
		}
	}
	
	@Test
	public void testDecrementaDado() {
		Juego juego = listaJugadoresTerminadoUnJugador();
		juego.setDado();
		int dado = juego.getValorDado();
		juego.decrementaDado();
		assertEquals(dado-1, juego.getValorDado());
	}
	
}
