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

}