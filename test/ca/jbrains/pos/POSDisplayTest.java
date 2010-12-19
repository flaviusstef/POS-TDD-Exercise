package ca.jbrains.pos;
import org.junit.*;
import static org.junit.Assert.*;

public class POSDisplayTest {

	@Test
	public void itBootstraps() {
		new POSDisplay();
	}
	
	@Test
	public void itDisplaysPrices() {
		POSDisplay pd = new POSDisplay();
		pd.displayPrice(Price.inCents(100));
		assertEquals("$1.00", pd.getText());
	}
}
