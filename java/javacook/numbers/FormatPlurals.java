/**
 * Format a plural correctly, by hand.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: FormatPlurals.java,v 1.5 2001/04/21 00:16:33 ian Exp $
 */
public class FormatPlurals {
	public static void main(String[] argv) {
		report(0);
		report(1);
		report(2);
	}

	/** report -- using conditional operator */
	public static void report(int n) {
		System.out.println("We used " + n + " item" + (n==1?"":"s"));
	}
}
