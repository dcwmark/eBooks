import org.apache.regexp.*;

/**
 * REsubstr -- demonstrate RE Match -> String.substring()
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: REsubstr.java,v 1.6 2001/03/15 19:54:58 ian Exp $
 */
public class REsubstr {
	public static void main(String[] argv) throws RESyntaxException {
		//+
		String patt = "Q[^u]\\d+\\.";
		RE r = new RE(patt);
		String line = "Order QT300. Now!";
		if (r.match(line)) {
			System.out.println(patt + " matches \"" + 
				line.substring(r.getParenStart(0), r.getParenEnd(0)) +
				"\" in \"" + line + "\"");
		} else {
			System.out.println("NO MATCH");
		}
	}
}
