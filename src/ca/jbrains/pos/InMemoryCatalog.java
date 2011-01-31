package ca.jbrains.pos;

import java.util.List;

class InMemoryCatalog implements ICatalog {
	private List<Product> pricesByBarcode;

	public InMemoryCatalog(List<Product> pricesByBarcode) {
		if (pricesByBarcode == null) {
			throw new IllegalArgumentException("pricesByBarcode = " + pricesByBarcode);
		}

		this.pricesByBarcode = pricesByBarcode;
	}

	public Product lookupProduct(String barcode) {
		for (Product p: pricesByBarcode) {
			if (p.getBarcode().equals(barcode)) {
				return p;
			}
		}
		return null;
	}

	public boolean hasBarcode(String barcode) {
		for (Product p: pricesByBarcode) {
			if (p.getBarcode().equals(barcode)) {
				return true;
			}
		}
		return false;
	}
}