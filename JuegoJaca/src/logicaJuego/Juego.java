package logicaJuego;

import java.util.ArrayList;
import java.util.HashMap;

import elementos.*;

public class Juego {

	private HashMap<Coordenada, Element> tablero;
	private ArrayList<Coordenada> coordenadaJugadores;
	private int jugadorJuega;
	private int dado; // Dado para ver los movimientos del jugador que juega

	/**
	 * Creación del juego, recibe un array con el orden de los jugadores que han elegido
	 * @param jugadores
	 * @throws JuegoException
	 */
	public Juego(PlayerType[] jugadores) {
		this.tablero = new HashMap<>();
		this.coordenadaJugadores = new ArrayList<>();
		crearTablero();
		
		for(int i=0; i<jugadores.length; i++) {
			boolean creado = crearJugador​(jugadores[i]);
			while(!creado) {
				creado = crearJugador​(jugadores[i]);
			}
		}
	}
	
	/**
	 * Método privado que crea los elementos del tablero menos los jugadores
	 */
	private void crearTablero() {
		crearDinero();
		crearGemas();
		crearPociones();
		crearRocas();
	}
	
	/**
	 * Método privado para crear un jugador
	 * @param tipo
	 * @return true if succesfully
	 */
	private boolean crearJugador​(PlayerType tipo) {
		boolean creado = false;
		Jugador jugador = new Jugador(tipo);
		Coordenada coordenada = new Coordenada();
		if(!coordenadaJugadores.contains(coordenada) && !tablero.containsKey(coordenada)) {
			coordenadaJugadores.add(coordenada);
			tablero.put(coordenada, jugador);
			creado = true;
		}
		return creado;
	}
	
	
	/**
	 * Crear las rocas en el tablero
	 */
	private void crearRocas() {
		for(int i=0; i<Constantes.NUM_ROCAS; i++) {
			Coordenada coordenada = new Coordenada();
			while(tablero.containsKey(coordenada)) {
				coordenada = new Coordenada();
			}
			Element roca = new Element(ElementType.ROCA);
			tablero.put(coordenada, roca);
		}
	}
	
	/**
	 * Crear las gemas en el tablero
	 */
	private void crearGemas() {
		for(int i=0; i<Constantes.NUM_GEMAS; i++) {
			Coordenada coordenada = new Coordenada();
			while(tablero.containsKey(coordenada)) {
				coordenada = new Coordenada();
			}
			Element gema = new Element(ElementType.GEMA);
			tablero.put(coordenada, gema);
		}
	}
	
	/**
	 * Crear el dinero en el tablero
	 */
	private void crearDinero() {
		for(int i=0; i<Constantes.NUM_DINERO; i++) {
			Coordenada coordenada = new Coordenada();
			while(tablero.containsKey(coordenada)) {
				coordenada = new Coordenada();
			}
			Element dinero = new Element(ElementType.DINERO);
			tablero.put(coordenada, dinero);
		}
	}
	

	/**
	 * Crear las pociones en el tablero
	 */
	private void crearPociones() {
		for(int i=0; i<Constantes.NUM_POCIONES; i++) {
			Coordenada coordenada = new Coordenada();
			while(tablero.containsKey(coordenada)) {
				coordenada = new Coordenada();
			}
			Element pocion = new Element(ElementType.POCION);
			tablero.put(coordenada, pocion);
		}
	}
	
	/**
	 * Escribe el tablero en formato no gráfico. Devuelve el string con la
	 * información
	 */
	@Override
	public String toString() {
		StringBuilder resul = new StringBuilder();
		resul.append(barra());
		resul.append("     |");

		for (int fila = 0; fila < Constantes.TAMANNO; fila++) {
			for (int columna = 0; columna < Constantes.TAMANNO; columna++) {
				Coordenada coor = new Coordenada(columna, fila);

				if (this.tablero.get(coor) != null) {
					resul.append(" " + this.tablero.get(coor).getType().getSymbol() + " ");
				} else {
					resul.append("   ");
				}

				resul.append("|");
			}
			resul.append("\n");
			resul.append(barra());
			resul.append("     |");
		}
		resul.delete(resul.length() - 5, resul.length());
		return resul.toString();
	}
	
	/**
	 * Devuelve un boolean que indica si está terminado o no. 
	 * Un juego está termindo si sólo queda un jugador o si algún jugador tiene todo el dinero
	 * @return true si juego terminado
	 */
	public boolean isTerminado() {
		boolean finished = false;
		if(coordenadaJugadores.size()==1) {
			finished = true;
		}else {
			for(Coordenada c : coordenadaJugadores) {
				if(tablero.get(c)!=null) {
					if(((Jugador) this.tablero.get(c)).getDinero() == Constantes.NUM_DINERO) {
						finished = true;
					}
				}
			}
		}
		return finished;
	}
	
