/**
 * DON'T DO THIS. THIS IS BAD CODE.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: BadNewline.java,v 1.2 2000/11/25 17:55:27 ian Exp $
 */
public class BadNewline {
	//+
	String myName;
	public static void main(String[] argv) {
		BadNewline jack = new BadNewline("Jack Adolphus Schmidt, III");
		System.out.println(jack);
	}
	/**
	 * DON'T DO THIS. THIS IS BAD CODE.
	 */
	public String toString() {
		return "BadNewlineDemo@" + hashCode() + "\n" + myName;
	}

	// The obvious Constructor is not shown for brevity; it's in the code
	//-
	/* Constructor */
	public BadNewline(String s) {
		myName = s;
	}
}
