package ca.jbrains.pos;

public class ReceiptItem {

	private String productName;
	private Money price;
	private Money gst;
	private Money pst;

	public ReceiptItem(String productName, Money price, Money gst, Money pst) {
		this.productName = productName;
		this.price = price;
		this.gst = gst;
		this.pst = pst;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public Money getPrice() {
		return price;
	}
	
	public Money getGST() {
		return gst;
	}
	
	public Money getPST() {
		return pst;
	}
}
