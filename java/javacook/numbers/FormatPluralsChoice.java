import java.text.*;

/**
 * Format a plural correctly, using a ChoiceFormat.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: FormatPluralsChoice.java,v 1.3 2001/04/21 00:16:33 ian Exp $
 */
public class FormatPluralsChoice extends FormatPlurals {
	static double[] limits = { 0, 1, 2 };
	static String[] formats = { "items", "item", "items"};
	static ChoiceFormat myFormat = new ChoiceFormat(limits, formats);

	public static void main(String[] argv) {
		report(0);	// inherited method
		report(1);
		report(2);
	}
}