	/**
	 * Simplemente escribe una barra en pantalla
	 * @return the string
	 */
	private String barra() {
		StringBuilder resul = new StringBuilder();
		resul.append("     ");
		for (int i = 0; i < Constantes.TAMANNO * 4; i++) {
			resul.append("-");
		}
		resul.append("\n");
		return resul.toString();
	}
	
	/**
	 * Función que imprime, devuelve, el nombre de los jugadores actuales. 
	 * El formato es El jugador 1 es un xxx El jugador 2 es un xxx ......................
	 * @return
	 */
	public String imprimeNombreJugadores() {
		StringBuilder mensaje = new StringBuilder();
		int contador = 0;
		for(Coordenada c : coordenadaJugadores) {
			if(tablero.containsKey(c)) {
				Jugador jugador = (Jugador) tablero.get(c);
				contador++;
				mensaje.append("El jugador " + contador + " es un " + jugador.getNombre() + "\n");
			}
		}
		return mensaje.toString();
	}
	
	/**
	 * Función que imprime, devuelve, los jugadores con sus valores de dinero, pociones y gemas.
	 * @return
	 */
	public String imprimeValoresJugadores() {
		StringBuilder mensaje = new StringBuilder();
		for(Coordenada c : coordenadaJugadores) {
			if(tablero.containsKey(c)) {
				mensaje.append(((Jugador) tablero.get(c)).resumen()+"\n");
			}
		}
		return mensaje.toString();
	}
	
	/**
	 * Elimina el jugador que está en la coordenadas que se le pasa por parámetro 
	 * Tiene que borrar el jugador del tablero y de la lista de coordenadas del jugadores
	 * @param coord
	 */
	private void eliminarJugador​(Coordenada coord) {
		coordenadaJugadores.remove(coord);
		tablero.remove(coord);
	}
	
	/**
	 * Devuelve una coordenada que indica a donde se deberá mover el jugador. 
	 * Si no es una dirección válida N, S, E, O deberá lanzar una exception
	 * @param direction
	 * @return
	 * @throws JuegoException
	 */
	private Coordenada getNextPosition​(char direction) throws JuegoException {
		if(direction!='N' && direction!='S' && direction!='E' && direction!='O') {
			throw new JuegoException("Error en la dirección.");
		}
		Coordenada c = coordenadaJugadores.get(jugadorJuega).clone();
		if(direction=='N') {
			c.goUp();
		}else if(direction=='S') {
			c.goDown();
		}else if(direction=='E') {
			c.goRight();
		}else {
			c.goLeft();
		}
		return c;
	}
	
	/**
	 * Cambia el jugador que juega a la posición indicada por parámetro. 
	 * Para cambiar un jugador a una determinada posición, hay que obtener la coordenada actual de jugador y el jugador. 
	 * Borrar del tablero la coordenada actual e insertar el jugador en la nueva coordenada. 
	 * Por último, en la lista de las coordenadas de los jugadores, hay que actualizar las coordenadas.
	 * @param coord
	 */
	private void cambiaJugadorAPosicion​(Coordenada coord) {
		Coordenada c = coordenadaJugadores.get(jugadorJuega); // Coordenada jugador
		Jugador jug = (Jugador) tablero.get(c); // Jugador
		tablero.remove(c); //Eliminar clave/valor mapa
		coordenadaJugadores.remove(jugadorJuega); //Eliminar coordenada del jugador
		coordenadaJugadores.add(jugadorJuega, coord); //Cambiar la coordenada del jugador
		tablero.put(coord, jug); //Añadir jugador con su nueva coordenada
	}
	
	
	
