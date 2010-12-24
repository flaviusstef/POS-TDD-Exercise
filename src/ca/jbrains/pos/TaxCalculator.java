package ca.jbrains.pos;

public class TaxCalculator {

	public static final int GST_PERCENT = 5;
	public static final float PST_PERCENT = 10;
	
	private Product product;
		
	public TaxCalculator(Product product) {
		this.product = product;
	}

	public Money getGST() {
		int cents = product.getPrice().getCents();
		return new Money(Math.round(TaxCalculator.GST_PERCENT / 100f * cents));
	}
	
	public Money getPST() {
		if (product.isPstExempt()) {
			return new Money(0);
		}
		
		int cents = product.getPrice().getCents() + getGST().getCents();
		return new Money(Math.round(TaxCalculator.PST_PERCENT / 100f * cents));
	}
	
	public Money getTotalTax() {
		return getGST().add(getPST());
	}
}