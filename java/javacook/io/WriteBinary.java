import java.io.*;

/**
 * Write some data in binary.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: WriteBinary.java,v 1.2 2000/11/25 17:55:34 ian Exp $
 */
public class WriteBinary {
	public static void main(String[] argv) throws IOException {
		int i = 42;
		double d = Math.PI;
		String FILENAME = "binary.dat";
		DataOutputStream os = new DataOutputStream(
			new FileOutputStream(FILENAME));
		os.writeInt(i);
		os.writeDouble(d);
		os.close();
		System.out.println("Wrote " + i + ", " + d + " to file " + FILENAME);
	}
}
