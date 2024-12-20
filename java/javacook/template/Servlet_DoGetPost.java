import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
 * __NAME__.java
 * 
 * @author  __USER__
 * @version $Id: Servlet_DoGetPost.java,v 1.4 2001/10/27 18:57:20 ian Exp $ Created on __DATE__, __TIME__
 */
public class Servlet_DoGetPost extends HttpServlet
{
	/** Called in response to a GET request (data encoded in the URL) */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter(); 

		// BOILERPLATE beginning
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Servlet Output</TITLE></HEAD>");
		out.println("<BODY>");

		// to do: logic code and main HTML goes here.

		// BOILERPLATE ending
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}

	/** Called in response to a POST request (data unencoded on the socket) */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter(); 

		// BOILERPLATE beginning
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Servlet Output</TITLE></HEAD>");
		out.println("<BODY>");

		// to do: logic code and main HTML goes here.

		// BOILERPLATE ending
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}
}
