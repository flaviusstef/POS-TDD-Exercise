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
		sale = new Sale(posDisplay, new InMemoryCatalog(new HashMap<String, Price>() {
			{
				put("123", Price.inCents(1250));
				put("456", Price.inCents(2000));
				put("333", Price.inCents(0));
			}
		}));
	}

	@Test
	public void productFound() throws Exception {
		assertForBarcodeDisplayShows("123", "$12.50");
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

	private void assertForBarcodeDisplayShows(String barcode, String message) {
		sale.onBarcode(barcode);
		assertEquals(message, posDisplay.getText());
	}
}
