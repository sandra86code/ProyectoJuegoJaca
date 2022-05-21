package test.logicajuego;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import elementos.Coordenada;
import elementos.Element;
import elementos.ElementType;
import elementos.Jugador;
import elementos.JugadorException;
import elementos.PlayerType;
import logicajuego.Constantes;
import logicajuego.Juego;
import logicajuego.JuegoException;

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
	
	@Test
	public void testGetGanadorQuedaUno() {
		PlayerType[] jugadores = {PlayerType.GUERRERO};
		Juego juego = new Juego(jugadores);
		assertEquals("Jugador: GUERRERO Dinero: 0 Gemas: 0 Pociones: 0", juego.getGanador());
	}
	
	
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
		for(int i=0; i<200; i++) {
			juego.setDado();
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
	
	@Test
	public void testMovePlayerErrorNoValido() {
		Juego juego = listaJugadoresTerminadoUnJugador();
		try {
			juego.movePlayer('.');
			fail("Error. La excepción no ha sido lanzada.");
		} catch (JuegoException | JugadorException e) {
			System.out.println("Excepción correcta: " + e.getMessage());
		}
		
	}
	
	@Test
	public void testMovePlayerNorteConPocionesGemasYDinero() {
		for(int i=0; i<5000; i++) {
			//Creo el juego
			Juego juego = listaJugadoresTerminadoUnJugador();
			//Obtengo coordenada jugador que juega
			Coordenada coordenada = juego.obtenerCoordenadaJugadorJuega();
			//Obtengo jugador
			Jugador jugador = ((Jugador)juego.obtenerElementoTablero​(coordenada));
			//Asigno al jugador una gema, un dinero y una poción
			jugador.encuentraGema();
			jugador.encuentraDinero();
			jugador.encuentraPocion();
			//Obtengo la coordenada a la que se mueve
			Coordenada coordenadaN = new Coordenada(coordenada.getX(), coordenada.getY()-1);
			//Solo si las coordenadas son diferentes, es decir, el jugador no está en el borde superior del tablero, ejecuto las pruebas
			if(!coordenada.equals(coordenadaN)) {
				//Compruebo si hay un elemento en la coordenada a la que se mueve y la guardo
				Element elementoN = juego.obtenerElementoTablero​(coordenadaN);
				//Si el elemento no es nulo y el elemento es un jugador
				if(elementoN!=null && elementoN instanceof Jugador) {
					//Guardo el jugador
					Jugador enemigo = (Jugador) elementoN;
					//Asigno al jugador una gema, un dinero y una poción
					enemigo.encuentraGema();
					enemigo.encuentraDinero();
					enemigo.encuentraPocion();
					//Guardo los posibles resultados
					String resultadoEmpate = "Empate entre los jugadores";
					String resultadoGanaUsaPocima = "El jugador " + jugador.getNombre() + " gana. El enemigo pierde una pócima.";
					String resultadoGanaDinero = "El jugador " + jugador.getNombre() + " gana. Le quita el dinero al enemigo";
					String resultadoPierdeUsaPocima = "El enemigo " + enemigo.getNombre() + " gana. El jugador pierde una pócima.";
					String resultadoPierdeDinero = "El enemigo " + enemigo.getNombre() + " gana. Le quita el dinero al jugador";
					try {
						String resultado = juego.movePlayer('N');
						assertTrue(resultado.equals(resultadoEmpate) || (resultado.equals(resultadoGanaUsaPocima) && jugador.getPociones()==1 && enemigo.getPociones()==0) 
								|| (resultado.equals(resultadoGanaDinero) && jugador.getDinero()==2 && enemigo.getDinero()==0 && juego.isTerminado() && juego.getGanador().equals("Jugador: ELFO Dinero: 2 Gemas: 1 Pociones: 1"))
								|| (resultado.equals(resultadoPierdeUsaPocima) && jugador.getPociones()==0 && enemigo.getPociones()==1)
								|| (resultado.equals(resultadoPierdeDinero) && jugador.getDinero()==0 && enemigo.getDinero()==2 && juego.isTerminado() 
								&& juego.getGanador().equalsIgnoreCase("Jugador: " + enemigo.getNombre() + " Dinero: 2 Gemas: 1 Pociones: 1")));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoN!=null && elementoN.getType()==ElementType.ROCA) {
					String resultadoRompeRocaGema = "El jugador " + jugador.getNombre() + " rompe la roca con una gema";
					try {
						String resultado = juego.movePlayer('N');
						assertTrue((resultado.equals(resultadoRompeRocaGema) && jugador.getGemas()==0 && jugador.equals(juego.obtenerElementoTablero​(coordenadaN))));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoN!=null && elementoN.getType()==ElementType.GEMA) {
					String resultado;
					try {
						resultado = juego.movePlayer('N');
						assertTrue(resultado.equals("") && jugador.getGemas()==2 && jugador.equals(juego.obtenerElementoTablero​(coordenadaN)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoN!=null && elementoN.getType()==ElementType.POCION) {
					String resultado;
					try {
						resultado = juego.movePlayer('N');
						assertTrue(resultado.equals("") && jugador.getPociones()==2 && jugador.equals(juego.obtenerElementoTablero​(coordenadaN)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoN!=null && elementoN.getType()==ElementType.DINERO) {
					String resultado;
					try {
						resultado = juego.movePlayer('N');
						assertTrue(resultado.equals("") && jugador.getDinero()==2 && jugador.equals(juego.obtenerElementoTablero​(coordenadaN)) &&
								juego.isTerminado() && juego.getGanador().equals("Jugador: ELFO Dinero: 2 Gemas: 1 Pociones: 1"));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else {
					String resultado;
					try {
						resultado = juego.movePlayer('N');
						assertTrue(resultado.equals("") && jugador.getDinero()==1 && jugador.getPociones()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaN)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}
			}
		}
	}
	
	
	@Test
	public void testMovePlayerNorteSinPocionesGemasYDinero() {
		for(int i=0; i<5000; i++) {
			//Creo el juego
			Juego juego = listaJugadoresTerminadoUnJugador();
			//Obtengo coordenada jugador que juega
			Coordenada coordenada = juego.obtenerCoordenadaJugadorJuega();
			//Obtengo jugador
			Jugador jugador = ((Jugador)juego.obtenerElementoTablero​(coordenada));
			//Obtengo la coordenada a la que se mueve
			Coordenada coordenadaN = new Coordenada(coordenada.getX(), coordenada.getY()-1);
			//Solo si las coordenadas son diferentes, es decir, el jugador no está en el borde superior del tablero, ejecuto las pruebas
			if(!coordenada.equals(coordenadaN)) {
				//Compruebo si hay un elemento en la coordenada a la que se mueve y la guardo
				Element elementoN = juego.obtenerElementoTablero​(coordenadaN);
				//Si el elemento no es nulo y el elemento es un jugador
				if(elementoN!=null && elementoN instanceof Jugador) {
					//Guardo el jugador
					Jugador enemigo = (Jugador) elementoN;
					//Guardo los posibles resultados
					String resultadoEmpate = "Empate entre los jugadores";
					String resultadoGanaMuere = "El jugador " + jugador.getNombre() + " gana. El enemigo muere";
					String resultadoPierdeMuere = "El enemigo " + enemigo.getNombre() + " gana. El jugador muere";
					try {
						String resultado = juego.movePlayer('N');
						assertTrue(resultado.equals(resultadoEmpate) || 
								(resultado.equals(resultadoGanaMuere) && juego.obtenerElementoTablero​(coordenadaN)==null) ||
								(resultado.equals(resultadoPierdeMuere) && juego.obtenerElementoTablero​(coordenada)==null));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoN!=null && elementoN.getType()==ElementType.ROCA) {
					String resultadoGanaARoca = "El jugador " + jugador.getNombre() + " gana a la roca";
					String resultadoPierdeARoca = "El jugador " + jugador.getNombre() + " pierde. No se mueve";
					try {
						String resultado = juego.movePlayer('N');
						assertTrue(resultado.equals(resultadoGanaARoca) && jugador.equals(juego.obtenerElementoTablero​(coordenadaN))
								|| (resultado.equals(resultadoPierdeARoca) && jugador.equals(juego.obtenerElementoTablero​(coordenada))));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoN!=null && elementoN.getType()==ElementType.GEMA) {
					String resultado;
					try {
						resultado = juego.movePlayer('N');
						assertTrue(resultado.equals("") && jugador.getGemas()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaN)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoN!=null && elementoN.getType()==ElementType.POCION) {
					String resultado;
					try {
						resultado = juego.movePlayer('N');
						assertTrue(resultado.equals("") && jugador.getPociones()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaN)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoN!=null && elementoN.getType()==ElementType.DINERO) {
					String resultado;
					try {
						resultado = juego.movePlayer('N');
						assertTrue(resultado.equals("") && jugador.getDinero()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaN)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else {
					String resultado;
					try {
						resultado = juego.movePlayer('N');
						assertTrue(resultado.equals("") && jugador.getDinero()==0 && jugador.getPociones()==0 && jugador.equals(juego.obtenerElementoTablero​(coordenadaN)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}
			}
		}
	}
	
	
	@Test
	public void testMovePlayerSurConPocionesGemasYDinero() {
		for(int i=0; i<5000; i++) {
			//Creo el juego
			Juego juego = listaJugadoresTerminadoUnJugador();
			//Obtengo coordenada jugador que juega
			Coordenada coordenada = juego.obtenerCoordenadaJugadorJuega();
			//Obtengo jugador
			Jugador jugador = ((Jugador)juego.obtenerElementoTablero​(coordenada));
			//Asigno al jugador una gema, un dinero y una poción
			jugador.encuentraGema();
			jugador.encuentraDinero();
			jugador.encuentraPocion();
			//Obtengo la coordenada a la que se mueve
			Coordenada coordenadaS = new Coordenada(coordenada.getX(), coordenada.getY()+1);
			//Solo si las coordenadas son diferentes, es decir, el jugador no está en el borde inferior del tablero, ejecuto las pruebas
			if(!coordenada.equals(coordenadaS)) {
				//Compruebo si hay un elemento en la coordenada a la que se mueve y la guardo
				Element elementoS = juego.obtenerElementoTablero​(coordenadaS);
				//Si el elemento no es nulo y el elemento es un jugador
				if(elementoS!=null && elementoS instanceof Jugador) {
					//Guardo el jugador
					Jugador enemigo = (Jugador) elementoS;
					//Asigno al jugador una gema, un dinero y una poción
					enemigo.encuentraGema();
					enemigo.encuentraDinero();
					enemigo.encuentraPocion();
					//Guardo los posibles resultados
					String resultadoEmpate = "Empate entre los jugadores";
					String resultadoGanaUsaPocima = "El jugador " + jugador.getNombre() + " gana. El enemigo pierde una pócima.";
					String resultadoGanaDinero = "El jugador " + jugador.getNombre() + " gana. Le quita el dinero al enemigo";
					String resultadoPierdeUsaPocima = "El enemigo " + enemigo.getNombre() + " gana. El jugador pierde una pócima.";
					String resultadoPierdeDinero = "El enemigo " + enemigo.getNombre() + " gana. Le quita el dinero al jugador";
					try {
						String resultado = juego.movePlayer('S');
						assertTrue(resultado.equals(resultadoEmpate) || (resultado.equals(resultadoGanaUsaPocima) && jugador.getPociones()==1 && enemigo.getPociones()==0) 
								|| (resultado.equals(resultadoGanaDinero) && jugador.getDinero()==2 && enemigo.getDinero()==0 && juego.isTerminado() && juego.getGanador().equals("Jugador: ELFO Dinero: 2 Gemas: 1 Pociones: 1"))
								|| (resultado.equals(resultadoPierdeUsaPocima) && jugador.getPociones()==0 && enemigo.getPociones()==1)
								|| (resultado.equals(resultadoPierdeDinero) && jugador.getDinero()==0 && enemigo.getDinero()==2 && juego.isTerminado() 
								&& juego.getGanador().equalsIgnoreCase("Jugador: " + enemigo.getNombre() + " Dinero: 2 Gemas: 1 Pociones: 1")));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoS!=null && elementoS.getType()==ElementType.ROCA) {
					String resultadoRompeRocaGema = "El jugador " + jugador.getNombre() + " rompe la roca con una gema";
					try {
						String resultado = juego.movePlayer('S');
						assertTrue((resultado.equals(resultadoRompeRocaGema) && jugador.getGemas()==0 && jugador.equals(juego.obtenerElementoTablero​(coordenadaS))));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoS!=null && elementoS.getType()==ElementType.GEMA) {
					String resultado;
					try {
						resultado = juego.movePlayer('S');
						assertTrue(resultado.equals("") && jugador.getGemas()==2 && jugador.equals(juego.obtenerElementoTablero​(coordenadaS)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoS!=null && elementoS.getType()==ElementType.POCION) {
					String resultado;
					try {
						resultado = juego.movePlayer('S');
						assertTrue(resultado.equals("") && jugador.getPociones()==2 && jugador.equals(juego.obtenerElementoTablero​(coordenadaS)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoS!=null && elementoS.getType()==ElementType.DINERO) {
					String resultado;
					try {
						resultado = juego.movePlayer('S');
						assertTrue(resultado.equals("") && jugador.getDinero()==2 && jugador.equals(juego.obtenerElementoTablero​(coordenadaS)) &&
								juego.isTerminado() && juego.getGanador().equals("Jugador: ELFO Dinero: 2 Gemas: 1 Pociones: 1"));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else {
					String resultado;
					try {
						resultado = juego.movePlayer('S');
						assertTrue(resultado.equals("") && jugador.getDinero()==1 && jugador.getPociones()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaS)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}
			}
		}
	}
	
	
	@Test
	public void testMovePlayerSurSinPocionesGemas() {
		for(int i=0; i<5000; i++) {
			//Creo el juego
			Juego juego = listaJugadoresTerminadoUnJugador();
			//Obtengo coordenada jugador que juega
			Coordenada coordenada = juego.obtenerCoordenadaJugadorJuega();
			//Obtengo jugador
			Jugador jugador = ((Jugador)juego.obtenerElementoTablero​(coordenada));
			//Obtengo la coordenada a la que se mueve
			Coordenada coordenadaS = new Coordenada(coordenada.getX(), coordenada.getY()+1);
			//Solo si las coordenadas son diferentes, es decir, el jugador no está en el borde inferior del tablero, ejecuto las pruebas
			if(!coordenada.equals(coordenadaS)) {
				//Compruebo si hay un elemento en la coordenada a la que se mueve y la guardo
				Element elementoS = juego.obtenerElementoTablero​(coordenadaS);
				//Si el elemento no es nulo y el elemento es un jugador
				if(elementoS!=null && elementoS instanceof Jugador) {
					//Guardo el jugador
					Jugador enemigo = (Jugador) elementoS;
					//Guardo los posibles resultados
					String resultadoEmpate = "Empate entre los jugadores";
					String resultadoGanaMuere = "El jugador " + jugador.getNombre() + " gana. El enemigo muere";
					String resultadoPierdeMuere = "El enemigo " + enemigo.getNombre() + " gana. El jugador muere";
					try {
						String resultado = juego.movePlayer('S');
						assertTrue(resultado.equals(resultadoEmpate) || 
								(resultado.equals(resultadoGanaMuere) && juego.obtenerElementoTablero​(coordenadaS)==null) ||
								(resultado.equals(resultadoPierdeMuere) && juego.obtenerElementoTablero​(coordenada)==null));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoS!=null && elementoS.getType()==ElementType.ROCA) {
					String resultadoGanaARoca = "El jugador " + jugador.getNombre() + " gana a la roca";
					String resultadoPierdeARoca = "El jugador " + jugador.getNombre() + " pierde. No se mueve";
					try {
						String resultado = juego.movePlayer('S');
						assertTrue(resultado.equals(resultadoGanaARoca) && jugador.equals(juego.obtenerElementoTablero​(coordenadaS))
								|| (resultado.equals(resultadoPierdeARoca) && jugador.equals(juego.obtenerElementoTablero​(coordenada))));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoS!=null && elementoS.getType()==ElementType.GEMA) {
					String resultado;
					try {
						resultado = juego.movePlayer('S');
						assertTrue(resultado.equals("") && jugador.getGemas()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaS)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoS!=null && elementoS.getType()==ElementType.POCION) {
					String resultado;
					try {
						resultado = juego.movePlayer('S');
						assertTrue(resultado.equals("") && jugador.getPociones()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaS)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoS!=null && elementoS.getType()==ElementType.DINERO) {
					String resultado;
					try {
						resultado = juego.movePlayer('S');
						assertTrue(resultado.equals("") && jugador.getDinero()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaS)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else {
					String resultado;
					try {
						resultado = juego.movePlayer('S');
						assertTrue(resultado.equals("") && jugador.getDinero()==0 && jugador.getPociones()==0 && jugador.equals(juego.obtenerElementoTablero​(coordenadaS)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}
			}
		}
	}
	
	@Test
	public void testMovePlayerEsteConPocionesGemasYDinero() {
		for(int i=0; i<5000; i++) {
			//Creo el juego
			Juego juego = listaJugadoresTerminadoUnJugador();
			//Obtengo coordenada jugador que juega
			Coordenada coordenada = juego.obtenerCoordenadaJugadorJuega();
			//Obtengo jugador
			Jugador jugador = ((Jugador)juego.obtenerElementoTablero​(coordenada));
			//Asigno al jugador una gema, un dinero y una poción
			jugador.encuentraGema();
			jugador.encuentraDinero();
			jugador.encuentraPocion();
			//Obtengo la coordenada a la que se mueve
			Coordenada coordenadaE = new Coordenada(coordenada.getX()+1, coordenada.getY());
			//Solo si las coordenadas son diferentes, es decir, el jugador no está en el borde derecho del tablero, ejecuto las pruebas
			if(!coordenada.equals(coordenadaE)) {
				//Compruebo si hay un elemento en la coordenada a la que se mueve y la guardo
				Element elementoE = juego.obtenerElementoTablero​(coordenadaE);
				//Si el elemento no es nulo y el elemento es un jugador
				if(elementoE!=null && elementoE instanceof Jugador) {
					//Guardo el jugador
					Jugador enemigo = (Jugador) elementoE;
					//Asigno al jugador una gema, un dinero y una poción
					enemigo.encuentraGema();
					enemigo.encuentraDinero();
					enemigo.encuentraPocion();
					//Guardo los posibles resultados
					String resultadoEmpate = "Empate entre los jugadores";
					String resultadoGanaUsaPocima = "El jugador " + jugador.getNombre() + " gana. El enemigo pierde una pócima.";
					String resultadoGanaDinero = "El jugador " + jugador.getNombre() + " gana. Le quita el dinero al enemigo";
					String resultadoPierdeUsaPocima = "El enemigo " + enemigo.getNombre() + " gana. El jugador pierde una pócima.";
					String resultadoPierdeDinero = "El enemigo " + enemigo.getNombre() + " gana. Le quita el dinero al jugador";
					try {
						String resultado = juego.movePlayer('E');
						assertTrue(resultado.equals(resultadoEmpate) || (resultado.equals(resultadoGanaUsaPocima) && jugador.getPociones()==1 && enemigo.getPociones()==0) 
								|| (resultado.equals(resultadoGanaDinero) && jugador.getDinero()==2 && enemigo.getDinero()==0 && juego.isTerminado() && juego.getGanador().equals("Jugador: ELFO Dinero: 2 Gemas: 1 Pociones: 1"))
								|| (resultado.equals(resultadoPierdeUsaPocima) && jugador.getPociones()==0 && enemigo.getPociones()==1)
								|| (resultado.equals(resultadoPierdeDinero) && jugador.getDinero()==0 && enemigo.getDinero()==2 && juego.isTerminado() 
								&& juego.getGanador().equalsIgnoreCase("Jugador: " + enemigo.getNombre() + " Dinero: 2 Gemas: 1 Pociones: 1")));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoE!=null && elementoE.getType()==ElementType.ROCA) {
					String resultadoRompeRocaGema = "El jugador " + jugador.getNombre() + " rompe la roca con una gema";
					try {
						String resultado = juego.movePlayer('E');
						assertTrue((resultado.equals(resultadoRompeRocaGema) && jugador.getGemas()==0 && jugador.equals(juego.obtenerElementoTablero​(coordenadaE))));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoE!=null && elementoE.getType()==ElementType.GEMA) {
					String resultado;
					try {
						resultado = juego.movePlayer('E');
						assertTrue(resultado.equals("") && jugador.getGemas()==2 && jugador.equals(juego.obtenerElementoTablero​(coordenadaE)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoE!=null && elementoE.getType()==ElementType.POCION) {
					String resultado;
					try {
						resultado = juego.movePlayer('E');
						assertTrue(resultado.equals("") && jugador.getPociones()==2 && jugador.equals(juego.obtenerElementoTablero​(coordenadaE)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoE!=null && elementoE.getType()==ElementType.DINERO) {
					String resultado;
					try {
						resultado = juego.movePlayer('E');
						assertTrue(resultado.equals("") && jugador.getDinero()==2 && jugador.equals(juego.obtenerElementoTablero​(coordenadaE)) &&
								juego.isTerminado() && juego.getGanador().equals("Jugador: ELFO Dinero: 2 Gemas: 1 Pociones: 1"));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else {
					String resultado;
					try {
						resultado = juego.movePlayer('E');
						assertTrue(resultado.equals("") && jugador.getDinero()==1 && jugador.getPociones()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaE)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}
			}
		}
	}
	
	@Test
	public void testMovePlayerEsteSinPocionesGemas() {
		for(int i=0; i<5000; i++) {
			//Creo el juego
			Juego juego = listaJugadoresTerminadoUnJugador();
			//Obtengo coordenada jugador que juega
			Coordenada coordenada = juego.obtenerCoordenadaJugadorJuega();
			//Obtengo jugador
			Jugador jugador = ((Jugador)juego.obtenerElementoTablero​(coordenada));
			//Obtengo la coordenada a la que se mueve
			Coordenada coordenadaE = new Coordenada(coordenada.getX()+1, coordenada.getY());
			//Solo si las coordenadas son diferentes, es decir, el jugador no está en el borde derecho del tablero, ejecuto las pruebas
			if(!coordenada.equals(coordenadaE)) {
				//Compruebo si hay un elemento en la coordenada a la que se mueve y la guardo
				Element elementoE = juego.obtenerElementoTablero​(coordenadaE);
				//Si el elemento no es nulo y el elemento es un jugador
				if(elementoE!=null && elementoE instanceof Jugador) {
					//Guardo el jugador
					Jugador enemigo = (Jugador) elementoE;
					//Guardo los posibles resultados
					String resultadoEmpate = "Empate entre los jugadores";
					String resultadoGanaMuere = "El jugador " + jugador.getNombre() + " gana. El enemigo muere";
					String resultadoPierdeMuere = "El enemigo " + enemigo.getNombre() + " gana. El jugador muere";
					try {
						String resultado = juego.movePlayer('E');
						assertTrue(resultado.equals(resultadoEmpate) || 
								(resultado.equals(resultadoGanaMuere) && juego.obtenerElementoTablero​(coordenadaE)==null) ||
								(resultado.equals(resultadoPierdeMuere) && juego.obtenerElementoTablero​(coordenada)==null));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoE!=null && elementoE.getType()==ElementType.ROCA) {
					String resultadoGanaARoca = "El jugador " + jugador.getNombre() + " gana a la roca";
					String resultadoPierdeARoca = "El jugador " + jugador.getNombre() + " pierde. No se mueve";
					try {
						String resultado = juego.movePlayer('E');
						assertTrue(resultado.equals(resultadoGanaARoca) && jugador.equals(juego.obtenerElementoTablero​(coordenadaE))
								|| (resultado.equals(resultadoPierdeARoca) && jugador.equals(juego.obtenerElementoTablero​(coordenada))));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoE!=null && elementoE.getType()==ElementType.GEMA) {
					String resultado;
					try {
						resultado = juego.movePlayer('E');
						assertTrue(resultado.equals("") && jugador.getGemas()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaE)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoE!=null && elementoE.getType()==ElementType.POCION) {
					String resultado;
					try {
						resultado = juego.movePlayer('E');
						assertTrue(resultado.equals("") && jugador.getPociones()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaE)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoE!=null && elementoE.getType()==ElementType.DINERO) {
					String resultado;
					try {
						resultado = juego.movePlayer('E');
						assertTrue(resultado.equals("") && jugador.getDinero()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaE)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else {
					String resultado;
					try {
						resultado = juego.movePlayer('E');
						assertTrue(resultado.equals("") && jugador.getDinero()==0 && jugador.getPociones()==0 && jugador.equals(juego.obtenerElementoTablero​(coordenadaE)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}
			}
		}
	}
	
	@Test
	public void testMovePlayerOesteConPocionesGemasYDinero() {
		for(int i=0; i<5000; i++) {
			//Creo el juego
			Juego juego = listaJugadoresTerminadoUnJugador();
			//Obtengo coordenada jugador que juega
			Coordenada coordenada = juego.obtenerCoordenadaJugadorJuega();
			//Obtengo jugador
			Jugador jugador = ((Jugador)juego.obtenerElementoTablero​(coordenada));
			//Asigno al jugador una gema, un dinero y una poción
			jugador.encuentraGema();
			jugador.encuentraDinero();
			jugador.encuentraPocion();
			//Obtengo la coordenada a la que se mueve
			Coordenada coordenadaO = new Coordenada(coordenada.getX()-1, coordenada.getY());
			//Solo si las coordenadas son diferentes, es decir, el jugador no está en el borde izquierdo del tablero, ejecuto las pruebas
			if(!coordenada.equals(coordenadaO)) {
				//Compruebo si hay un elemento en la coordenada a la que se mueve y la guardo
				Element elementoO = juego.obtenerElementoTablero​(coordenadaO);
				//Si el elemento no es nulo y el elemento es un jugador
				if(elementoO!=null && elementoO instanceof Jugador) {
					//Guardo el jugador
					Jugador enemigo = (Jugador) elementoO;
					//Asigno al jugador una gema, un dinero y una poción
					enemigo.encuentraGema();
					enemigo.encuentraDinero();
					enemigo.encuentraPocion();
					//Guardo los posibles resultados
					String resultadoEmpate = "Empate entre los jugadores";
					String resultadoGanaUsaPocima = "El jugador " + jugador.getNombre() + " gana. El enemigo pierde una pócima.";
					String resultadoGanaDinero = "El jugador " + jugador.getNombre() + " gana. Le quita el dinero al enemigo";
					String resultadoPierdeUsaPocima = "El enemigo " + enemigo.getNombre() + " gana. El jugador pierde una pócima.";
					String resultadoPierdeDinero = "El enemigo " + enemigo.getNombre() + " gana. Le quita el dinero al jugador";
					try {
						String resultado = juego.movePlayer('O');
						assertTrue(resultado.equals(resultadoEmpate) || (resultado.equals(resultadoGanaUsaPocima) && jugador.getPociones()==1 && enemigo.getPociones()==0) 
								|| (resultado.equals(resultadoGanaDinero) && jugador.getDinero()==2 && enemigo.getDinero()==0 && juego.isTerminado() && juego.getGanador().equals("Jugador: ELFO Dinero: 2 Gemas: 1 Pociones: 1"))
								|| (resultado.equals(resultadoPierdeUsaPocima) && jugador.getPociones()==0 && enemigo.getPociones()==1)
								|| (resultado.equals(resultadoPierdeDinero) && jugador.getDinero()==0 && enemigo.getDinero()==2 && juego.isTerminado() 
								&& juego.getGanador().equalsIgnoreCase("Jugador: " + enemigo.getNombre() + " Dinero: 2 Gemas: 1 Pociones: 1")));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoO!=null && elementoO.getType()==ElementType.ROCA) {
					String resultadoRompeRocaGema = "El jugador " + jugador.getNombre() + " rompe la roca con una gema";
					try {
						String resultado = juego.movePlayer('O');
						assertTrue((resultado.equals(resultadoRompeRocaGema) && jugador.getGemas()==0 && jugador.equals(juego.obtenerElementoTablero​(coordenadaO))));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoO!=null && elementoO.getType()==ElementType.GEMA) {
					String resultado;
					try {
						resultado = juego.movePlayer('O');
						assertTrue(resultado.equals("") && jugador.getGemas()==2 && jugador.equals(juego.obtenerElementoTablero​(coordenadaO)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoO!=null && elementoO.getType()==ElementType.POCION) {
					String resultado;
					try {
						resultado = juego.movePlayer('O');
						assertTrue(resultado.equals("") && jugador.getPociones()==2 && jugador.equals(juego.obtenerElementoTablero​(coordenadaO)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoO!=null && elementoO.getType()==ElementType.DINERO) {
					String resultado;
					try {
						resultado = juego.movePlayer('O');
						assertTrue(resultado.equals("") && jugador.getDinero()==2 && jugador.equals(juego.obtenerElementoTablero​(coordenadaO)) &&
								juego.isTerminado() && juego.getGanador().equals("Jugador: ELFO Dinero: 2 Gemas: 1 Pociones: 1"));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else {
					String resultado;
					try {
						resultado = juego.movePlayer('O');
						assertTrue(resultado.equals("") && jugador.getDinero()==1 && jugador.getPociones()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaO)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}
			}
		}
	}
	
	@Test
	public void testMovePlayerOesteSinPocionesGemasYDinero() {
		for(int i=0; i<5000; i++) {
			//Creo el juego
			Juego juego = listaJugadoresTerminadoUnJugador();
			//Obtengo coordenada jugador que juega
			Coordenada coordenada = juego.obtenerCoordenadaJugadorJuega();
			//Obtengo jugador
			Jugador jugador = ((Jugador)juego.obtenerElementoTablero​(coordenada));
			//Obtengo la coordenada a la que se mueve
			Coordenada coordenadaO = new Coordenada(coordenada.getX()-1, coordenada.getY());
			//Solo si las coordenadas son diferentes, es decir, el jugador no está en el borde izquierdo del tablero, ejecuto las pruebas
			if(!coordenada.equals(coordenadaO)) {
				//Compruebo si hay un elemento en la coordenada a la que se mueve y la guardo
				Element elementoO = juego.obtenerElementoTablero​(coordenadaO);
				//Si el elemento no es nulo y el elemento es un jugador
				if(elementoO!=null && elementoO instanceof Jugador) {
					//Guardo el jugador
					Jugador enemigo = (Jugador) elementoO;
					//Guardo los posibles resultados
					String resultadoEmpate = "Empate entre los jugadores";
					String resultadoGanaMuere = "El jugador " + jugador.getNombre() + " gana. El enemigo muere";
					String resultadoPierdeMuere = "El enemigo " + enemigo.getNombre() + " gana. El jugador muere";
					try {
						String resultado = juego.movePlayer('O');
						assertTrue(resultado.equals(resultadoEmpate) || 
								(resultado.equals(resultadoGanaMuere) && juego.obtenerElementoTablero​(coordenadaO)==null) ||
								(resultado.equals(resultadoPierdeMuere) && juego.obtenerElementoTablero​(coordenada)==null));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoO!=null && elementoO.getType()==ElementType.ROCA) {
					String resultadoGanaARoca = "El jugador " + jugador.getNombre() + " gana a la roca";
					String resultadoPierdeARoca = "El jugador " + jugador.getNombre() + " pierde. No se mueve";
					try {
						String resultado = juego.movePlayer('O');
						assertTrue(resultado.equals(resultadoGanaARoca) && jugador.equals(juego.obtenerElementoTablero​(coordenadaO))
								|| (resultado.equals(resultadoPierdeARoca) && jugador.equals(juego.obtenerElementoTablero​(coordenada))));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoO!=null && elementoO.getType()==ElementType.GEMA) {
					String resultado;
					try {
						resultado = juego.movePlayer('O');
						assertTrue(resultado.equals("") && jugador.getGemas()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaO)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoO!=null && elementoO.getType()==ElementType.POCION) {
					String resultado;
					try {
						resultado = juego.movePlayer('O');
						assertTrue(resultado.equals("") && jugador.getPociones()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaO)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else if(elementoO!=null && elementoO.getType()==ElementType.DINERO) {
					String resultado;
					try {
						resultado = juego.movePlayer('O');
						assertTrue(resultado.equals("") && jugador.getDinero()==1 && jugador.equals(juego.obtenerElementoTablero​(coordenadaO)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}else {
					String resultado;
					try {
						resultado = juego.movePlayer('O');
						assertTrue(resultado.equals("") && jugador.getDinero()==0 && jugador.getPociones()==0 && jugador.equals(juego.obtenerElementoTablero​(coordenadaO)));
					} catch (JuegoException | JugadorException e) {
						System.out.println("Excepción incorrecta: " + e.getMessage());
					}
				}
			}
		}
	}
}
