package ca.jbrains.pos;
import org.junit.*;
import static org.junit.Assert.*;

public class POSDisplayTest {

	@Test
	public void itBootstraps() {
		new POSDisplay();
	}
	
	@Test
	public void itDisplaysProductPrices() {
		POSDisplay pd = new POSDisplay();
		pd.displayPrice(new Money(100));
		assertEquals("$1.00", pd.getText());
	}
}
