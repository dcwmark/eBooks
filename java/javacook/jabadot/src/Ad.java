package jabadot;

	/** A class for storing the information about each ad. */
	class Ad {
		protected String url;
		protected String alt;
		protected String img;
		protected String html;	// The HTML form.

		Ad(String u, String a, String i) {
			url = u;
			alt = a;
			img = i;

			// Do in Constructor so overhead only happens once.
			StringBuffer sb = new StringBuffer();

			sb.append("<a href=\"");
			sb.append(url);
			sb.append("\">");
			sb.append("<img src=\"");
			sb.append(JDConstants.getProperty("jabadot.ads_url_prefix"));
			sb.append(img);
			sb.append("\" alt=\"");
			sb.append(alt);
			sb.append(" (Clicking here helps support JabaDot)");
			sb.append("\" border=0>");
			sb.append("</a>");
		
			html = sb.toString();
		}

		/** Return a String representation of the ad IN HTML.
		 * Heavily optimized so it only creates the string once.
		 */
		public String toString() {
			return html;
		}
	}
