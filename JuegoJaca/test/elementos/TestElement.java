package elementos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestElement {
	
	@Test
	public void testGetTypeElemento() {
		Element e = new Element(ElementType.GEMA);
		assertTrue(ElementType.GEMA.equals(e.getType()) && e.toString().equals("Element [type = GEMA]"));
	}
	
	@Test
	public void testElementosNoIguales() {
		Element e1 = new Element(ElementType.GEMA);
		Element e2 = new Element(ElementType.ROCA);
		Element e3 = null;
		assertNotEquals(e1, e2);
		assertNotEquals(e1, e3);
	}
	
	@Test
	public void testElementosRocaIguales() {
		Element e1 = new Element(ElementType.ROCA);
		Element e2 = new Element(ElementType.ROCA);
		assertEquals(e1, e2);
	}
	
	@Test
	public void testElementosGemaIguales() {
		Element e1 = new Element(ElementType.GEMA);
		Element e2 = new Element(ElementType.GEMA);
		assertEquals(e1, e2);
	}
	
	@Test
	public void testElementosDineroIguales() {
		Element e1 = new Element(ElementType.DINERO);
		Element e2 = new Element(ElementType.DINERO);
		assertEquals(e1, e2);
	}
	
	@Test
	public void testElementosPocionIguales() {
		Element e1 = new Element(ElementType.POCION);
		Element e2 = new Element(ElementType.POCION);
		assertEquals(e1, e2);
	}
}

