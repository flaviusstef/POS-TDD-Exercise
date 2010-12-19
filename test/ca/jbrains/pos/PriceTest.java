package ca.jbrains.pos;

import org.junit.Test;
import static org.junit.Assert.*;

public class PriceTest {

	@Test
	public void itBootstraps() {
		Price p = new Price(100);
		assertEquals(100, p.getCents());
	}
	
	@Test
	public void itBootstrapsViaFactory() {
		Price p = Price.inCents(100);
		assertEquals(100, p.getCents());		
	}
	
	@Test
	public void itFormatsThePrice() {
		Price p = new Price(123);
		assertEquals("$1.23", p.format());
	}
	
	@Test
	public void itFormatsZeroCentsDifferently() {
		Price p = new Price(0);
		assertEquals("FREE", p.format());
	}
	
	@Test
	public void testEquality() {
		Price p1 = new Price(100);
		Price p2 = new Price(101);
		Price p3 = new Price(101);
		
		assertFalse(p1.equals(null));
		assertFalse(p1.equals("not_a_product"));
		assertFalse(p1.equals(p2));
		assertTrue(p2.equals(p3));
	}
	
	@Test
	public void testAddition() {
		Price p1 = new Price(100);
		Price p2 = new Price(200);
		assertEquals(new Price(300), p1.add(p2));
	}
}