	/**
	 * Mover el jugador
	 * @param direction
	 * @return
	 * @throws JuegoException
	 * @throws JugadorException
	 */
	public String movePlayer(char direction) throws JuegoException, JugadorException {
		// Si no es una dirección válida, mando un exception
		String resul = "";
		Jugador jugador = (Jugador) this.tablero.get(this.coordenadaJugadores.get(jugadorJuega));
		Coordenada coordDestino = getNextPosition​(direction);
		// Tengo que ver que hay en la nueva casilla
		Element elemento = this.tablero.get(coordDestino);
		if (elemento != null) { // Hay algo en la casilla
			if (elemento instanceof Jugador) {
				Jugador enemigo = (Jugador) elemento;
				int resultadoLucha = jugador.lucha(enemigo);
				switch (resultadoLucha) {
				case Constantes.EMPATE:
					resul = "Empate entre los jugadores";
					break;
				case Constantes.GANA_USA_POCIMA:
					resul = "El jugador " + jugador.getNombre() + " gana. El enemigo pierde una pócima.";
					break;
				case Constantes.GANA_DINERO:
					resul = "El jugador " + jugador.getNombre() + " gana. Le quita el dinero al enemigo";
					break;
				case Constantes.GANA_MUERE:
					resul = "El jugador " + jugador.getNombre() + " gana. El enemigo muere";
					this.eliminarJugador​(coordDestino);
					break;
				case Constantes.PIERDE_USA_POCIMA:
					resul = "El enemigo " + enemigo.getNombre() + " gana. El jugador pierde una pócima.";
					break;
				case Constantes.PIERDE_DINERO:
					resul = "El enemigo " + enemigo.getNombre() + " gana. Le quita el dinero al jugador";
					break;
				case Constantes.PIERDE_MUERE:
					resul = "El enemigo " + enemigo.getNombre() + " gana. El jugador muere";
					this.eliminarJugador​(this.coordenadaJugadores.get(jugadorJuega));
					this.dado = 0;
					// Decrementamos en uno el jugador, para que no se salte al siguiente
					// ya que al borrarlo el siguiente apunta al siguiente, y al incrementarlo
					// se le salta
					this.jugadorJuega--;
					break;
				}
				// Después de la lucha los jugadores no se mueven
			} else if (elemento.getType() == ElementType.ROCA) {
				int resultadoRoca = jugador.encuentraRoca();
				switch (resultadoRoca) {
				case Constantes.ROMPE_ROCA_CON_GEMA:
					resul = "El jugador " + jugador.getNombre() + " rompe la roca con una gema";
					this.cambiaJugadorAPosicion​(coordDestino);
					break;

				case Constantes.GANA_A_LA_ROCA:
					resul = "El jugador " + jugador.getNombre() + " gana a la roca";
					this.cambiaJugadorAPosicion​(coordDestino);
					break;

				case Constantes.PIERDE_A_LA_ROCA:
					resul = "El jugador " + jugador.getNombre() + " pierde. No se mueve";
					break;
				}
			} else if (elemento.getType() == ElementType.GEMA) {
				jugador.encuentraGema();
				this.cambiaJugadorAPosicion​(coordDestino);

			} else if (elemento.getType() == ElementType.DINERO) {
				jugador.encuentraDinero();
				this.cambiaJugadorAPosicion​(coordDestino);

			} else if (elemento.getType() == ElementType.POCION) {
				jugador.encuentraPocion();
				this.cambiaJugadorAPosicion​(coordDestino);
			}
		} else {
			this.cambiaJugadorAPosicion​(coordDestino);
		}
		return resul;
	}

	/**
	 * Actualiza la variable jugadorJuega al próximo jugador. Si es el último de la lista se debe empezar por el principio.
	 */
	public void proximoJugador() {
		if(this.jugadorJuega == coordenadaJugadores.size()-1) {
			this.jugadorJuega = 0;
		}else {
			this.jugadorJuega++;
		}
	}
	
	/**
	 * Devuelve la información del ganador si sólo hay jugador o si no, si hay alguien que tiene todo el dinero
	 * @return the string
	 */
	public String getGanador() {
		String mensaje = null;
		Coordenada c;
		Jugador j;
		if(coordenadaJugadores.size() == 1) {
			c = coordenadaJugadores.get(0);
			j = (Jugador) tablero.get(c);
			mensaje = j.resumen();
		}else {
			for(Coordenada coor : coordenadaJugadores) {
				j = (Jugador) tablero.get(coor);
				if(j.getDinero() == Constantes.NUM_DINERO) {
					mensaje = j.resumen();
				}
			}
		}
		return mensaje;
	}
	
	/**
	 * Devuelve el nombre del jugador al que le toca jugar
	 * @return
	 */
	public String getNombreJuegadorQueJuega() {
		return ((Jugador) tablero.get(coordenadaJugadores.get(jugadorJuega))).getNombre();
	}
	
	/**
	 * Devuelve el número de movimientos que el jugador que está jugando debe hacer
	 * @return
	 */
	public int getMovimientoJugador() {
		return ((Jugador) tablero.get(coordenadaJugadores.get(jugadorJuega))).getVelocidadParaLuchar();
	}
	
	/**
	 * Devuelve el valor actual del dado
	 * @return
	 */
	public int getValorDado() {
		return dado;
	}
	
	/**
	 * Decrementa el valor actual del dado
	 */
	public void decrementaDado() {
		this.dado--;
	}
	
	/**
	 * Asigna valor del dado con los movimientos para el jugador que juega le ha salido
	 */
	public void setDado() {
		this.dado = ((Jugador) tablero.get(coordenadaJugadores.get(jugadorJuega))).getVelocidadParaLuchar();
	}
	
	/**
	 * Devuelve el elemento que está en la coordenada coord. Devuelve el elemento, no una copia
	 * @param coord
	 * @return
	 */
	public Element obtenerElementoTablero​(Coordenada coord) {
		return tablero.get(coord);
	}
	
	/**
	 * Obtiene la coordenada del jugador que está jugando actualmente
	 * @return
	 */
	public Coordenada obtenerCoordenadaJugadorJuega() {
		return coordenadaJugadores.get(jugadorJuega);
	}
}
