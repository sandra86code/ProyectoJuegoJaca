package elementos;

import java.util.Random;
import logicaJuego.Constantes;

/**
 * The Class Jugador. Jugador extiende de Element
 * @author sandra
 */
public class Jugador extends Element {
	
	private int dinero;
	private int gemas;
	private PlayerType player;
	private int pociones;
	
	/**
	 * Instantiates a new jugador.
	 * @param player - el jugador
	 */
	public Jugador(PlayerType player) {
		super(ElementType.valueOf(player.name()));
		this.player = player;	
		this.dinero = 0;
		this.gemas = 0;
		this.pociones = 0;
	}
	
	/**
	 * Gets the nombre.
	 * @return the nombre
	 */
	public String getNombre() {
		return this.player.name();
	}
	
	/**
	 * Gets the fuerza para luchar. 
	 * Cuando el jugador lucha debe calcular que fuerza utiliza 
	 * La fuerza debe ser un número al azar entre 0 y la fuerza del jugador.
	 * @return the fuerza para luchar
	 */
	public int getFuerzaParaLuchar() {
		return new Random().nextInt(player.getFuerza());
	}
	
	/**
	 * Gets the fuerza.
	 * @return the fuerza
	 */
	private int getFuerza() {
		return player.getFuerza();
	}
	
	/**
	 * Gets the magia.
	 * @return the magia
	 */
	private int getMagia() {
		return player.getMagia();
	}
	
	/**
	 * Gets la magiapara luchar. 
	 * Cuando el jugador lucha debe calcular que magia utiliza 
	 * La magia debe ser un número al azar entre 0 y la magia del jugador.
	 * @return
	 */
	public int getMagiaParaLuchar() {
		return new Random().nextInt(player.getMagia());
	}
	
	/**
	 * Gets the velocidad.
	 * @return the velocidad
	 */
	private int getVelocidad() {
		return player.getVelocidad();
	}
	
	/**
	 * Gets the velocidad para moverse. 
	 * Cuando el jugador se va a mover debe calcular que velocidad utiliza. 
	 * La velocidad debe ser un número al azar entre 1 y la velocidad del jugador.
	 * @return the velocidad para moverse
	 */
	public int getVelocidadParaLuchar() {
		return new Random().nextInt(player.getVelocidad())+1;
	}

	/**
	 * Gets the dinero.
	 * @return the dinero
	 */
	public int getDinero() {
		return this.dinero;
	}
	
	/**
	 * Sets the dinero.	
	 * @param dinero - the new dinero
	 * @throws JugadorException
	 */
	public void setDinero(int dinero) throws JugadorException {
		if(dinero < 0 || dinero > Constantes.NUM_DINERO) {
			throw new JugadorException("Error. El dinero no puede ser menor que 0 ni mayor que  " + Constantes.NUM_DINERO);
		}
		this.dinero = dinero;
	}
	
	/**
	 * Gets the pociones.
	 * @return the pociones
	 */
	public int getPociones() {
		return this.pociones;
	}
	
	/**
	 * Sets the pociones.
	 * @param pociones - the new pociones
	 * @throws JugadorException
	 */
	public void setPociones(int pociones) throws JugadorException {
		if(pociones < 0 || pociones > Constantes.NUM_POCIONES) {
			throw new JugadorException("Error. Las pociones no pueden ser menor que 0 ni mayor que " + Constantes.NUM_POCIONES);
		}
		this.pociones = pociones;
	}
	
	/**
	 * Gets the gemas.
	 * @return the gemas
	 */
	public int getGemas() {
		return this.gemas;
	}
	
	/**
	 * Sets the Gemas.
	 * @param gemas - the new gemas
	 * @throws JugadorException
	 */
	public void setGemas(int gemas) throws JugadorException {
		if(gemas < 0|| gemas > Constantes.NUM_GEMAS) {
			throw new JugadorException("Error. Las gemas no pueden ser menor que 0 ni mayor que " + Constantes.NUM_GEMAS);
		}
		this.gemas = gemas;
	}
	
	/**
	 * Resumen.
	 * @return the string
	 */
	public String resumen() {
		return "Jugador: " + super.getType() + " Dinero: " + this.dinero + " Gemas: " +
				this.gemas + " Pociones: " + this.pociones;
	}
	
