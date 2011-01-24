package ca.jbrains.pos;
import org.junit.*;
import static org.junit.Assert.*;

public class ProductTest {

	private Product p;
	
	@Before
	public void setUp() {
		String barcode = "123";
		Boolean pstExempt = false;
		Money price = Money.inCents(1000);
		p = new Product(barcode, price, pstExempt);		
	}
	
	@Test
	public void itBootstraps() {
		assertEquals("123", p.getBarcode());
		assertEquals(new Money(1000), p.getPrice());
		assertFalse(p.isPstExempt());
		p.setPstExempt(true);
		assertTrue(p.isPstExempt());
	}
}
