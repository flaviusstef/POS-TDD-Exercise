package ca.jbrains.pos;
import org.junit.*;

public class ReceiptViewTest {

	@Test
	public void emptyReceipt() {
		Receipt r = new Receipt();
		ReceiptView rv = new ReceiptView(r);
		
		String out = rv.getOutput();
		
		Util.assertMultilineStringMatches(out, ".*Subtotal.+\\$.+0.00.+");
		Util.assertMultilineStringMatches(out, ".+PST.+\\$.+0.00.+");
		Util.assertMultilineStringMatches(out, ".+GST.+\\$.+0.00.+");
		Util.assertMultilineStringMatches(out, ".+Total.+\\$.+0.00.+");
	}

	@Test
	public void nonEmptyReceipt() {
		Receipt r = new Receipt();
		String header = "JBRAINS.CA SHOP\n1 Johnson St\nAnywhere, ON  L1L 1L1\n905 555-1212\nGST# 991283742RT0001\n\n";
		r.setHeader(header);
		ReceiptItem item1 = new ReceiptItem("Quux", new Money(149287), new Money(7464), new Money(15675));
		r.addItem(item1);
		ReceiptItem item2 = new ReceiptItem("Foo", new Money(39225), new Money(1961), new Money(0));
		r.addItem(item2);
		ReceiptView rv = new ReceiptView(r);
		
		String out = rv.getOutput();
		
		Util.assertMultilineStringMatches(out, header+".+");
		
		Util.assertMultilineStringMatches(out, ".*Quux.+\\$.+1492.87GP.+");
		Util.assertMultilineStringMatches(out, ".+Foo.+\\$.+392.25G.+");

		Util.assertMultilineStringMatches(out, ".+Subtotal.+\\$.+1885.12.+");
		Util.assertMultilineStringMatches(out, ".+PST.+\\$.+156.75.+");
		Util.assertMultilineStringMatches(out, ".+GST.+\\$.+94.25.+");
		Util.assertMultilineStringMatches(out, ".+Total.+\\$.+2136.12.+");
	}
}
