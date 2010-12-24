package ca.jbrains.pos;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import static org.junit.Assert.*;

public class InMemoryCatalogTest {
	
	private List<Product> productList;
	
	@Before
	public void setUp() {
		productList = new ArrayList<Product>() {
			private static final long serialVersionUID = 1L;
			{
				add(new Product("123", new Price(100), false));
				add(new Product("456", new Price(200), false));
			}
		};		
	}
	
	@Test(expected=RuntimeException.class)
	public void nullProductListIsInvalid() {
		new InMemoryCatalog(null);
	}
	
	@Test
	public void itRetrievesPrices() {
		InMemoryCatalog c = new InMemoryCatalog(productList);
		assertNotNull(c.lookupProduct("123"));
		assertNull(c.lookupProduct("999"));
	}
	
	@Test
	public void itRemembersBarcodes() {
		InMemoryCatalog c = new InMemoryCatalog(productList);
		assertTrue(c.hasBarcode("123"));
		assertFalse(c.hasBarcode("789"));
	}
}