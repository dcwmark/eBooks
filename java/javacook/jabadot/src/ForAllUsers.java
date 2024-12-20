package jabadot;

import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
import java.util.*;

/** JSP Custom Tag class for iterating over all JabaDot users.
 * Used by the admin page, and other pages that need a list.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id: ForAllUsers.java,v 1.2 2000/12/03 00:43:54 ian Exp $
 */
public class ForAllUsers extends BodyTagSupport {

	private String element;
	private Iterator it;
	private Object o;

	public void setElement(String elem) {
		element = elem;
		// reset the iterator!
		it = UserDB.getInstance().getUserList().iterator();
		// System.err.println("ForAllUsers: it=" + it);
	}

	/** Called after the start tag. Must get the *first* element from
	 * the collection and put it into the PageContext.
	 */
	public int doStartTag() throws JspException {
		// DELEGATE - code is all common.
		return doAfterBody();
	}


	/** Called AFTER the first (and subsequent) body has been output.
	 * This one controls the iteration!!
	 */
	public int doAfterBody() throws JspException {
		if (it == null) {
			return Tag.SKIP_BODY;
		}
		if (it.hasNext()) {
			o = it.next();
			
			// Put the "bean" named "element", really User object in o,
			// into the pageContext where <jsp:getProperty can find it.
			pageContext.setAttribute(element, o);

		 	return BodyTag.EVAL_BODY_TAG;	// Process it.
		}
		// All done the iterator, don't do body any more.
		return Tag.SKIP_BODY;
	}

	/** Final step - actually output something! */
	public int doEndTag() throws JspException {
		try {
			if (bodyContent != null) {
				bodyContent.writeOut(bodyContent.getEnclosingWriter());
			}
			return Tag.EVAL_PAGE;
		} catch (IOException ex) {
			throw new JspException(ex.toString());
		}
	}
}
