package ca.jbrains.pos;

public class Product {

	private String barcode;
	private Money price;
	private Boolean pstExempt;

	public Product(String barcode, Money price, Boolean pstExempt) {
		this.barcode = barcode;
		this.price = price;
		this.pstExempt = pstExempt;
	}

	public String getBarcode() {
		return barcode;
	}
	
	public Money getPrice() {
		return price;
	}
	
	public Boolean isPstExempt() {
		return pstExempt;
	}
	
	public void setPstExempt(Boolean pstExempt) {
		this.pstExempt = pstExempt;
	}

	public Money getPriceWithTax() {
		Money tax = new TaxCalculator(this).getTotalTax();
		return this.price.add(tax);
	}

}
