package com.darwinsys.webmail;

import com.darwinsys.util.*;

import java.io.*;
import javax.servlet.*;;
import javax.servlet.http.*;;

/**
 * Servlet that sends email.
 * BEWARE of security implications!
 */
public class MailSendServlet extends HttpServlet {
   /**
	* Called to process each request.
    */
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
   {
		HttpSession session = request.getSession(false);
		if (session == null ||
			session.getAttribute("jabadot.user") == null) {
			out.println("<h1>Error</h1>");
			out.println("<p>You must be logged in before you can send mail!</p>");
			log("Non-logged-in mail servlet from " + request.getHost());
			return;
		}

      String title = "Mail Sender Servlet";
      String text = request.getParameter("text");
      String recipient = request.getParameter("recipient");
      String subject = request.getParameter("subject");
      
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      out.println("<html><head><title>");
      out.println(title);
      out.println("</title></head><body>");
      out.println("<h1>" + title + "</h1>");     
      out.println("<h2>Calling Mailer...</h2>");

      try
      {
            MimeMessage m = new MimeMessage(session);
            m.setFrom("ian@darwinsys.com");
            Address[] to = new InternetAddress[] {
                new InternetAddress(recipient)
            };
            m.setRecipients(Message.RecipientType.TO, to);
            m.setSubject(subject);
            m.setSentDate(new Date());
            m.setContent(text);
            Transport.send(m);
		 out.println("Mail sent....");
      }
      catch(Exception e)
      {
         out.println(e.toString());
      }
      finally
      {
         out.println("</BODY></HTML>");
         out.close();
      }
   }
}
