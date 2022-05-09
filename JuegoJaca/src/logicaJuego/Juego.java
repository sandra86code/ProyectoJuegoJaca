package logicaJuego;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
	public Juego​(PlayerType[] jugadores) throws JuegoException {
		
	}
	
	/**
	 * Método privado que crea los elementos del tablero menos los jugadores
	 */
	private void crearTablero() {
		
	}
	
	/**
	 * Método privado para crear un jugador
	 * @param tipo
	 * @return true if succesfully
	 */
	private boolean crearJugador​(PlayerType tipo) {
		
	}
	
	
	/**
	 * Crear las rocas en el tablero
	 */
	private void crearRocas() {
	
	}
	
	/**
	 * Crear las gemas en el tablero
	 */
	private void crearGemas() {
		
	}
	
	/**
	 * Crear el dinero en el tablero
	 */
	private void crearDinero() {
		
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
	 * Devuelve un boolean que indica si está terminado o no. Un juego está termindo si sólo queda un jugador 
	 * o si algún jugador tiene todo el dinero
	 * @return
	 */
	public boolean isTerminado() {
		
	}
	
	/**
	 * Simplemente escribe una barra en pantalla
	 * 
	 * @return
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
		
	}
	
	/**
	 * Función que imprime, devuelve, los jugadores con sus valores de dinero, pociones y gemas.
	 * @return
	 */
	public String imprimeValoreJugadores() {
		
	}
	
	/**
	 * Elimina el jugador que está en la coordenadas que se le pasa por parámetro 
	 * Tiene que borrar el jugador del tablero y de la lista de coordenadas del jugadores
	 * @param coord
	 */
	private void eliminarJugador​(Coordenada coord) {
		
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

		Coordenada coordDestino = getNextPosition(direction);

		// Tengo que ver que hay en la nueva casilla
		Element elemento = this.tablero.get(coordDestino);

		if (elemento != null) { // Hay algo en la casilla
			if (elemento instanceof Jugador) {

				Jugador enemigo = (Jugador) elemento;
				int resultadoLucha = jugador.lucha(enemigo);
				switch (resultadoLucha) {
				case Constantes.EMPATE:
					resul = "Empate entre los jugadore";
					break;
				case Constantes.GANA_USA_POCIMA:
					resul = "El jugador " + jugador.getNombre() + " gana. Le quita una pócima al enemigo";
					break;
				case Constantes.GANA_DINERO:
					resul = "El jugador " + jugador.getNombre() + " gana. Le quita el dinero al enemigo";
					break;
				case Constantes.GANA_MUERE:
					resul = "El jugador " + jugador.getNombre() + " gana. El enemigo muere";
					this.eliminarJugador(coordDestino);
					// Si se elimina el jugador que juega el dado se pone a 0 para que no siga
					// tirando
					break;
				case Constantes.PIERDE_USA_POCIMA:
					resul = "El enemigo " + enemigo.getNombre() + " gana. Le quita una pócima al jugador";
					break;
				case Constantes.PIERDE_DINERO:
					resul = "El enemigo " + enemigo.getNombre() + " gana. Le quita el dinero al jugador";
					break;
				case Constantes.PIERDE_MUERE:
					resul = "El enemigo " + enemigo.getNombre() + " gana. El jugador muere";
					this.eliminarJugador(this.coordenadaJugadores.get(jugadorJuega));
					dado = 0;
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
					this.cambiaJugadorAPosicion(coordDestino);
					break;

				case Constantes.GANA_A_LA_ROCA:
					resul = "El jugador " + jugador.getNombre() + " gana a la roca";
					this.cambiaJugadorAPosicion(coordDestino);
					break;

				case Constantes.PIERDE_A_LA_ROCA:
					resul = "El jugador " + jugador.getNombre() + " pierde. No se mueve";
					break;
				}
			} else if (elemento.getType() == ElementType.GEMA) {
				jugador.encuentraGema();
				this.cambiaJugadorAPosicion(coordDestino);

			} else if (elemento.getType() == ElementType.DINERO) {
				jugador.encuentraDinero();
				this.cambiaJugadorAPosicion(coordDestino);

			} else if (elemento.getType() == ElementType.POCION) {
				jugador.encuentraPocion();
				this.cambiaJugadorAPosicion(coordDestino);

			}

		} else {
			this.cambiaJugadorAPosicion(coordDestino);
		}

		return resul;
	}

	/**
	 * Actualiza la variable jugadorJuega al próximo jugador. Si es el último de la lista se debe empezar por el principio.
	 */
	public void proximoJugador() {
		
	}
	
	/**
	 * Devuelve la información del ganador si sólo hay jugador o si no, si hay alguien que tiene todo el dinero
	 * @return the string
	 */
	public String getGanador() {
		
	}
	
	/**
	 * Devuelve el nombre del jugador al que le toca jugar
	 * @return
	 */
	public String getNombreJuegadorQueJuega() {
		
	}
	
	/**
	 * Devuelve el número de movimientos que el jugador que está jugando debe hacer
	 * @return
	 */
	public int getMovimientoJugador() {
		
	}
	
	/**
	 * Devuelve el valor actual del dado
	 * @return
	 */
	public int getValorDado() {
		
	}
	
	/**
	 * Decrementa el valor actual del dado
	 */
	public void decrementaDado() {
		
	}
	
	/**
	 * Asigna valor del dado con los movimientos para el jugador que juega le ha salido
	 */
	public void setDado() {
		
	}
	
	/**
	 * Devuelve el elemento que está en la coordenada coord. Devuelve el elemento, no una copia
	 * @param coord
	 * @return
	 */
	public Element obtenerElementoTablero​(Coordenada coord) {
		
	}
	
	/**
	 * Obtiene la coordenada del jugador que está jugando actualmente
	 * @return
	 */
	public Coordenada obtenerCoordenadaJugadorJuega() {
		
	}
}
