package elementos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import logicaJuego.Constantes;

class TestJugador {

	@Test
	public void testGetsDeJugador() {
		Jugador j = new Jugador(PlayerType.ELFO);
		j.encuentraDinero();
		j.encuentraGema();
		j.encuentraPocion();
		assertTrue(j.getDinero()==1 && j.getGemas()==1 && j.getPociones()==1 && j.getNombre().equalsIgnoreCase("elfo"));

	}
	
	@Test
	public void testResumenJugador() {
		Jugador j = new Jugador(PlayerType.OGRO);
		j.encuentraDinero();
		j.encuentraGema();
		j.encuentraPocion();
		assertEquals("Jugador: OGRO Dinero: 1 Gemas: 1 Pociones: 1", j.resumen());
	}
	
	@Test
	public void testgetFuerzaParaLucharElfo() {
		Jugador j = new Jugador(PlayerType.ELFO);
		for(int i=0; i<200; i++) {
			assertTrue(j.getFuerzaParaLuchar()>=0 && j.getFuerzaParaLuchar()<=Constantes.ELFO_FUERZA);
		}
	}
	
	@Test
	public void testgetFuerzaParaLucharGuerrero() {
		Jugador j = new Jugador(PlayerType.GUERRERO);
		for(int i=0; i<200; i++) { //Fuerza = 6
			assertTrue(j.getFuerzaParaLuchar()>=0 && j.getFuerzaParaLuchar()<=Constantes.GUERRERO_FUERZA);
		}
	}
	
	@Test
	public void testgetFuerzaParaLucharMago() {
		Jugador j = new Jugador(PlayerType.MAGO);
		for(int i=0; i<200; i++) { //Fuerza = 4
			assertTrue(j.getFuerzaParaLuchar()>=0 && j.getFuerzaParaLuchar()<=Constantes.MAGO_FUERZA);
		}
	}
	
	@Test
	public void testgetFuerzaParaLucharOgro() {
		Jugador j = new Jugador(PlayerType.OGRO);
		for(int i=0; i<200; i++) { //Fuerza = 7
			assertTrue(j.getFuerzaParaLuchar()>=0 && j.getFuerzaParaLuchar()<=Constantes.OGRO_FUERZA);
		}
	}
	
	
	@Test
	public void testgetMagiaParaLucharElfo() {
		Jugador j = new Jugador(PlayerType.ELFO);
		for(int i=0; i<200; i++) { //Magia = 6
			assertTrue(j.getMagiaParaLuchar()>=0 && j.getMagiaParaLuchar()<=Constantes.ELFO_MAGIA);
		}
	}
	
	@Test
	public void testgetMagiaParaLucharGuerrero() {
		Jugador j = new Jugador(PlayerType.GUERRERO);
		for(int i=0; i<200; i++) { //Magia = 5
			assertTrue(j.getMagiaParaLuchar()>=0 && j.getMagiaParaLuchar()<=Constantes.GUERRERO_MAGIA);
		}
	}
	
	@Test
	public void testgetMagiaParaLucharMago() {
		Jugador j = new Jugador(PlayerType.MAGO);
		for(int i=0; i<200; i++) { //Magia = 7
			assertTrue(j.getMagiaParaLuchar()>=0 && j.getMagiaParaLuchar()<=Constantes.MAGO_MAGIA);
		}
	}
	
	@Test
	public void testgetMagiaParaLucharOgro() {
		Jugador j = new Jugador(PlayerType.OGRO);
		for(int i=0; i<200; i++) { //Magia = 4
			assertTrue(j.getMagiaParaLuchar()>=0 && j.getMagiaParaLuchar()<=Constantes.OGRO_MAGIA);
		}
	}
	
	
	@Test
	public void testgetVelocidadParaLucharElfo() {
		Jugador j = new Jugador(PlayerType.ELFO);
		for(int i=0; i<200; i++) { //Velocidad = 7
			assertTrue(j.getVelocidadParaLuchar()>=1 && j.getVelocidadParaLuchar()<=Constantes.ELFO_VELOCIDAD);
		}
	}
	
	@Test
	public void testgetVelocidadParaLucharGuerrero() {
		Jugador j = new Jugador(PlayerType.GUERRERO);
		for(int i=0; i<200; i++) { //Velocidad = 5
			assertTrue(j.getVelocidadParaLuchar()>=1 && j.getVelocidadParaLuchar()<=Constantes.GUERRERO_VELOCIDAD);
		}
	}
	
	@Test
	public void testgetVelocidadParaLucharMago() {
		Jugador j = new Jugador(PlayerType.MAGO);
		for(int i=0; i<200; i++) { //Velocidad = 6
			assertTrue(j.getVelocidadParaLuchar()>=1 && j.getVelocidadParaLuchar()<=Constantes.MAGO_VELOCIDAD);
		}
	}
	
