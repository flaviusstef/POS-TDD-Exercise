package ca.jbrains.pos;

public class Product {

	private String barcode;
	private Price price;
	private Boolean pstExempt;

	public Product(String barcode, Price price, Boolean pstExempt) {
		this.barcode = barcode;
		this.price = price;
		this.pstExempt = pstExempt;
	}

	public String getBarcode() {
		return barcode;
	}
	
	public Price getPrice() {
		return price;
	}
	
	public Boolean isPstExempt() {
		return pstExempt;
	}
	
	public void setPstExempt(Boolean pstExempt) {
		this.pstExempt = pstExempt;
	}

	public Price priceWithTax() {
		Price tax = new TaxCalculator().calculate(this);
		return this.price.add(tax);
	}

}
