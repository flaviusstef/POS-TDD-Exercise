package ca.jbrains.pos;

import org.junit.Test;
import static org.junit.Assert.*;

public class MoneyTest {

	@Test
	public void itBootstraps() {
		Money p = new Money(100);
		assertEquals(100, p.getCents());
	}
	
	@Test
	public void itBootstrapsViaFactory() {
		Money p = Money.inCents(100);
		assertEquals(100, p.getCents());		
	}
	
	@Test
	public void itFormatsThePrice() {
		Money p = new Money(123);
		assertEquals("$1.23", p.format());
	}
	
	@Test
	public void itFormatsZeroCentsDifferently() {
		Money p = new Money(0);
		assertEquals("FREE", p.format());
	}
	
	@Test
	public void testEquality() {
		Money p1 = new Money(100);
		Money p2 = new Money(101);
		Money p3 = new Money(101);
		
		assertFalse(p1.equals(null));
		assertFalse(p1.equals("not_a_product"));
		assertFalse(p1.equals(p2));
		assertTrue(p2.equals(p3));
	}
	
	@Test
	public void testAddition() {
		Money p1 = new Money(100);
		Money p2 = new Money(200);
		assertEquals(new Money(300), p1.add(p2));
	}
	
	@Test
	public void itCanRemoveLessMoneyFromMoreMoney() {
		Money more = new Money(200);
		Money less = new Money(100);
		
		Money result = more.remove(less);
		
		assertEquals(new Money(100), result);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void itCannotRemoveMoreMoneyFromLessMoney() {
		Money more = new Money(200);
		Money less = new Money(100);
		
		// this will throw an Exception
		less.remove(more);
	}
}
