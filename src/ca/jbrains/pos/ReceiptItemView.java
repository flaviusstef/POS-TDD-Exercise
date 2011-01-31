package ca.jbrains.pos;

public class ReceiptItemView implements IView {

	private ReceiptItem receiptItem;

	public ReceiptItemView(ReceiptItem receiptItem) {
		this.receiptItem = receiptItem;
	}

	public String getOutput() {
		String format = "%-20s $%10.2fG%s\n";
		return String.format(format, receiptItem.getProductName(), 0.01 * receiptItem.getPrice().getCents(), hasPST() ? "P" : "");
	}

	private boolean hasPST() {
		return ! receiptItem.getPST().equals(new Money(0));
	}

}
