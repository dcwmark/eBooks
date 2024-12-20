package jabadot;

import java.io.*;
import java.util.*;

/* DB interface for loading news articles */
public class NewsArticleDB {

	final static String DIR = JDConstants.getProperty("jabadot.articles_dir");

	public ArrayList getCurrentArticles() throws IOException {
		String[] files = new File(DIR).list();
		Arrays.sort(files);
		ArrayList al = new ArrayList();
		for (int i=0; i<files.length; i++) {
			NewsArticle art = new NewsArticle();
			art.setFileName(files[i]);
			BufferedReader is = new BufferedReader(new FileReader(
				DIR + files[i]));
			String line;
			while ((line = is.readLine()) != null)  {
				// Standard RFC822 null line after header
				if (line.length() == 0)
					break;
				// System.out.println(">>" + line);
				if (line.startsWith("Subject:"))
					art.setSubject(line.substring(line.indexOf(':')+1));
				else if (line.startsWith("Date:"))
					art.setDate(line.substring(line.indexOf(':')+1));
				else if (line.startsWith("From:"))
					art.setFrom(line.substring(line.indexOf(':')+1));
			}
			StringBuffer message = new StringBuffer();
			while ((line = is.readLine()) != null)  {
				message.append(line);
				message.append(' ');
			}
			art.setMessage(message.toString());
			al.add(art);
		}
		return al;
	}

	public static void main(String[] args) throws IOException {
		ArrayList al = new NewsArticleDB().getCurrentArticles();
		for (int i=0; i<al.size(); i++) {
			System.out.println(al.get(i));
		}
	}
}
