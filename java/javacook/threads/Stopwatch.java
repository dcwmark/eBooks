/**
 * Count the seconds up from zero, until terminated.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: Stopwatch.java,v 1.3 2000/11/25 17:54:20 ian Exp $
 */
public class Stopwatch {
	public static void main(String[] argv) {
		//+
		long t0 = System.currentTimeMillis();
		while (true) {
			long t1 = System.currentTimeMillis();
			int seconds = (int) (t1-t0)/1000;
			System.out.print("Elapsed: " + seconds + "\r");
			try {
				Thread.sleep(999);
			} catch (InterruptedException e) {
				// nothing to say
			}
		}
		//-
	}
}
