package ca.jbrains.pos;

class Sale {
	private POSDisplay posDisplay;
	private Catalog catalog;
	private Price totalCharge = new Price(0);

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

		Product p = catalog.lookupProduct(barcode);
		sellProduct(p);
	}

	public Price totalCharge() {
		return totalCharge;
	}
	
	private void sellProduct(Product p) {
		posDisplay.displayPrice(p.getPrice());
		totalCharge = totalCharge.add(p.getPriceWithTax());
	}

	public void complete() {
		posDisplay.displayTotalPrice(totalCharge);
		totalCharge = new Price(0);
	}
}