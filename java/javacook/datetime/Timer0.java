import java.io.*;
import java.text.*;

/**
 * How quickly can you press return?
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id: Timer0.java,v 1.3 2000/11/25 17:54:53 ian Exp $
 */
public class Timer0 {
	public static void main(String[] argv) throws IOException {
		//+
		long t0, t1;
		System.out.println("Press return when ready");
		t0=System.currentTimeMillis();
		int b;
		do {
			b = System.in.read();
		} while (b!='\r' && b != '\n');
		t1=System.currentTimeMillis();
		double deltaT = t1-t0;
		System.out.println("You took " + 
			DecimalFormat.getInstance().format(deltaT/1000.) + " seconds.");
		//-
	}
}
