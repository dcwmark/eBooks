package jabadot;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Banner Ad Rotator, similar to the M$ Ad Rotator provided in ASP.
 *
 * Converted to a servlet for efficiency.
 *
 * Picks ad at random.
 *
 * Input file format is like this:
 * URL-to-image:alt-desc:ImageName
 * e.g.
 * http://woo.trbleclick.com/images/Gurfle.gif:Georgeous Grufles:GGBanner.gif
 */
public class AdServlet extends HttpServlet {

	/** The list of ads */
	protected ArrayList list;

	/** The File object for statting the adlist file */
	protected File file;

	/** A Random Number generator for picking the next ad. */
	Random r = new Random();

	protected long load_time = 0;

	/** initialize the AdServlet -- load the adlist */
	public void init(ServletConfig cfg) throws ServletException {
		try {
			String adsList = JDConstants.getProperty("jabadot.dir.ads") +
					JDConstants.getProperty("jabadot.ads_def_list");
			System.err.println("AdServlet: Opening " + adsList);
			file = new File(adsList);
			list = AdAccessor.load(file);
			load_time = file.lastModified();
		} catch (IOException e) {
			throw new ServletException(e.toString());
		}
	}

	/** Print the HTML for the next advertisement for a user.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		// If the list-of-ads file has changed, reload it.
		// Synchronized so we only reload once, to avoid having
		// multiple threads calling load().
		synchronized(this) {
			if (load_time < file.lastModified()) {
				out.println("<!-- reloaded adrotator -->");
				list = AdAccessor.load(file);
				load_time = file.lastModified();
			}
		}

		// Pick an ad at random from the list
		int n = r.nextInt(list.size());
		Ad ad = (Ad)list.get(n);

		// Convert it to HTML
		out.println("<!-- Ad link made by AdServlet -->");
		out.println(ad.html);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		doPost(req, resp);
	}
}
