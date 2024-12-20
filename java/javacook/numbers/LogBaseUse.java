/**
 * Log to arbitrary base
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: LogBaseUse.java,v 1.2 2000/11/25 17:56:03 ian Exp $
 */
public class LogBaseUse {
	//+
	public static void main(String[] argv) {
		double d = LogBase.log_base(10, 10000);
		System.out.println("log10(10000) = " + d);
	}
	//-
}