	@Test
	public void testgetVelocidadParaLucharOgro() {
		Jugador j = new Jugador(PlayerType.OGRO);
		for(int i=0; i<1000; i++) { //Velocidad = 4
			assertTrue(j.getVelocidadParaLuchar()>=1 && j.getVelocidadParaLuchar()<=Constantes.OGRO_VELOCIDAD);
		}
	}
	
	@Test
	public void testSetDineroCorrectoLimiteSuperior() {
		Jugador j = new Jugador(PlayerType.OGRO);
		try {
			j.setDinero(Constantes.NUM_DINERO);
			assertEquals(Constantes.NUM_DINERO, j.getDinero());
		} catch (JugadorException e) {
			System.out.println("Excepción que no debería haberse lanzado.");
		}
	}
	
	@Test
	public void testSetDineroCorrectoLimiteInferior() {
		Jugador j = new Jugador(PlayerType.OGRO);
		try {
			j.setDinero(0);
			assertEquals(0, j.getDinero());
		} catch (JugadorException e) {
			System.out.println("Excepción que no debería haberse lanzado.");
		}
	}
	
	@Test
	public void testSetDineroIncorrectoLimiteInferior() {
		Jugador j = new Jugador(PlayerType.OGRO);
		try {
			j.setDinero(-1);
			fail("Tendría que haber saltado una excepción por dinero negativo.");
		} catch (JugadorException e) {
			System.out.println("Excepción lanzada correctamente: " + e.getMessage());
		}
	}
	
	@Test
	public void testSetDineroIncorrectoLimiteSuperior() {
		Jugador j = new Jugador(PlayerType.OGRO);
		try {
			j.setDinero(Constantes.NUM_DINERO+1);
			fail("Tendría que haber saltado una excepción por dinero mayor del permitido.");
		} catch (JugadorException e) {
			System.out.println("Excepción lanzada correctamente: " + e.getMessage());
		}
	}
	
	@Test
	public void testSetPocionesCorrectoLimiteSuperior() {
		Jugador j = new Jugador(PlayerType.OGRO);
		try {
			j.setPociones(Constantes.NUM_POCIONES);
			assertEquals(Constantes.NUM_POCIONES, j.getPociones());
		} catch (JugadorException e) {
			System.out.println("Excepción que no debería haberse lanzado.");
		}
	}
	
	@Test
	public void testSetPocionesCorrectoLimiteInferior() {
		Jugador j = new Jugador(PlayerType.OGRO);
		try {
			j.setPociones(0);
			assertEquals(0, j.getPociones());
		} catch (JugadorException e) {
			System.out.println("Excepción que no debería haberse lanzado.");
		}
	}
	
	@Test
	public void testSetPocionesIncorrectoLimiteInferior() {
		Jugador j = new Jugador(PlayerType.OGRO);
		try {
			j.setPociones(-1);
			fail("Tendría que haber saltado una excepción por pociones negativa.");
		} catch (JugadorException e) {
			System.out.println("Excepción lanzada correctamente: " + e.getMessage());
		}
	}
	
	@Test
	public void testSetPocionesIncorrectoLimiteSuperior() {
		Jugador j = new Jugador(PlayerType.OGRO);
		try {
			j.setPociones(Constantes.NUM_POCIONES+1);
			fail("Tendría que haber saltado una excepción por pociones mayor del número permitido.");
		} catch (JugadorException e) {
			System.out.println("Excepción lanzada correctamente: " + e.getMessage());
		}
	}
	
	
	
	@Test
	public void testSetGemasCorrectoLimiteSuperior() {
		Jugador j = new Jugador(PlayerType.OGRO);
		try {
			j.setGemas(Constantes.NUM_GEMAS);
			assertEquals(Constantes.NUM_GEMAS, j.getGemas());
		} catch (JugadorException e) {
			System.out.println("Excepción que no debería haberse lanzado.");
		}
	}
	
	@Test
	public void testSetGemasCorrectoLimiteInferior() {
		Jugador j = new Jugador(PlayerType.OGRO);
		try {
			j.setGemas(0);
			assertEquals(0, j.getGemas());
		} catch (JugadorException e) {
			System.out.println("Excepción que no debería haberse lanzado.");
		}
	}
	
	@Test
	public void testSetGemasIncorrectoLimiteInferior() {
		Jugador j = new Jugador(PlayerType.OGRO);
		try {
			j.setGemas(-1);
			fail("Tendría que haber saltado una excepción por gemas negativa.");
		} catch (JugadorException e) {
			System.out.println("Excepción lanzada correctamente: " + e.getMessage());
		}
	}
	
