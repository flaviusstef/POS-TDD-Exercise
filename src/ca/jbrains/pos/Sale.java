package ca.jbrains.pos;

class Sale {
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