import java.io.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;

/** Convert a simple XML file to text.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id: XmlForm.java,v 1.17 2000/11/25 17:56:34 ian Exp $
 */
public class XmlForm {
	protected Reader is;
	protected String fileName;

	protected static PrintStream msg = System.out;

	/** Construct a converter given an input filename */
	public XmlForm(String fn) {
		fileName = fn;
	}

	/** Convert the file */
	public void convert(boolean verbose) {
		try {
			if (verbose)
				System.err.println(">>>Parsing " + fileName + "...");
			// Make the document a URL so relative DTD works.
			String uri = "file:" + new File(fileName).getAbsolutePath();
			XmlDocument doc = XmlDocument.createXmlDocument(uri);
			if (verbose)
				System.err.println(">>>Walking " + fileName + "...");
			XmlFormWalker c = new GenMIF(doc, msg);
			c.convertAll();

		} catch (Exception ex) {
			System.err.println("+================================+");
			System.err.println("|         *Parse Error*          |");
			System.err.println("+================================+");
			System.err.println(ex.getClass());
			System.err.println(ex.getMessage());
			System.err.println("+================================+");
		}
		if (verbose)
			System.err.println(">>>Done " + fileName + "...");
	}

	public static void main(String[] av) {
		if (av.length == 0) {
			System.err.println("Usage: XmlForm file");
			return;
		}
		for (int i=0; i<av.length; i++) {
			String name = av[i];
			new XmlForm(name).convert(true);
		}
		msg.close();
	}
}
