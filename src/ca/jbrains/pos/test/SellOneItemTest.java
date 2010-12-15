package ca.jbrains.pos.test;

import org.junit.*;
import static org.junit.Assert.*;

import java.text.NumberFormat;
import java.util.*;

public class SellOneItemTest {
  private POSDisplay posDisplay = new POSDisplay();
  private Sale sale;

  public interface Catalog {
    Price lookupPrice(String barcode);
    boolean hasBarcode(String barcode);
  }

  public static class InMemoryCatalog implements Catalog {
    private Map<String, Price> pricesByBarcode;

    public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
      if (pricesByBarcode == null) {
        throw new IllegalArgumentException("pricesByBarcode = " + pricesByBarcode);
      }

      this.pricesByBarcode = pricesByBarcode;
    }

    public Price lookupPrice(String barcode) {
      return pricesByBarcode.get(barcode);
    }

    public boolean hasBarcode(String barcode) {
      return pricesByBarcode.containsKey(barcode);
    }
  }

  public static class Sale {
    private POSDisplay posDisplay;
    private Catalog catalog;

    public Sale(POSDisplay posDisplay, Catalog catalog) {
      this.posDisplay = posDisplay;
      this.catalog = catalog;
    }

    public void onBarcode(String barcode) {
      if ("".equals(barcode)) {
        posDisplay.displayScannedEmptyBarcodeMessage();
        return;
      }

      if (!catalog.hasBarcode(barcode)) {
        posDisplay.displayProductNotFoundMessage(barcode);
        return;
      }

      posDisplay.displayPrice(catalog.lookupPrice(barcode));
    }
  }

  public static class Price {
    private int cents;

    public Price(int cents) {
      this.cents = cents;
    }

    public static Price inCents(int cents) {
      return new Price(cents);
    }

    public String format() {
      return cents == 0 ? "FREE" : NumberFormat.getCurrencyInstance().format(cents / 100.0d);
    }
  }

  public static class POSDisplay {
    private String text;

    private void setText(String text) {
      this.text = text;
    }

    public String getText() {
      return text;
    }

    public void displayScannedEmptyBarcodeMessage() {
      setText("Scanning error: empty barcode");
    }

    public void displayProductNotFoundMessage(String barcode) {
      setText("No product with barcode " + barcode);
    }

    public void displayPrice(Price price) {
      setText(price.format());
    }
  }

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

  @Test
    public void freeProductHasDistinctFormat() throws Exception {
      assertEquals("FREE", Price.inCents(0).format());
    }

  @Test(expected=RuntimeException.class)
    public void nullProductListIsInvalid() {
      new InMemoryCatalog(null);
    }

  private void assertForBarcodeDisplayShows(String barcode, String message) {
    sale.onBarcode(barcode);
    assertEquals(message, posDisplay.getText());
  }
}
