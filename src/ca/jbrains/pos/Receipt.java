package ca.jbrains.pos;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

	private String header;
	private List<ReceiptItem> items = new ArrayList<ReceiptItem>();

	public void setHeader(String header) {
		this.header = header;
	}

	public String getHeader() {
		return header;
	}

	public void addItem(ReceiptItem receiptItem) {
		items.add(receiptItem);
	}

	public Money getSubtotal() {
		Money result = new Money(0);
		for (ReceiptItem i: items){
			result = result.add(i.getPrice());
		}
		
		return result;
	}

	public Money getGST() {
		Money result = new Money(0);
		for (ReceiptItem i: items){
			result = result.add(i.getGST());
		}
		
		return result;
	}

	public Money getPST() {
		Money result = new Money(0);
		for (ReceiptItem i: items){
			result = result.add(i.getPST());
		}
		
		return result;
	}

	public Money getTotal() {
		return getSubtotal().add(getGST()).add(getPST());
	}

	public List<ReceiptItem> getItems() {
		return items;
	}
}
