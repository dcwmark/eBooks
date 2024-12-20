/**
 * Log to arbitrary base
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: LogBase.java,v 1.2 1999/05/19 18:51:12 ian Exp $
 */
public class LogBase {
	//+
	public static double log_base(double base, double value) {
		return Math.log(value) / Math.log(base);
	}
	//-
}
