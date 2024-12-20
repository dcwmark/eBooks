import java.io.*;

/**
 * Multiple lines output from one method.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: GoodNewline.java,v 1.3 2001/03/13 20:32:33 ian Exp $
 */
public class GoodNewline {
	//+
	String myName;
	public static void main(String[] argv) {
		GoodNewline jack = new GoodNewline("Jack Adolphus Schmidt, III");
		jack.print(System.out);
	}

	protected void print(PrintStream out) {
		out.println(toString());	// classname and hashcode
		out.println(myName);		// print name  on next line
	}

	//-
	/* Constructor */
	public GoodNewline(String s) {
		myName = s;
	}
}
