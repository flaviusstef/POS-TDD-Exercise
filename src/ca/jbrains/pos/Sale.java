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

		sellProductWithBarcode(barcode);
	}

	public Money totalCharge() {
		return totalCharge;
	}
	
	private void sellProductWithBarcode(String barcode) {
		Product p = catalog.lookupProduct(barcode);
		Money price = p.getPrice();
		posDisplay.displayPrice(price);
		lastPrice  = price.add(getTax(p));
		totalCharge = totalCharge.add(lastPrice);
	}
	
	private Money getTax(Product p) {
		return new TaxCalculator(p).getTotalTax();
	}

	public void complete() {
		posDisplay.displayTotalPrice(totalCharge);
		totalCharge = new Money(0);
	}

	public void overrideLastPriceWith(Money desired) {
		totalCharge = totalCharge.remove(lastPrice).add(desired);
	}
}