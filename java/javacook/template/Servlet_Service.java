import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
 * __NAME__.java
 * 
 * @author  __USER__
 * @version $Id: Servlet_Service.java,v 1.4 2001/10/27 18:57:20 ian Exp $ Created on __DATE__, __TIME__
 */
public class Servlet_Service extends HttpServlet
{
	/** Called in response to any request. */
	public void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String requestType = request.getMethod();

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
