/**
 * Reverse a string by character
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: StringRevChar.java,v 1.2 2000/11/25 17:56:18 ian Exp $
 */
public class StringRevChar {
	public static void main(String[] argv) {
		//+
		String sh = "FCGDAEB";
		System.out.println(sh + " -> " + new StringBuffer(sh).reverse());
		//-
	}
}