	/**
	 * Gets the player.
	 * @return the player
	 */
	public PlayerType getPlayer() {
		return player;
	}
	
	/**
	 * Lucha entre jugadores
	 * @param enemigo - el jugador contra el que juega
	 * @return 
	 * 0: EMPATE: Hay empate ninguno de los dos gana la lucha 
	 * 1: GANA_USA_POCIMA: Gana el jugador y se utiliza pocima del enemigo para que no muera 
	 * 2: GANA_DINERO: Gana el jugador y se lleva todo el dinero del enemigo 
	 * 3: GANA_MUERE; Gana el jugador y el enemigo muere 
	 * 4: PIERDE_USA_POCIMA: Gana el enemigo y se utiliza pocima del jugador para que no muera 
	 * 5: PIERDE_DINERO: Gana el enemigo y se lleva todo el dinero del jugador 
	 * 6: PIERDE_MUERE: Gana el enemigo y el jugador muere
	 * @throws JugadorException
	 */
	public int lucha(Jugador enemigo) throws JugadorException { //Revisar 
		int resultado;
		int fuerzaJugador = this.getFuerzaParaLuchar();
		int fuerzaEnemigo = enemigo.getFuerzaParaLuchar();
		
		if(fuerzaJugador == fuerzaEnemigo) {
			resultado = Constantes.EMPATE;
		}else if(fuerzaJugador > fuerzaEnemigo) {
			if(enemigo.getPociones() > 0) {
				resultado = Constantes.GANA_USA_POCIMA;
				this.setPociones(this.getPociones()+1);
				enemigo.setPociones(enemigo.getPociones()-1);
			}else if(enemigo.getDinero() > 0) {
				resultado = Constantes.GANA_DINERO;
				this.setDinero(this.getDinero() + enemigo.getDinero());
				enemigo.setDinero(0);
			}else {
				resultado = Constantes.GANA_MUERE;
			}
		}else {
			if(this.getPociones() > 0) {
				resultado = Constantes.PIERDE_USA_POCIMA;
				enemigo.setPociones(enemigo.getPociones()+1);
				this.setPociones(this.getPociones()-1);
			}else if(this.getDinero() > 0) {
				resultado = Constantes.PIERDE_DINERO;
				enemigo.setDinero(enemigo.getDinero() + this.getDinero());
				this.setDinero(0);
			}else {
				resultado = Constantes.PIERDE_MUERE;
			}
		}
		return resultado;
	}
	
	/**
	 * No permiten el paso a no ser que tengas super poderes, es decir, que tenga gemas. 
	 * Si tiene gema el jugador ocupa la posición de la roca.
	 * Si el jugador no tiene gema, puede intentar con la magia romper el obstáculo, 
	 * si consigue una magia superior 4, romperá el obstáculo. 
	 * Si usa la gema hay que decrementar las gemas que tiene, porque ya la usado
	 * @return 
	 * ROMPE_ROCA_CON_GEMA =0 / 
	 * Si tiene gema rompe la roca GANA_A_LA_ROCa = 1 // 
	 * Consigue más mágia para ganarle a la roca PIERDE_A_LA_ROCA = 2; // 
	 * Pierde con la roca
	 * @throws JugadorException
	 */
	public int encuentraRoca() throws JugadorException {
		int resultado;
		if(this.getGemas() > 0) {
			resultado = Constantes.ROMPE_ROCA_CON_GEMA;
			this.setGemas(this.getGemas()-1);
		}else {
			int magia = this.getMagiaParaLuchar();
			if(magia > 4) {
				resultado = Constantes.GANA_A_LA_ROCA;
			}else {
				resultado= Constantes.PIERDE_A_LA_ROCA;
			}
		}
		return resultado;
	}
	
	/**
	 * Encuentra dinero, tiene que incrementar en uno el dinero que tiene
	 */
	public void encuentraDinero() {
		this.dinero++;
	}
	
	/**
	 * Encuentra la pócima, tiene que incrementar en uno las pócimas que tiene
	 */
	public void encuentraPocion() {
		this.pociones++;
	}
	
	/**
	 * Encuentra gema, tiene que incrementar en uno las gemas que tiene
	 */
	public void encuentraGema() {
		this.gemas++;
	}
}