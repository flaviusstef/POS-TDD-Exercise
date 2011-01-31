package ca.jbrains.pos;

import org.junit.*;
import static org.junit.Assert.*;

public class ReceiptItemViewTest {

	@Test
	public void withoutPST() {
		ReceiptItem receiptItem = new ReceiptItem("Without PST", new Money(1000), new Money(50), new Money(0));
		ReceiptItemView view = new ReceiptItemView(receiptItem);
		
		// "Without PST          $     10.00G"
		String out = view.getOutput();
		
		assertTrue(out.matches("Without PST.+\\$.+10.00G"));
	}

	@Test
	public void withPST() {
		ReceiptItem receiptItem = new ReceiptItem("With PST", new Money(1000), new Money(50), new Money(105));
		ReceiptItemView view = new ReceiptItemView(receiptItem);
		
		// "With PST             $     10.00GP"
		String out = view.getOutput();
		
		assertTrue(out.matches("With PST.+\\$.+10.00GP"));
	}
}
