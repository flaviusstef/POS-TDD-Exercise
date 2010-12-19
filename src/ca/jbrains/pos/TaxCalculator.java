package ca.jbrains.pos;

public class TaxCalculator {

	private static final float GST_TAX = 0.05f;
	private static final float PST_TAX = 0.1f;

	public Price calculate(Product product) {
		int taxableAmount = product.getPrice().getCents();
		int gst = Math.round(TaxCalculator.GST_TAX * taxableAmount);
		int pst = 0;
		if (!product.isPstExempt()) {
			pst = Math.round(TaxCalculator.PST_TAX * (gst + taxableAmount));
		}
		return new Price(gst+pst);
	}

}
