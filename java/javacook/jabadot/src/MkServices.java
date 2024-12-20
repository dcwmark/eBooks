import jabadot.*;	 // NOT IN package jabadot

import java.io.*;
import java.util.*;

public class MkServices {
	protected final static String INPUT_NAME = "services.txt";
	protected final static String PUBLIC_NAME = "public_services.html";
	protected final static String LOGGEDIN_NAME = "logged_in_services.html";
	// protected final static String TEASER_NAME = "restricted.html";


	protected MkServices() throws IOException {
	}

	protected void run() throws IOException {
		BufferedReader is = new BufferedReader(new FileReader(INPUT_NAME));
		PrintWriter pub = new PrintWriter(new FileWriter(PUBLIC_NAME));
		PrintWriter logged = new PrintWriter(new FileWriter(LOGGEDIN_NAME));
		// PrintWriter teaser = new PrintWriter(new FileWriter(TEASER_NAME));
		String line;
		while ((line = is.readLine()) != null) {
			// Login:P:login.jsp
			// Search:L:search.jsp

			if (line.startsWith("#")) {		// comment
				continue;
			}

			StringTokenizer st = new StringTokenizer(line, ":");
			String name = st.nextToken();
			String perm = st.nextToken();
			String url = st.nextToken();

			// if P-only
			if (perm.indexOf('P')!=-1 && perm.indexOf('L')==-1) {
				mkLink(pub, url, name);
			} else
				// if L and P
				if (perm.indexOf('L')!=-1 && perm.indexOf('P')!=-1) {
					mkLink(pub, url, name);
					mkLink(logged, url, name);
			} else
				// if L and !P
				if (perm.indexOf('L')!=-1 && perm.indexOf('P')==-1) {
					mkLink(logged, url, name);
					pub.print("<li><i id=\"LinkLocal\">");
					pub.print(name);
					pub.println("</i>");
			} else if (perm.equals("E")) {	// Editorial
				// ignore for now
			}
			else if (perm.equals("A")) {	// Administration
				// ignore for now
			}
			else {
				System.err.println("WARNING: line " + line + 
					"--> has invalid permission tag " + perm);
			}
		}
		pub.close();
		logged.close();
		// teaser.close();
	}

	public void mkLink(PrintWriter out, String url, String name) {
		out.println("<li><a href=\"" + url + "\" id=\"LinkLocal\">" + name + "</a></li>");
	}

	public static void main(String argv[]) throws java.io.IOException {

		try {
			MkServices mk = new MkServices();
			mk.run();
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}
}
