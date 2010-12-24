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
	
	@Test
	public void itComputesPriceTax() {
		p.setPstExempt(true);
		int expectedExempt = Math.round(1000.0f*(1.0f+0.05f));
		assertEquals(Money.inCents(expectedExempt), p.getPriceWithTax());
		
		p.setPstExempt(false);
		int expectedNotExempt = Math.round(1000.0f*(1.0f+0.05f)*(1.0f+0.1f));
		assertEquals(Money.inCents(expectedNotExempt), p.getPriceWithTax());
	}
}
