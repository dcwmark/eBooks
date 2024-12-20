<%@page errorPage="oops.jsp"
	import="jabadot.*, javax.mail.*, javax.mail.internet.*, java.util.*" %>
<!-- 
  -- $Id: mailview.jsp,v 1.1 2001/12/25 20:47:45 ian Exp $
  -->
<html>
<title>Mail Message</title>
<%
	User user = (User)session.getAttribute("jabadot.login");
	if (user == null) {
		out.println("<b>You must be logged in to use the mail service</b>");
		return;
	}
	Session mailSession = (Session)session.getAttribute("jabadot.mail.session");
	String root = (String)session.getAttribute("jabadot.mail.folder");
	String mailProtocol = 
		JDConstants.getProperty("jabadot.email.receive.protocol");
	String mailReadHost =
		JDConstants.getProperty("jabadot.email.receive.host");

	// Get a Store object for the given protocol
	Store store = mailSession.getStore(mailProtocol);
	if (store == null) {
		throw new IllegalStateException("store=null,sess=" + mailSession +
			", proto=" + mailProtocol);
	}

	// Connect to it.
	store.connect(mailReadHost, user.getName(), user.getPassword());

	// Get Folder object for root, and list it
	// If root name = "", getDefaultFolder(), else getFolder(root)
	Folder folder;
	if (root.length() != 0) {
		System.out.println("Getting folder " + root + ".");
		folder = store.getFolder(root);
	} else {
		System.out.println("Getting default folder.");
		folder = store.getDefaultFolder();
	}
	folder.open(Folder.READ_WRITE);

	// Finally, get the message
	if ((folder.getType() & Folder.HOLDS_MESSAGES) == 0) {
		out.println("NO MESSAGES");
		return;
	}
	int messageNum = Integer.parseInt(request.getParameter("messageNum"));
	Message m = folder.getMessage(messageNum);
		
 %>
	<table border="1" width="100%">
		<tr><td width="20%">From:<td width="80%"><%=m.getFrom()[0]%></td></tr>
<%		String subject = m.getSubject();
		if (subject == null)
			subject = "(No subject)";
 %>
		<tr><td>Subject:<td><%= subject %></td> </tr>
		<tr><td>Date:<td><%= m.getSentDate() %></td> </tr>
	</table>
	<br />
	<hr />
<%
	Object o = m.getContent();
	if (o instanceof String) {
		out.print(o);
	} else if (o instanceof MimeMultipart) {
		MimeMultipart mp = (MimeMultipart)o;
		for (int i=0; i<mp.getCount(); i++) {
			out.print(mp.getBodyPart(i).getContent());
			out.println("<hr />");
		}
	} else {
		out.print("UNKNOWN TYPE " + o.getClass().getName());
	}
 %>
<hr />
<a href="/jabadot/servlet/MailServlet?command=replyMessage&messageNum=<%= m.getMessageNumber() %>">Reply</a>
<br />
<a href="/jabadot/servlet/MailServlet?command=deleteMessage&messageNum=<%= m.getMessageNumber() %>">Delete</a>
<br />
Back to <a href="/jabadot/mail.jsp">All Messages</a>
