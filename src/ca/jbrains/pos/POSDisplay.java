package ca.jbrains.pos;

class POSDisplay {
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

	public void displayPrice(Money price) {
		setText(price.format());
	}

	public void displayTotalPrice(Money price) {
		setText("Total price: " + price.format());
	}
}
