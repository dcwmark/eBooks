import java.io.*;

/** detab- replace blanks by tabs and blanks.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: Detab.java,v 1.2 2000/11/25 17:56:14 ian Exp $
 */
public class Detab extends Tabs {

	public static void main(String[] argv) throws IOException {
		Detab dt = new Detab(8);
		dt.detab(new BufferedReader(new InputStreamReader(System.in)));
	}

	public Detab(int n) {
		super(n);
	}

	public void detab(BufferedReader is) throws IOException {
		String line;
		char c;
		int col;
		while ((line = is.readLine()) != null) {
			col = 0;
			for (int i=0; i<line.length(); i++) {
				// Either ordinary character or tab.
				if ((c=line.charAt(i)) != '\t') {
					System.out.print(c); // Ordinary
					++col;
					continue;
				}
				do { // Tab, expand it, must put >=1 space
					System.out.print(' ');
				} while (!tabpos(++col));
			}
			System.out.println();
		}
	}
}
