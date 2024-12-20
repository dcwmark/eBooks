package jabadot;

import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Simple Dispatcher Servlet for Mail -- most work done by JSP's
 */
public class MailServlet extends HttpServlet {

	protected String mailReadRoot;
	/** The mail session, not the HTTP session */
	protected Session mailSession;
	// XXX hope that javax.mail.Session is threadsafe!

	/** The init method is called when the servlet is starting. */
	public void init() throws ServletException {

		mailReadRoot = JDConstants.getProperty("jabadot.email.receive.root");

		// Start with a JavaMail Session object, as usual
		mailSession = Session.getDefaultInstance(
			System.getProperties(), null);
		mailSession.setDebug(false);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {

		User user;

		// Session must exist; don't bother creating here if not.
		HttpSession httpSession = request.getSession(false);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (httpSession == null ||
		    (user = (User)(httpSession.getAttribute("jabadot.login"))) == null) {
			out.println(
			"<H1>ERROR</H1><p>You must be logged in to use the Mail Service.");
			out.close();
			return;
		}

		// Put mail parameters (loaded by init()) into HttpSession
		httpSession.setAttribute("jabadot.mail.session", mailSession);

		// Find out from the HTTP request which function we're to do.
		String command = request.getParameter("command");

		// Do it.
		if (command == null || command.equals("list")) {
			httpSession.setAttribute("jabadot.mail.folder", mailReadRoot);
		}
		else if (command.equals("viewMessage")) {
			getServletContext().getRequestDispatcher("/mailview.jsp").forward(request, response);
		}
		else if (command.equals("replyMessage")) {
			getServletContext().getRequestDispatcher("/mailreply.jsp").forward(request, response);
		}
		else if (command.equals("deleteMessage")) {
			getServletContext().getRequestDispatcher("/maildelete.jsp").forward(request, response);
		}
		else if (command.equals("chdir")) {
			String newdir = request.getParameter("newdir");
			httpSession.setAttribute("jabadot.mail.folder", newdir);
		}
			
		// XXX TODO
		//	compose new
		//	replyAll
		//	move?
		else {
			out.println("<h1>Error</h1><p>Mail servlet - function not written yet");
		}
		// Finally, do a "jsp include" of the list JSP to display
		// the current folder list view.
		getServletContext().getRequestDispatcher("/maillist.jsp").include(request, response);
		out.close();
	}
}
