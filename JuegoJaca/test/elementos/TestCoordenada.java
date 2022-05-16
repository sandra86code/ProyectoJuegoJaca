package elementos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import logicaJuego.Constantes;

class TestCoordenada {

	@Test
	public void testCoordenadaXIncorrectaRangoSuperior() {
		Coordenada c = new Coordenada(Constantes.TAMANNO,0);
		assertEquals(0, c.getX());
	}
	
	@Test
	public void testCoordenadaXIncorrectaRangoInferior() {
		Coordenada c = new Coordenada(-1,0);
		assertEquals(0, c.getX());
	}
	
	@Test
	public void testCoordenadaYIncorrectaRangoSuperior() {
		Coordenada c = new Coordenada(0,Constantes.TAMANNO);
		assertEquals(0, c.getY());
	}
	
	@Test
	public void testCoordenadaYIncorrectaRangoInferior() {
		Coordenada c = new Coordenada(0,-1);
		assertEquals(0, c.getY());
	}
	
	@Test
	public void testCoordenadasCorrectasRangoInferior() {
		Coordenada c = new Coordenada(0,0);
		assertEquals(0, c.getX());
		assertEquals(0, c.getY());
	}
	
	@Test
	public void testCoordenadasCorrectasRangoSuperior() {
		Coordenada c = new Coordenada(Constantes.TAMANNO-1,Constantes.TAMANNO-1);
		assertEquals(Constantes.TAMANNO-1, c.getX());
		assertEquals(Constantes.TAMANNO-1, c.getY());
	}
	
	@Test
	public void testCoordenadasRandomCorrectas() {
		for(int i=0; i<100; i++) {
			Coordenada c = new Coordenada();
			assertTrue(c.getX()>=0 && c.getX()<Constantes.TAMANNO && c.getY()>=0 && c.getY()<Constantes.TAMANNO);
		}
	}
	
	@Test
	public void testCoordenadasIguales() {
		Coordenada c1 = new Coordenada(2, 7);
		Coordenada c2 = new Coordenada(2, 7);
		assertEquals(c1, c2);
	}
	
	@Test
	public void testCoordenadasNoIguales() {
		Coordenada c1 = new Coordenada(2, 7);
		Coordenada c2 = new Coordenada(2, 6);
		Coordenada c3 = null;
		assertNotEquals(c1, c2);
		assertNotEquals(c1, c3);
	}
	
	@Test
	public void testToString() {
		Coordenada c1 = new Coordenada(2, 7);
		assertEquals("Coordenada [x = 2, y = 7]", c1.toString());
	}
	
	
	@Test
	public void testCoordenadaGoRightCorrecta() {
		Coordenada c = new Coordenada(Constantes.TAMANNO-2, 7);
		Coordenada expected = new Coordenada(Constantes.TAMANNO-1, 7);
		assertTrue(c.goRight());
		assertEquals(c.getX(), expected.getX());
	}
	
	@Test
	public void testCoordenadaGoRightIncorrecta() {
		Coordenada c = new Coordenada(Constantes.TAMANNO-1, 7);
		Coordenada expected = new Coordenada(Constantes.TAMANNO-1, 7);
		assertFalse(c.goRight());
		assertEquals(c.getX(), expected.getX());
	}
	
	@Test
	public void testCoordenadaGoLeftCorrecta() {
		Coordenada c = new Coordenada(1, 7);
		Coordenada expected = new Coordenada(0, 7);
		assertTrue(c.goLeft());
		assertEquals(c.getX(), expected.getX());
	}
	
	@Test
	public void testCoordenadaGoLeftIncorrecta() {
		Coordenada c = new Coordenada(0, 7);
		Coordenada expected = new Coordenada(0, 7);
		assertFalse(c.goLeft());
		assertEquals(c.getX(), expected.getX());
	}
	
	@Test
	public void testCoordenadaGoUpCorrecta() {
		Coordenada c = new Coordenada(8, 1);
		Coordenada expected = new Coordenada(8, 0);
		assertTrue(c.goUp());
		assertEquals(c.getY(), expected.getY());
	}
	
	@Test
	public void testCoordenadaGoUpIncorrecta() {
		Coordenada c = new Coordenada(8, 0);
		Coordenada expected = new Coordenada(8, 0);
		assertFalse(c.goUp());
		assertEquals(c.getY(), expected.getY());
	}
	
	@Test
	public void testCoordenadaGoDownCorrecta() {
		Coordenada c = new Coordenada(8, Constantes.TAMANNO-2);
		Coordenada expected = new Coordenada(8, Constantes.TAMANNO-1);
		assertTrue(c.goDown());
		assertEquals(c.getY(), expected.getY());
	}
	
	@Test
	public void testCoordenadaGoDownIncorrecta() {
		Coordenada c = new Coordenada(8, Constantes.TAMANNO-1);
		Coordenada expected = new Coordenada(8, Constantes.TAMANNO-1);
		assertFalse(c.goDown());
		assertEquals(c.getY(), expected.getY());
	}
	
	@Test
	public void testClonarCoordenada() {
		Coordenada c = new Coordenada(8, 9);
		Coordenada expected = c.clone();
		assertNotSame(c, expected);
		assertEquals(c, expected);
	}
	
}