	@Test
	public void testSetGemasIncorrectoLimiteSuperior() {
		Jugador j = new Jugador(PlayerType.OGRO);
		try {
			j.setGemas(Constantes.NUM_GEMAS+1);
			fail("Tendría que haber saltado una excepción por gemas mayor del número permitido.");
		} catch (JugadorException e) {
			System.out.println("Excepción lanzada correctamente: " + e.getMessage());
		}
	}
	
	
	@Test
	public void testLuchaConPociones() {
		Jugador j1 = new Jugador(PlayerType.ELFO);
		Jugador j2 = new Jugador(PlayerType.MAGO);
		int resultado;
		for(int i=0; i<30; i++) {
			try {
				j1.setPociones(1);
				j2.setPociones(1);
				resultado = j1.lucha(j2);
				if(resultado==0) { //Empate
					assertTrue(1==j1.getPociones() && 1==j2.getPociones());	
				//Fuerza jugador > fuerza enemigo
				}else if(resultado==1) { //GANA_USA_POCIMA
					assertTrue(1==j1.getPociones() && 0==j2.getPociones());	
				//Fuerza jugador > fuerza enemigo
				}else if(resultado==4) { //PIERDE_USA_POCIMA
					assertTrue(0==j1.getPociones() && 1==j2.getPociones());	
				}
			} catch (JugadorException e) {
				System.out.println("Excepción que no debería haberse lanzado.");
			}
		}
	}
	
	@Test
	public void testLuchaConDinero() {
		Jugador j1 = new Jugador(PlayerType.ELFO);
		Jugador j2 = new Jugador(PlayerType.MAGO);
		int resultado;
		for(int i=0; i<30; i++) {
			try {
				j1.setDinero(1);
				j2.setDinero(1);
				resultado = j1.lucha(j2);
				if(resultado==0) { //Empate
					assertTrue(1==j1.getDinero() && 1==j2.getDinero());	
				//Fuerza jugador > fuerza enemigo
				}else if(resultado==2) { //GANA_DINERO
					assertTrue(2==j1.getDinero() && 0==j2.getDinero());	
				//Fuerza jugador > fuerza enemigo
				}else if(resultado==5) { //PIERDE_DINERO
					assertTrue(0==j1.getDinero() && 2==j2.getDinero());	
				}
			} catch (JugadorException e) {
				System.out.println("Excepción que no debería haberse lanzado.");
			}
		}
	}
	
	@Test
	public void testLuchaSinPocionesSinDinero() {
		Jugador j1 = new Jugador(PlayerType.ELFO);
		Jugador j2 = new Jugador(PlayerType.MAGO);
		int resultado;
		for(int i=0; i<30; i++) {
			try {
				resultado = j1.lucha(j2);
				assertTrue(resultado==0 || resultado==3 || resultado==6);	
			} catch (JugadorException e) {
				System.out.println("Excepción que no debería haberse lanzado.");
			}
		}
	}
	
	
	@Test
	public void testJugadorEncuentraRocaConGema() {
		Jugador j = new Jugador(PlayerType.ELFO);
		int resultado;
		for(int i=0; i<30; i++) {
			try {
				j.setGemas(1);
				resultado = j.encuentraRoca();
				assertTrue(resultado==0 && j.getGemas()==0);	
			} catch (JugadorException e) {
				System.out.println("Excepción que no debería haberse lanzado." + e.getMessage());
			}
		}
	}
	
	@Test
	public void testJugadorEncuentraRocaSinGema() {
		Jugador j = new Jugador(PlayerType.MAGO);
		int resultado;
		for(int i=0; i<30; i++) {
			try {
				resultado = j.encuentraRoca();
				assertTrue((resultado==1 && j.getPlayer().getMagia()>4) || resultado==2);
			} catch (JugadorException e) {
				System.out.println("Excepción que no debería haberse lanzado." + e.getMessage());
			}
		}
	}
	
	@Test
	public void testJugadorEncuentraDinero() {
		Jugador j = new Jugador(PlayerType.MAGO);
		j.encuentraDinero();
		j.encuentraDinero();
		assertEquals(2, j.getDinero());
	}
	
	@Test
	public void testJugadorEncuentraPocion() {
		Jugador j = new Jugador(PlayerType.MAGO);
		j.encuentraPocion();
		assertEquals(1, j.getPociones());
	}
	
	@Test
	public void testJugadorEncuentraGema() {
		Jugador j = new Jugador(PlayerType.MAGO);
		j.encuentraGema();
		assertEquals(1, j.getGemas());
	}

}
