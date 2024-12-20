import java.io.*;

/**
 * Create one or more files by name.
 * The final "e" is omitted in homage to the underlying UNIX system call.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: Creat.java,v 1.2 2000/11/25 17:54:55 ian Exp $
 */
public class Creat {
	public static void main(String[] argv) throws IOException {

		// Ensure that a filename (or something) was given in argv[0]
		if (argv.length == 0) {
			System.err.println("Usage: Creat filename");
			System.exit(1);
		}

		for (int i = 0; i< argv.length; i++) {
			// Constructing a File object doesn't affect the disk, but
			// the createNewFile() method does.
			new File(argv[i]).createNewFile();
		}
	}
}
