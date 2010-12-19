package ca.jbrains.pos;

import java.text.NumberFormat;

class Price {
	private int cents;

	public Price(int cents) {
		this.cents = cents;
	}

	public static Price inCents(int cents) {
		return new Price(cents);
	}

	public int getCents() {
		return cents;
	}

	public String format() {
		return cents == 0 ? "FREE" : NumberFormat.getCurrencyInstance().format(cents / 100.0d);
	}
	
	public boolean equals(Object other) {
		if (other == null || other.getClass() != this.getClass()) {
			return false;
		}
		
		return this.cents == ((Price)other).getCents();
	}

	public Price add(Price other) {
		return new Price(this.getCents()+other.getCents());
	}
	
	public String toString() {
		return format();
	}

}