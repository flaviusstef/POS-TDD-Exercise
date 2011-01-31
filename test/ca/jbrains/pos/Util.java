package ca.jbrains.pos;

import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

public class Util {

	public static void assertMultilineStringMatches(String multilineString, String regexp) {
		Pattern p = Pattern.compile(regexp, Pattern.DOTALL);
		String message = String.format("String \"%s\" should have matched /%s/", multilineString, regexp);
		assertTrue(message, p.matcher(multilineString).matches());
	}
}
