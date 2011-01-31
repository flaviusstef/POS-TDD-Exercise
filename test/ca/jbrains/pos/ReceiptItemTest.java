package ca.jbrains.pos;
import org.junit.*;
import static org.junit.Assert.*;


public class ReceiptItemTest {
	@Test
	public void itBootstraps() {
		ReceiptItem ri = new ReceiptItem("Cheese", new Money(10000), new Money(500), new Money(1050));
		
		assertEquals("Cheese", ri.getProductName());
		assertEquals(new Money(10000), ri.getPrice());
		assertEquals(new Money(500), ri.getGST());
		assertEquals(new Money(1050), ri.getPST());
	}
}
