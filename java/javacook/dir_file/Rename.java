import java.io.*;

/**
 * Rename a file in Java
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: Rename.java,v 1.3 2001/04/07 01:35:51 ian Exp $
 */
public class Rename {
	public static void main(String[] argv) throws IOException {

		// Construct the file object. Does NOT create a file on disk!
		File f = new File("Rename.java~"); // backup of this source file.

		// Rename the backup file to "junk.dat"
		// Renaming requires a File object for the target.
		f.renameTo(new File("junk.dat"));
	}
}
