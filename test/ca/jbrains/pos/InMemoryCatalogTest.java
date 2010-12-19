package ca.jbrains.pos;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import static org.junit.Assert.*;

public class InMemoryCatalogTest {
	
	private Map<String, Price> priceList;
	
	@Before
	public void setUp() {
		priceList = new HashMap<String,Price>() {
			private static final long serialVersionUID = 1L;

			{
				put("123", new Price(100));
				put("456", new Price(200));
			}
		};		
	}

	@Test
	public void itBootstraps() {
		new InMemoryCatalog(priceList);
	}
	
	@Test(expected=RuntimeException.class)
	public void nullProductListIsInvalid() {
		new InMemoryCatalog(null);
	}
	
	@Test
	public void itRetrievesPrices() {
		InMemoryCatalog c = new InMemoryCatalog(priceList);
		assertNotNull(c.lookupPrice("123"));
		assertNull(c.lookupPrice("999"));
	}
	
	@Test
	public void itRemembersBarcodes() {
		InMemoryCatalog c = new InMemoryCatalog(priceList);
		assertTrue(c.hasBarcode("123"));
		assertFalse(c.hasBarcode("789"));
	}
}