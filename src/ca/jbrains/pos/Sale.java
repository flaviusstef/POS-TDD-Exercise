package ca.jbrains.pos;

class Sale {
	private POSDisplay posDisplay;
	private Catalog catalog;
	private Money totalCharge = new Money(0);
	private Money lastPrice = new Money(0);

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

	public Money totalCharge() {
		return totalCharge;
	}
	
	private void sellProduct(Product p) {
		posDisplay.displayPrice(p.getPrice());
		Money price = p.getPriceWithTax();
		lastPrice  = price;
		totalCharge = totalCharge.add(price);
	}

	public void complete() {
		posDisplay.displayTotalPrice(totalCharge);
		totalCharge = new Money(0);
	}

	public void overrideLastPriceWith(Money desired) {
		totalCharge = totalCharge.remove(lastPrice).add(desired);
	}
}