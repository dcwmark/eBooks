package jabadot;

import java.io.*;
import java.util.*;

/** Class for accessing the list of Ads. */
public class AdAccessor {

	static ArrayList load(File file) throws IOException {
		ArrayList list = new ArrayList();
		BufferedReader is = new BufferedReader(new FileReader(file));
		String line;
		while ((line = is.readLine()) != null) {
			// Must use something that doesn't occur in URLs as delim
			// Can't serve "file://C|/foo/bar" (which is at any rate invalid)
			StringTokenizer st = new StringTokenizer(line, "|");
			String url = st.nextToken();
			String alt = st.nextToken();
			String img = st.nextToken();
			System.out.println(url + "--" + alt + "--" + img);
			list.add(new Ad(url, alt, img));
		}
		is.close();
		return list;
	}
}
