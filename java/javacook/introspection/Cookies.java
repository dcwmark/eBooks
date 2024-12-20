/**
 * This is the part of the Cookies application that loads
 * the user-defined subclass.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: Cookies.java,v 1.2 2000/11/25 17:55:24 ian Exp $
 */
public class Cookies {
	public static void main(String[] argv) {
		System.out.println("Cookies Application Version 0.0");
		Cooklet cooklet = null;
		String cookletClassName = argv[0];
		try {
			Class cookletClass = Class.forName(cookletClassName);
			Object cookletObject = cookletClass.newInstance();
			cooklet = (Cooklet)cookletObject;
		} catch (Exception e) {
			System.err.println("Error " + cookletClassName + e);
		}
		cooklet.initialize();
		cooklet.work();
		cooklet.terminate();
	}
}
