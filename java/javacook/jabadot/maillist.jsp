<%@page errorPage="oops.jsp" import="jabadot.*, javax.mail.*, java.util.*" %>
<!-- 
  -- $Id: maillist.jsp,v 1.1 2001/12/25 20:47:45 ian Exp $
  -->
<html>
<title>Mail Messages</title>
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

	if (!folder.isSubscribed()) {
		System.out.println(
			"<p><b>Mail Transport reports: Not Subscribed</b></p>");
	}

	// Print out subfolders
	if ((folder.getType() & Folder.HOLDS_FOLDERS) != 0) {
		Folder[] f = folder.list();
		out.print("<ul>");
		for (int i=0; i < f.length; i++) {
			out.print("<li>SubFolder: " + f[i]);
		}
		out.print("</ul>");
	}

	// Finally, print out messages
	if ((folder.getType() & Folder.HOLDS_MESSAGES) != 0) {
		if (folder.hasNewMessages())
			System.out.println("<p>New Messages</p>");

		Message[] msgs = folder.getMessages();
		
 %>
		<table border=1>
		<tr>
		<th>Select</th><th>Number</th><th>From</th><th>Subject</th><th>Date</th>
		</tr>
<%
		for (int i=0; i<msgs.length; i++) {
			Message m = msgs[i];
 %>
			<tr><td><input type="checkbox" />
			<td><%= m.getMessageNumber() %></td>
			<td><%= m.getFrom()[0] %></td>
<%			String subject = m.getSubject();
			if (subject == null)
				subject = "(No subject)";
 %>
			<td><a href="/jabadot/servlet/MailServlet?command=viewMessage&messageNum=<%= m.getMessageNumber() %>">
				<%= subject %></a></td>
			
<%			Date d = m.getSentDate();
 %>
			<td><%= d %></td>
			</tr>
<%
		}
 %>
		</table>
<%
	}
 %>
<hr />
<b>Delete Selected</b>
<b>Move Selected</b>
