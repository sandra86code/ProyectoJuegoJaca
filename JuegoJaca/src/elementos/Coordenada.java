package elementos;

import java.util.Objects;
import java.util.Random;
import logicaJuego.Constantes;

/**
 * The Class Coordenada.
 * @author sandra
 */
public class Coordenada implements Cloneable {
	
	private int x;
	private int y;
	
	/**
	 * Instantiates a new coordenada de forma aleatoria. 
	 * Los valores de la x y la y deben estar comprendidos entre 0 y Constantes.TAMMANO
	 */
	public Coordenada() {
		super();
		this.x = new Random().nextInt(Constantes.TAMANNO);
		this.y = new Random().nextInt(Constantes.TAMANNO);
	}
	
	/**
	 * Si el parámetro x o el y están fuera de rango se creará la coordenada 0 y 0.
	 * El rango permitido es 0 y Constantes.TAMMANO
	 * @param x - the x
	 * @param y - the y
	 */
	public Coordenada(int x, int y) {
		super();
		this.setX(x);
		this.setY(y);
	}
	
	private void setX(int x) {
		if(x<0 || x>Constantes.TAMANNO-1) {
			x=0;
		}
		this.x = x;
	}
	
	
	private void setY(int y) {
		if(y<0 || y>Constantes.TAMANNO-1) {
			y=0;
		}
		this.y = y;
	}
	
	
	/**
	 * Devuelve el valor de x
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Devuelve el valor de y
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Hash code. Una coordenada será igual a otro cuando las x sean iguales y las y sean iguales
	 * @return the int	
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	/**
	 * Equals. Una coordenada será igual a otro cuando las x sean iguales y las y sean iguales
	 * @param obj - the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada other = (Coordenada) obj;
		return x == other.x && y == other.y;
	}
	
	/**
	 * To string
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Coordenada [x = " + x + ", y = " + y + "]";
	}
	
	/**
	 * Go right. Mueve la coordenada a una posicición a la derecha, es decir, incrementa la x en un posición 
	 * Si la coordenada está en el borde del tablero (es decir una menos que el tamañom máximo Constantes.TAMMANO) 
	 * NO deberá mover la coordenada y devolverá False.
	 * @return true, if successful
	 */
	public boolean goRight() {
		boolean move = false;
		if(this.x < Constantes.TAMANNO-1) {
			this.x++;
			move = true;
		}
		return move;
	}
	
	/**
	 * Go left. Mueve la coordenada a una posicición a la izquierda, es decir, decrementa la x en un posición
	 * Si la coordenada está en el borde del tablero (es decir en la primera columna) 
	 * NO deberá mover la coordenada y devolverá False.
	 * @return true, if successful
	 */
	public boolean goLeft() {
		boolean move = false;
		if(this.x != 0) {
			this.x--;
			move = true;
		}
		return move;
	}
	
	/**
	 * Go up. Mueve la coordenada a una posicición hacia arriba, es decir, decrementa la y en un posición 
	 * Si la coordenada está en el borde del tablero (es decir en la primera fila) NO deberá mover la 
	 * coordenada y devolverá False.
	 * @return true, if successful
	 */
	public boolean goUp() {
		boolean move = false;
		if(this.y != 0) {
			this.y--;
			move = true;
		}
		return move;
	}
	
	/**
	 * Go down. Mueve la coordenada a una posicición hacia abajo, es decir, incrementa la y en un posición 
	 * Si la coordenada está en el borde del tablero (es decir una menos que el tamañom máximo 
	 * Constantes.TAMMANO) NO deberá mover la coordenada y devolverá False.
	 * @return true, if successful
	 */
	public boolean goDown() {
		boolean move  = false;
		if(this.y < Constantes.TAMANNO-1) {
			this.y++;
			move = true;
		}
		return move ;
	}
	
	/**
	 * Clone. Devuelve una nueva coordenada con los mismos atributos que la coordenada que lo llama
	 * @throws CloneNotSupportedException 
	 */
	@Override
	public Coordenada clone() throws CloneNotSupportedException {
		return (Coordenada) super.clone();
	}
}
