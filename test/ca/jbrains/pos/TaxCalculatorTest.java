package ca.jbrains.pos;
import org.junit.*;
import static org.junit.Assert.*;

public class TaxCalculatorTest {

	@Test
	public void itComputesGST() {
		TaxCalculator tc = new TaxCalculator(new Product("123", new Money(200), false));
		
		Money expected = new Money(Math.round(200*0.05f)); 
		assertEquals(expected, tc.getGST());
	}
	
	@Test
	public void itComputesPST() {
		TaxCalculator tc = new TaxCalculator(new Product("123", new Money(200), false));
		
		Money expected = new Money(Math.round(200*1.05f*0.1f)); 
		assertEquals(expected, tc.getPST());
	}
	
	@Test
	public void itKnowsSomeItemsArePSTExempt() {
		TaxCalculator tc = new TaxCalculator(new Product("123", new Money(200), true));
		
		Money expected = new Money(0); 
		assertEquals(expected, tc.getPST());
	}
	
	@Test
	public void itComputesTotalTax() {
		TaxCalculator tc = new TaxCalculator(new Product("123", new Money(200), false));
		
		Money expected = new Money(Math.round(200*0.05f + 200*1.05f*0.1f));
		assertEquals(expected, tc.getTotalTax());
	}
}
