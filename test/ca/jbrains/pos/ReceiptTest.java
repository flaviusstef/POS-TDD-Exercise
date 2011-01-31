package ca.jbrains.pos;
import org.junit.*;
import static org.junit.Assert.*;


public class ReceiptTest {

	private Receipt receipt;

	@Before
	public void setUp() {
		this.receipt = new Receipt();
	}
	
	@Test
	public void itStoresAHeader() {
		receipt.setHeader("receipt header");
		
		String header = receipt.getHeader();
		
		assertEquals("receipt header", header);
	}
	
	@Test
	public void addingAndRetrievingItems() {
		addItemsToReceipt();
		
		assertEquals(2, receipt.getItems().size());
	}
	
	@Test
	public void itProperlyComputesTheSubtotal() {
		addItemsToReceipt();
		
		assertEquals(new Money(2000+1000), receipt.getSubtotal());
	}

	@Test
	public void itProperlyComputesTheGST() {
		addItemsToReceipt();
		
		assertEquals(new Money(100+50), receipt.getGST());
	}

	@Test
	public void itProperlyComputesThePST() {
		addItemsToReceipt();
		
		assertEquals(new Money(210+105), receipt.getPST());
	}
	
	@Test
	public void itProperlyComputesTheTotal() {
		addItemsToReceipt();
		
		assertEquals(new Money(2000+1000+100+50+210+105), receipt.getTotal());
	}
	
	private void addItemsToReceipt() {
		ReceiptItem item1 = new ReceiptItem("one", new Money(2000), new Money(100), new Money(210));
		ReceiptItem item2 = new ReceiptItem("two", new Money(1000), new Money(50), new Money(105));
		receipt.addItem(item1);
		receipt.addItem(item2);
	}
}
