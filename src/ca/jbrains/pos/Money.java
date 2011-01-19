package ca.jbrains.pos;

import java.text.NumberFormat;

class Money {
	private int cents;

	public Money(int cents) {
		this.cents = cents;
	}

	public static Money inCents(int cents) {
		return new Money(cents);
	}
	
	public Money add(Money other) {
		return new Money(this.getCents()+other.getCents());
	}

	public Money remove(Money lastPrice) {
		int difference = this.cents - lastPrice.getCents();
		if (difference < 0 ) {
			throw new IllegalArgumentException("Trying to remove too much");
		}
		
		return new Money(difference);
	}

	public int getCents() {
		return cents;
	}

	public String format() {
		return cents == 0 ? "FREE" : NumberFormat.getCurrencyInstance().format(cents / 100.0d);
	}
	
	public String toString() {
		return format();
	}
	
	public boolean equals(Object other) {
		if (other == null || other.getClass() != this.getClass()) {
			return false;
		}
		
		return this.cents == ((Money)other).getCents();
	}
}