package ca.jbrains.pos;

public class ReceiptView implements IView {

	private Receipt receipt;

	public ReceiptView(Receipt receipt) {
		this.receipt = receipt;
	}

	public String getOutput() {
		StringBuffer sb = new StringBuffer();
		sb.append(receipt.getHeader());
		sb.append(receiptItems());
		sb.append(receiptLine("Subtotal", receipt.getSubtotal()));
		sb.append(receiptLine("GST", receipt.getGST()));
		sb.append(receiptLine("PST", receipt.getPST()));
		sb.append(receiptLine("Total", receipt.getTotal()));
		return sb.toString();
	}
	
	private String receiptItems() {
		StringBuffer result = new StringBuffer();
		for (ReceiptItem i: receipt.getItems()) {
			result.append(new ReceiptItemView(i).getOutput());
		}
		return result.toString();
	}

	private String receiptLine(String item, Money sum) {
		String format = "%-20s$%10.2f\n";
		return String.format(format, item, 0.01*sum.getCents());
	}
}
