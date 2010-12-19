package ca.jbrains.pos;

import java.util.Map;

class InMemoryCatalog implements Catalog {
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