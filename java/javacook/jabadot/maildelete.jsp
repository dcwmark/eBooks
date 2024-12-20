<%@page errorPage="oops.jsp"
	import="jabadot.*, javax.mail.*, javax.mail.internet.*, java.util.*" %>
<!-- 
  -- $Id: maildelete.jsp,v 1.1 2001/12/25 20:47:45 ian Exp $
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

	// Now delete the message
	if ((folder.getType() & Folder.HOLDS_MESSAGES) == 0) {
		out.println("NO MESSAGES");
		return;
	}
	int messageNum = Integer.parseInt(request.getParameter("messageNum"));
	folder.getMessage(messageNum);
	folder.delete(false);

	folder.expunge();		// drastic -- no undo
		
 %>
<p>If that didn't throw an exception, it should be deleted.</p>
Back to <a href="/jabadot/mail.jsp">All Messages</a>
