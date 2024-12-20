package jabadot;

/** One article to/from the Articles database */
public class NewsArticle {

	/** the filename */
	String fileName;
	/** the sender */
	String from;
	/** the subject (DOH!) */
	String subject;
	/** Date of submission */
	String date;
	/** Optional URL */
	String theURL;
	/** The message body */
	String message;

	/** Get Filename (aka title) */
	public String getFilename() {
		return fileName;
	}

	/** Set Filename */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/** Get From */
	public String getFrom() {
		return from;
	}

	/** Set From */
	public void setFrom(String from) {
		this.from = from;
	}

	/** Get Subject */
	public String getSubject() {
		return subject;
	}

	/** Set Subject */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/** Get Date */
	public String getDate() {
		return date;
	}

	/** Set Date */
	public void setDate(String date) {
		this.date = date;
	}

	/** Get URL */
	public String getURL() {
		return theURL;
	}

	/** Set URL */
	public void setURL(String aURL) {
		this.theURL = aURL;
	}

	/** Get Message */
	public String getMessage() {
		return message;
	}

	/** Set Message */
	public void setMessage(String message) {
		this.message = message;
	}

	public boolean contains(String s) {
		return
			subject.indexOf(s) != -1 ||
			message.indexOf(s) != -1;
	}

	public String toString() {
		return new StringBuffer("NewsArticle[").append(from).append('/').append(subject).append(']').toString();
	}

}
