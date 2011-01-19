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
				add(new Product("123", new Money(1000), false));
				add(new Product("456", new Money(2000), false));
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
		assertEquals(new Money(0), sale.totalCharge());
	}
	
	@Test
	public void productPriceWithTaxIsAddedToTotalCharge() {
		sale.onBarcode("123");
		assertEquals(new Money(1000+155), sale.totalCharge());
		sale.onBarcode("123");
		assertEquals(new Money(1000+155+1000+155), sale.totalCharge());
	}
	
	@Test
	public void completingSaleResetsTotalCharge() {
		sale.complete();
		
		assertEquals(new Money(0), sale.totalCharge());
	}
	
	@Test
	public void completingSaleShowsTotalOnDisplay() {
		sale.onBarcode("123");
		assertEquals(new Money(1155), sale.totalCharge());
		
		sale.complete();
		
		assertEquals("Total price: $11.55", posDisplay.getText());
	}

	@Test
	public void priceOfLastProductCanBeOverridenIfTwoProductsPurchased() {
		sale.onBarcode("123");
		// guard assertion
		assertEquals(new Money(1155), sale.totalCharge());
		sale.onBarcode("123");
		// guard assertion
		assertEquals(new Money(2310), sale.totalCharge());

		sale.overrideLastPriceWith(new Money(1000));
		
		assertEquals(new Money(2155), sale.totalCharge());
	}

	private void assertForBarcodeDisplayShows(String barcode, String message) {
		sale.onBarcode(barcode);
		assertEquals(message, posDisplay.getText());
	}
}
