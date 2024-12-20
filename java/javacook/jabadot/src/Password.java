package jabadot;

public class Password {
	/** Minimum length for a decent password */
	public static final int MIN_LENGTH = 8;

	/** The random number generator. */
	protected static java.util.Random r = new java.util.Random();

	/** This password's value as a String */
	protected String pw;

	/** Construct a password using the given String as the "user's pick".
	 * @throws java.lang.IllegalArgumentException If password too short.
	 */
	public Password(String p) {
		if (p.length() < MIN_LENGTH)
			throw new IllegalArgumentException(
			"Password " + p + " length less than minimum of " + MIN_LENGTH);
		pw = p;
	}

	/** Construct a password: we pick the password. 
	 * This constructor is wasteful: you are better off using the
	 * static method getNext(), which returns a new Password object.
	 */
	public Password() {
		pw = getNext().toString();
	}

	/** Return this Password as a String */
	public String toString() {
		return pw;
	}

	/** Return this Password as a String */
	public String getPassword() {
		return pw;
	}

	/* Set of characters that is valid. Must be printable, memorable,
	 * and "won't break HTML" (i.e., not '<', '>', '&', '=', ...).
	 * I, L and O are good to leave out, as are numeric zero and one.
	 */
	protected static char[] goodChar = {
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
		'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N',
		'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'2', '3', '4', '5', '6', '7', '8', '9',
		'+', '-', '!', '@', '$', '^', '?',
	};

	/* Generate a Password object with a random password. */
	public static Password getNext() {
		StringBuffer sb = new StringBuffer();
		for (int i=0; i < MIN_LENGTH + 2; i++) {
			sb.append(goodChar[r.nextInt(goodChar.length)]);
		}
		return new Password(sb.toString());
	}

	public static void main(String[] argv) {
		for (int i=0; i<20; i++) {
			System.out.println(Password.getNext());
		}
	}
}
