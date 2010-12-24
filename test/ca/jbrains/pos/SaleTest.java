package ca.jbrains.pos;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class SaleTest {
	private POSDisplay posDisplay = new POSDisplay();
	private Sale sale;

	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		sale = new Sale(posDisplay, new InMemoryCatalog(new ArrayList<Product>() {
			{
				add(new Product("123", new Price(1000), false));
				add(new Product("456", new Price(2000), false));
			}
		}));
	}

	@Test
	public void productFound() throws Exception {
		assertForBarcodeDisplayShows("123", "$10.00");
	}

	@Test
	public void anotherProductFound() throws Exception {
		assertForBarcodeDisplayShows("456", "$20.00");
	}

	@Test
	public void productNotFound() throws Exception {
		assertForBarcodeDisplayShows("999", "No product with barcode 999");
	}

	@Test
	public void emptyBarcode() throws Exception {
		assertForBarcodeDisplayShows("", "Scanning error: empty barcode");
	}
	
	@Test
	public void initialTotalChargeIsZero() {
		assertEquals(new Price(0), sale.totalCharge());
	}
	
	@Test
	public void productPriceWithTaxIsAddedToTotalCharge() {
		sale.onBarcode("123");
		assertEquals(new Price(1155), sale.totalCharge());
		sale.onBarcode("123");
		assertEquals(new Price(2310), sale.totalCharge());
	}
	
	@Test
	public void completingSaleResetsTotalCharge() {
		sale.complete();
		
		assertEquals(new Price(0), sale.totalCharge());
	}
	
	@Test
	public void completingSaleShowsTotalOnDisplay() {
		sale.onBarcode("123");
		assertEquals(new Price(1155), sale.totalCharge());
		sale.onBarcode("456");
		assertEquals(new Price(3465), sale.totalCharge());
		
		sale.complete();
		
		assertEquals("Total price: $34.65", posDisplay.getText());
	}

	private void assertForBarcodeDisplayShows(String barcode, String message) {
		sale.onBarcode(barcode);
		assertEquals(message, posDisplay.getText());
	}
}
