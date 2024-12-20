/** StrCharAt - show String.charAt()
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: StrCharAt.java,v 1.2 2000/11/25 17:56:16 ian Exp $
 */

public class StrCharAt {
    public static void main(String[] av) {
        String a = "A quick bronze fox lept a lazy bovine";
		for (int i=0; i < a.length(); i++)
			System.out.println("Char " + i + " is " + a.charAt(i));
	}
}
