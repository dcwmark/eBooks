import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/** Parse an XML file using DOM, via JAXP.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id: XParse.java,v 1.4 2001/12/26 01:04:12 ian Exp $
 */
public class XParse {

	/** Convert the file */
	public static void parse(String fileName, boolean validate) {
		try {
			System.err.println("Parsing " + fileName + "...");

			// Make the document a URL so relative DTD works.
			String uri = "file:" + new File(fileName).getAbsolutePath();

			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			if (validate)
				f.setValidating(true);
			DocumentBuilder p = f.newDocumentBuilder();
			Document doc = p.parse(uri);
			System.out.println("Parsed OK");

		} catch (SAXException ex) {
			System.err.println("+================================+");
			System.err.println("|         *Parse Error*          |");
			System.err.println("+================================+");
			System.err.println(ex.getClass());
			System.err.println(ex.getMessage());
			System.err.println("+================================+");
		} catch (Exception ex) {
			System.err.println("+================================+");
			System.err.println("|           *XML Error*          |");
			System.err.println("+================================+");
			System.err.println(ex.toString()); 
		}
	}

	public static void main(String[] av) {
		if (av.length == 0) {
			System.err.println("Usage: XParse file");
			return;
		}
		boolean validate = false;
		for (int i=0; i<av.length; i++) {
			if (av[i].equals("-validate"))
				validate = true;
			else
				parse(av[i], validate);
		}
	}
}
