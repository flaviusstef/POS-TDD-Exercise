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
}
